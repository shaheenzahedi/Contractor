package core.service.callback

import core.domain.ready_to_generate.MutationType
import core.domain.ready_to_generate.ReadyToTestModel
import core.service.engine.ContractMutationEngine

class CallbackMapper(private val model: List<ReadyToTestModel>) {

    fun callbacks(): List<CallbackCase> {
        return model.flatMap { buildCallbacks(it, false) }
    }

    fun mutations(): List<CallbackCase>? {
        return ContractMutationEngine(model)
            .generateContractMutants()
            ?.flatMap { buildCallbacks(it, true) }
    }

    private fun buildCallbacks(model: ReadyToTestModel, isMutation: Boolean): List<CallbackCase> =
        with(CallbackGenerator(model, isMutation)) {
            val result = getListCallback(model, isMutation)
            if (isMutation && model.mutationMetaData != null) {
                result.map { it.copy(mutationName = model.mutationMetaData!!.name) }
            } else {
                result
            }
        }

    private fun CallbackGenerator.getListCallback(rttModel: ReadyToTestModel, isMutation: Boolean): List<CallbackCase> {
        if (!isMutation)
            return mutableListOf(bodyTest(), headerTest(), statusTest())
                .apply {
                    addAll(generateBodyRulesTest())
                    addAll(generateHeaderRulesTest())
                }.filterNotNull()
        return mutableListOf<CallbackCase?>().apply {
            when (rttModel.mutationMetaData?.type) {
                MutationType.STATUS -> add(statusTest())
                MutationType.HTTP_METHOD -> null
                MutationType.REQUEST_HEADER -> add(headerTest())
                MutationType.RESPONSE_HEADER -> add(headerTest())
                MutationType.REQUEST_BODY -> null
                MutationType.RESPONSE_BODY -> add(bodyTest())
                MutationType.REQUEST_PARAMS -> null
                MutationType.RESPONSE_PARAMS -> null
                MutationType.REQUEST_COOKIES -> null
                null -> null
            }
        }.filterNotNull()
    }
}