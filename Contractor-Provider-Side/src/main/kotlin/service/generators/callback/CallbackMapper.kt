package service.generators.callback

import domain.ready_to_generate.ReadyToTestModel

class CallbackMapper {

    fun callbacks(model: List<ReadyToTestModel>): List<CallbackCase> {
        return model.toMutableList().map(::buildCallbacks).flatten()
    }

    private fun buildCallbacks(model: ReadyToTestModel): List<CallbackCase> {
        val generator = CallbackGenerator(model)
        return mutableListOf(
            generator.bodyTest(),
            generator.headerTest(),
            generator.generateStatusTest()
        ).apply {
            addAll(generator.generateBodyRulesTest())
            addAll(generator.generateRuleStatementTest(),)
        }.filterNotNull()
    }
}