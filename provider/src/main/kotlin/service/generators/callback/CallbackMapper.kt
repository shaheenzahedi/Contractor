package service.generators.callback

import core.domain.ready_to_generate.ReadyToTestModel
import core.service.engine.ContractMutationEngine

class CallbackMapper(private val model: List<ReadyToTestModel>) {

    fun callbacks(): List<CallbackCase> {
        return model.toMutableList().map(::buildCallbacks).flatten()
    }

    fun mutations(): List<CallbackCase>? {
        return ContractMutationEngine(model).generateContractMutants()?.toMutableList()?.map(::buildCallbacks)?.flatten()
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