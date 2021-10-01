package core.domain.contract.pact


import com.google.gson.annotations.SerializedName
import core.domain.contract.GeneralContract
import core.domain.contract.SupportedTypes
import core.domain.contract.pact.consumer.ConsumerDTO
import core.domain.contract.pact.interactions.InteractionDTO
import core.domain.contract.pact.interactions.providerStates.ProviderStateDTO
import core.domain.contract.pact.metadata.MetadataDTO
import core.domain.contract.pact.provider.ProviderDTO

data class PactContractModel(
    @SerializedName("consumer")
    val consumerDTO: ConsumerDTO?,
    @SerializedName("interactions")
    val interactionDTOS: List<InteractionDTO>?,
    @SerializedName("metadata")
    val metadataDTO: MetadataDTO?,
    @SerializedName("provider")
    val providerDTO: ProviderDTO?,
    @SerializedName("providerStates")
    val providerStateDTOS: List<ProviderStateDTO>?,
) : GeneralContract {
    override val type
        get() = SupportedTypes.PACT

    override val isAllNull
        get() = listOf(consumerDTO, interactionDTOS, metadataDTO, providerDTO).all { it == null }

}