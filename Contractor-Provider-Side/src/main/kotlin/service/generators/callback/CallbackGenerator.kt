package service.generators.callback

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
        return null
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

    fun generateHeaderTest(): CallbackCase? {
        return null
    }

    fun generateBodyTest(): CallbackCase? {
        return null
    }


}