package core.service.engine

import com.google.gson.Gson
import com.natpryce.snodge.json.defaultJsonMutagens
import com.natpryce.snodge.json.forStrings
import com.natpryce.snodge.mutants
import core.domain.ready_to_generate.HTTPMethod
import core.domain.ready_to_generate.ReadyToTestModel
import kotlin.random.Random

class ContractMutationEngine(private val contracts: List<ReadyToTestModel>?) {
    private val mutationCount = 5

    fun generateContractMutants(): List<ReadyToTestModel>? {
        return contracts?.flatMapIndexed { index, contract ->
            val statusMutations = generateStatusMutation(contract.status!!).map { contract.copy(status = it) }
            val methodMutations = generateMethodMutations(contract.method).map { contract.copy(method = it) }
            val bodyResponseMutations = generateResponseBodyMutations(index, contract.response?.body)?.map {
                contract.copy(response = contract.response?.copy(body = it))
            }
            val requestBodyMutations = generateRequestBodyMutations(index, contract.request?.body)?.map {
                contract.copy(request = contract.request?.copy(body = it))
            }
            val requestHeaderMutations = generateRequestHeaderMutations(index, contract.request?.headers)?.map {
                contract.copy(request = contract.request?.copy(headers = it))
            }
            val paramsMutations = generateParamsMutations(index, contract.request?.params)?.map {
                contract.copy(
                    request = contract.request?.copy(params = it)
                )
            }
            val cookiesMutations = generateCookiesMutations(index, contract.request?.cookies)?.map {
                contract.copy(
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

    fun generateStatusMutation(status: Int) = mutableListOf(
        100,        //Continue
        101,        //Switching Protocol
        300,         //Multiple Choices
        301,        //Moved Permanently
        302,        //Found
        303,        //See Other
        304,        //Not Modified
        305,        //Use Proxy
        306,        //Switch Proxy
        307,        //Temporary Redirect
        308,        //Permanent Redirect
        500,        //Internal Server Error
        501,        //Not Implemented
        502,        //Bad Gateway
        503,        //Service Unavailable
        504,        //Gateway Timeout
        505,        //HTTP Version Not Supported
        506,        //Variant Also Negotiates
        510,        //Not Extended
        511,        //Network Authentication Required
        200,        //OK
        201,        //Created
        202,        //Accepted
        203,        //Non-Authoritive Information
        204,        //No Content
        205,        //Reset Content
        206,        //Partial Content
        226,        //IM Used
        400,        //Bad Request
        401,        //Unauthorized
        402,        //Payment Required
        403,        //Forbidden
        404,        //Not Found
        405,        //Method Not Allowed
        406,        //Not Acceptable
        407,        //Proxy Authentication Required
        408,        //Request Timeout
        409,        //Conflict
        410,        //Gone
        411,        //Length Required
        412,        //Precondition Failed
        413,        //Payload Too Large
        414,        //URI Too Long
        415,        //Unsupported Media Type
        416,        //Range Not Satisfiable
        417,        //Expectation Failed
        418,        //I’m a teapot
        421,        //Misdirected Request
        426,        //Upgrade Required
        428,        //Precondition Required
        429,        //Too Many Requests
        431,        //Request Header Fields Too Large
        451,        //Unavailable For Legal Reasons
    ).apply { removeIf { it == status } }

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