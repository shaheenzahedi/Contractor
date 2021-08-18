package service.generators.callback

import domain.ready_to_generate.ReadyToTestModel

class CallbackGenerator(
    private val model: ReadyToTestModel
) {
    fun headerTest(): CallbackCase {
        TODO("Not yet implemented")
    }

    fun bodyTest(): CallbackCase? {
        TODO()
    }
    fun generateBodyRulesTest(): List<CallbackCase?> {
        TODO()
    }
    fun generateRuleStatementTest():List<CallbackCase?>  {
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