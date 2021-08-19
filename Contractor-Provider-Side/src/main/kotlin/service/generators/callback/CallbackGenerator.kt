package service.generators.callback

import com.google.gson.GsonBuilder
import domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response

class CallbackGenerator(
    private val model: ReadyToTestModel
) {

    private val response: Response

    init {
        val handler = HTTPHandler(model)
        response = handler.retrieveResponse()
    }

    fun headerTest(): CallbackCase? {
        if (model.response?.headers == null) return null
        if (model.response.headers.isEmpty()) return null
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            name = "Asserts that the response has the desired header",
            callback = { matchesMap(response.headers, model.response.headers) },
            expected = GsonBuilder().setPrettyPrinting().create().toJson(model.response.headers),
            actual = GsonBuilder().setPrettyPrinting().create().toJson(response.headers),
            reason = "Response did not include those header defined in contract"
        )
    }

    fun bodyTest(): CallbackCase? {
        return null
    }

    fun generateBodyRulesTest(): List<CallbackCase?> {
        return emptyList()
    }

    fun generateRuleStatementTest(): List<CallbackCase?> {
        return emptyList()
    }

    fun generateStatusTest(): CallbackCase? {
        if (model.status == null) return null
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            name = "Assert if the status equals to ${model.status}",
            callback = { response.statusCode == model.status },
            expected = model.status.toString(),
            actual = response.statusCode.toString(),
            reason = "Status is not ${model.status} as defined in the contract"
        )
    }


    private fun matchesMap(map1: Map<String, Any>, reference: Map<String, Any>): Boolean {
        return reference.all { (k, v) -> map1[k] == v }
    }

    fun generateBodyTest(): CallbackCase? {
        return null
    }


}