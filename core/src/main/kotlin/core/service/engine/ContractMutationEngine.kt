package core.service.engine

import com.google.gson.Gson
import com.natpryce.snodge.json.defaultJsonMutagens
import com.natpryce.snodge.json.forStrings
import com.natpryce.snodge.mutants
import core.domain.ready_to_generate.HTTPMethod
import core.domain.ready_to_generate.MutationMetaData
import core.domain.ready_to_generate.MutationType
import core.domain.ready_to_generate.ReadyToTestModel
import kotlin.random.Random

class ContractMutationEngine(private val contracts: List<ReadyToTestModel>?) {
    private val mutationCount = 5

    fun generateContractMutants(): List<ReadyToTestModel>? {
        return contracts?.flatMapIndexed { index, contract ->
            val statusMutations =
                generateStatusMutation(contract.status!!).map {
                    contract.copy(
                        mutationMetaData = MutationMetaData(
                            name = "Mutating status code with ${it.key}(${it.value})",
                            type = MutationType.STATUS
                        ),
                        status = it.key
                    )
                }
            val methodMutations = generateMethodMutations(contract.method).map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating HTTP method code with $it",
                        type = MutationType.HTTP_METHOD
                    ),
                    method = it
                )
            }
            val bodyResponseMutations = generateResponseBodyMutations(index, contract.response?.body)?.map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating response body with $it",
                        MutationType.RESPONSE_BODY
                    ), response = contract.response?.copy(body = it)
                )
            }
            val requestBodyMutations = generateRequestBodyMutations(index, contract.request?.body)?.map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating request body with $it",
                        MutationType.REQUEST_BODY
                    ), request = contract.request?.copy(body = it)
                )
            }
            val requestHeaderMutations = generateRequestHeaderMutations(index, contract.request?.headers)?.map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating request header with $it",
                        type = MutationType.REQUEST_HEADER
                    ), request = contract.request?.copy(headers = it)
                )
            }
            val paramsMutations = generateParamsMutations(index, contract.request?.params)?.map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating request params with $it",
                        type = MutationType.REQUEST_PARAMS
                    ),
                    request = contract.request?.copy(params = it)
                )
            }
            val cookiesMutations = generateCookiesMutations(index, contract.request?.cookies)?.map {
                contract.copy(
                    mutationMetaData = MutationMetaData(
                        name = "Mutating cookies with $it",
                        type = MutationType.REQUEST_COOKIES
                    ),
                    request = contract.request?.copy(cookies = it)
                )
            }
            mutableListOf<ReadyToTestModel>().apply {
                addAll(statusMutations)
                addAll(methodMutations)
                if (bodyResponseMutations != null) addAll(bodyResponseMutations)
                if (requestBodyMutations != null) addAll(requestBodyMutations)
                if (requestHeaderMutations != null) addAll(requestHeaderMutations)
                if (paramsMutations != null) addAll(paramsMutations)
                if (cookiesMutations != null) addAll(cookiesMutations)
            }
        }
    }

    fun generateStatusMutation(status: Int) = mutableMapOf(
        100 to "Continue",
        101 to "Switching Protocol",
        300 to "Multiple Choices",
        301 to "Moved Permanently",
        302 to "Found",
        303 to "See Other",
        304 to "Not Modified",
        305 to "Use Proxy",
        306 to "Switch Proxy",
        307 to "Temporary Redirect",
        308 to "Permanent Redirect",
        500 to "Internal Server Error",
        501 to "Not Implemented",
        502 to "Bad Gateway",
        503 to "Service Unavailable",
        504 to "Gateway Timeout",
        505 to "HTTP Version Not Supported",
        506 to "Variant Also Negotiates",
        510 to "Not Extended",
        511 to "Network Authentication Required",
        200 to "OK",
        201 to "Created",
        202 to "Accepted",
        203 to "Non-Authoritive Information",
        204 to "No Content",
        205 to "Reset Content",
        206 to "Partial Content",
        226 to "IM Used",
        400 to "Bad Request",
        401 to "Unauthorized",
        402 to "Payment Required",
        403 to "Forbidden",
        404 to "Not Found",
        405 to "Method Not Allowed",
        406 to "Not Acceptable",
        407 to "Proxy Authentication Required",
        408 to "Request Timeout",
        409 to "Conflict",
        410 to "Gone",
        411 to "Length Required",
        412 to "Precondition Failed",
        413 to "Payload Too Large",
        414 to "URI Too Long",
        415 to "Unsupported Media Type",
        416 to "Range Not Satisfiable",
        417 to "Expectation Failed",
        418 to "I’m a teapot",
        421 to "Misdirected Request",
        426 to "Upgrade Required",
        428 to "Precondition Required",
        429 to "Too Many Requests",
        431 to "Request Header Fields Too Large",
        451 to "Unavailable For Legal Reasons",
    ).apply { remove(status) }

    fun generateMethodMutations(method: HTTPMethod) =
        HTTPMethod.values().toMutableList().apply { removeIf { it == method } }

    fun generateResponseBodyMutations(
        position: Int,
        body: LinkedHashMap<String, Any>?
    ): List<LinkedHashMap<String, Any>>? {
        return generateAnyMutatedPairs(contracts?.get(position)?.response?.body)?.filter { it != body }
    }

    fun generateRequestBodyMutations(
        position: Int,
        body: LinkedHashMap<String, Any>?
    ): List<LinkedHashMap<String, Any>>? {
        return generateAnyMutatedPairs(contracts?.get(position)?.request?.body)?.filter { it != body }
    }

    fun generateRequestHeaderMutations(
        position: Int,
        headers: LinkedHashMap<String, String>?
    ): List<LinkedHashMap<String, String>>? {
        return generateStringMutatedPairs(contracts?.get(position)?.request?.headers)?.filter { it != headers }
    }

    fun generateParamsMutations(
        position: Int,
        params: LinkedHashMap<String, String>?
    ): List<LinkedHashMap<String, String>>? {
        return generateStringMutatedPairs(contracts?.get(position)?.request?.params)?.filter { it != params }
    }

    fun generateCookiesMutations(
        position: Int,
        cookies: LinkedHashMap<String, String>?
    ): List<LinkedHashMap<String, String>>? {
        return generateStringMutatedPairs(contracts?.get(position)?.request?.cookies)?.filter { it != cookies }
    }

    private fun generateStringMutatedPairs(body: LinkedHashMap<String, String>?) =
        body?.let { generateMutatedPairs(it).map { mutant -> mutant as LinkedHashMap<String, String> } }

    private fun generateAnyMutatedPairs(body: LinkedHashMap<String, Any>?) =
        body?.let { generateMutatedPairs(it).map { mutant -> mutant as LinkedHashMap<String, Any> } }

    private fun generateMutatedPairs(body: LinkedHashMap<*, *>): List<LinkedHashMap<*, *>> =
        Random
            .mutants(defaultJsonMutagens().forStrings(), mutationCount, Gson().toJson(body))
            .map { Gson().fromJson(it, LinkedHashMap::class.java) }
}