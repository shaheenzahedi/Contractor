package service.generators.callback

import core.domain.ready_to_generate.ReadyToTestModel
import core.service.engine.ContractMutationEngine

class CallbackMapper(private val model: List<ReadyToTestModel>) {

    fun callbacks(): List<CallbackCase> {
        return model.toMutableList().map { buildCallbacks(it, false) }.flatten()
    }

    fun mutations(): List<CallbackCase>? {
        return ContractMutationEngine(model)
            .generateContractMutants()
            ?.flatMap { buildCallbacks(it, true) }
    }

    private fun buildCallbacks(model: ReadyToTestModel, isMutation: Boolean): List<CallbackCase> =
        with(CallbackGenerator(model, isMutation)) {
            val result = mutableListOf(bodyTest(), headerTest(), statusTest())
                .apply {
                    addAll(generateBodyRulesTest())
                    addAll(generateHeaderRulesTest())
                }.filterNotNull()
            if (isMutation && model.name != null) {
                result.map { it.copy(mutationName = model.name!!) }
            } else {
                result
            }
        }
}