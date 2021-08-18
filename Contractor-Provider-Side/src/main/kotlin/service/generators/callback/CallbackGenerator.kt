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
    }

    fun headerTest(): CallbackCase {
        TODO()
    }

    fun bodyTest(): CallbackCase? {
        TODO()
    }

    fun generateBodyRulesTest(): List<CallbackCase?> {
        TODO()
    }

    fun generateRuleStatementTest(): List<CallbackCase?> {
        TODO()
    }

    fun generateStatusTest(): CallbackCase? {
        TODO()
    }

    fun generateHeaderTest(): CallbackCase? {
        TODO()
    }

    fun generateBodyTest(): CallbackCase? {
        TODO()
    }


}