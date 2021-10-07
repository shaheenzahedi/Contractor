package service.generators.callback

import core.domain.ready_to_generate.ReadyToTestModel

class CallbackMapper {

    fun callbacks(model: List<ReadyToTestModel>): List<CallbackCase> {
        return model.toMutableList().map(::buildCallbacks).flatten()
    }

    private fun buildCallbacks(model: ReadyToTestModel): List<CallbackCase> =
        with(CallbackGenerator(model)) {
            mutableListOf(bodyTest(), headerTest(), statusTest())
                .apply {
                    addAll(generateBodyRulesTest())
                    addAll(generateHeaderRulesTest())
                }.filterNotNull()
        }
}