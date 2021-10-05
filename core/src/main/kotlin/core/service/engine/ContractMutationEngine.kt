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
    fun generateStatusMutation(position: Int): List<Int>? {
        val status = contracts?.get(position)?.status
        requireNotNull(status) { return null }
        return getGeneratedStatus(status)
    }

    private fun getGeneratedStatus(status: Int): List<Int> {
        fun generateNotSameNumber(number: Int): Int {
            var result = (100..999).random()
            while (result == number) {
                result = (100..999).random()
            }
            return result
        }
        return mutableListOf<Int>()
            .apply { for (i in 1..mutationCount) add(generateNotSameNumber(status)) }
    }

    fun generateMethodMutations(position: Int): List<HTTPMethod>? {
        val method = contracts?.get(position)?.method
        requireNotNull(method) { return null }
        return HTTPMethod.values().toMutableList().apply { removeIf { it == method } }
    }

    fun generateResponseBodyMutations(position: Int): List<LinkedHashMap<String, Any>>? {
        val body = contracts?.get(position)?.response?.body
        requireNotNull(body) { return null }
        return generateMutatedBody(body).map { it as LinkedHashMap<String, Any> }
    }

    private fun generateMutatedBody(body: LinkedHashMap<*,*>): List<LinkedHashMap<*, *>> =
        Random
            .mutants(defaultJsonMutagens().forStrings(), mutationCount, Gson().toJson(body))
            .map { Gson().fromJson(it, LinkedHashMap::class.java) }

    fun generateHeaderMutations(position: Int): List<LinkedHashMap<String, String>>? {
        val headers = contracts?.get(position)?.request?.headers
        requireNotNull(headers){return null}
        return generateMutatedBody(headers).map { it as LinkedHashMap<String, String> }
    }


}