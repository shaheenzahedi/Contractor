package service.generators.callback

import domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response

class CallbackGenerator(
    private val model: ReadyToTestModel
) {

    private val response: Response

    init {
        println("attempt to make the request")
        val handler = HTTPHandler(model)
        response = handler.retrieveResponse()
        response.toString()
        response.jsonObject
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
            name = "Testing status code",
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