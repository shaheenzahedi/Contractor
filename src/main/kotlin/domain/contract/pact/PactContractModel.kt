package domain.contract.pact


import com.google.gson.annotations.SerializedName
import domain.contract.pact.consumer.ConsumerDTO
import domain.contract.pact.interactions.InteractionDTO
import domain.contract.pact.interactions.providerStates.ProviderStateDTO
import domain.contract.pact.metadata.MetadataDTO
import domain.contract.pact.provider.ProviderDTO

data class PactContractModel(
    @SerializedName("consumer")
    val consumerDTO: ConsumerDTO,
    @SerializedName("interactions")
    val interactionDTOS: List<InteractionDTO>,
    @SerializedName("metadata")
    val metadataDTO: MetadataDTO,
    @SerializedName("provider")
    val providerDTO: ProviderDTO,
    @SerializedName("providerStates")
    val providerStateDTOS: List<ProviderStateDTO>
)