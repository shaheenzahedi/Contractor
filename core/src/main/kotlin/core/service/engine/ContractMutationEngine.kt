package core.service.engine

import com.google.gson.Gson
import com.natpryce.snodge.json.defaultJsonMutagens
import com.natpryce.snodge.json.forStrings
import com.natpryce.snodge.mutants
import core.domain.ready_to_generate.HTTPMethod
import core.domain.ready_to_generate.ReadyToTestModel
import core.domain.ready_to_generate.request.ReadyRequestModel
import core.domain.ready_to_generate.response.ReadyResponseModel
import kotlin.random.Random

class ContractMutationEngine(private val contracts: List<ReadyToTestModel>?) {
    private val mutationCount = 5

    fun generateContractMutants(): List<ReadyToTestModel>? {
        return contracts?.mapIndexed { index, contract ->
            val statusMutations = generateStatusMutation()
            val methodMutations = generateMethodMutations()
            val bodyResponseMutations = generateResponseBodyMutations(index)
            val requestBodyMutations = generateRequestBodyMutations(index)
            val requestHeaderMutations = generateRequestHeaderMutations(index)
            val paramsMutations = generateParamsMutations(index)
            val cookiesMutations = generateCookiesMutations(index)
            (0 until mutationCount).map { i ->
              ReadyToTestModel(
                    baseUrl = contract.baseUrl,
                    port = contract.port,
                    method = methodMutations[i],
                    path = contract.path,
                    status= statusMutations[i],
                    request = ReadyRequestModel(
                        body = requestBodyMutations?.get(i),
                        headers = requestHeaderMutations?.get(i),
                        params = paramsMutations?.get(i),
                        cookies = cookiesMutations?.get(i),
                        data = null
                    ),
                    response = ReadyResponseModel(
                        body = bodyResponseMutations?.get(i),
                        headers = null,
                        bodyPredicates = null,
                        headerPredicates = null
                    )
                )
            }
        }?.toMutableList()?.flatten()
    }

    fun generateStatusMutation() = (1..mutationCount).map { (100..999).random() }

    fun generateMethodMutations() = HTTPMethod.values().toMutableList()

    fun generateResponseBodyMutations(position: Int) =
        generateAnyMutatedPairs(contracts?.get(position)?.response?.body)

    fun generateRequestBodyMutations(position: Int) =
        generateAnyMutatedPairs(contracts?.get(position)?.request?.body)

    fun generateRequestHeaderMutations(position: Int) =
        generateStringMutatedPairs(contracts?.get(position)?.request?.headers)

    fun generateParamsMutations(position: Int) =
        generateStringMutatedPairs(contracts?.get(position)?.request?.params)

    fun generateCookiesMutations(position: Int) =
        generateStringMutatedPairs(contracts?.get(position)?.request?.cookies)

    private fun generateStringMutatedPairs(body: LinkedHashMap<String, String>?) =
        body?.let { generateMutatedPairs(it).map { mutant -> mutant as LinkedHashMap<String, String> } }

    private fun generateAnyMutatedPairs(body: LinkedHashMap<String, Any>?) =
        body?.let { generateMutatedPairs(it).map { mutant -> mutant as LinkedHashMap<String, Any> } }

    private fun generateMutatedPairs(body: LinkedHashMap<*, *>): List<LinkedHashMap<*, *>> =
        Random
            .mutants(defaultJsonMutagens().forStrings(), mutationCount, Gson().toJson(body))
            .map { Gson().fromJson(it, LinkedHashMap::class.java) }
}