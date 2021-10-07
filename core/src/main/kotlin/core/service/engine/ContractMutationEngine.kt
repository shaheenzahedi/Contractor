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