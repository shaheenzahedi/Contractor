package service.generators.callback

import domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response
import presentation.PrettyPrintingMap

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
            name = "`${model.method.name}\t${model.path}\n\n" +
                    "Asserts that the response has the desired header",
            callback = { matchesMap(response.headers, model.response.headers) },
            expected = PrettyPrintingMap(map = model.response.headers).toString(),
            actual = PrettyPrintingMap(map = response.headers).toString()
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
            name = "`${model.method.name}\t${model.path}\n\n" +
                    "Assert if the status equals to ${model.status}",
            callback = { response.statusCode == model.status },
            expected = model.status.toString(),
            actual = response.statusCode.toString()
        )
    }


    private fun matchesMap(map1: Map<String, Any>, reference: Map<String, Any>): Boolean {
        return reference.all { (k, v) -> map1[k] == v }
    }

    fun generateBodyTest(): CallbackCase? {
        return null
    }


}