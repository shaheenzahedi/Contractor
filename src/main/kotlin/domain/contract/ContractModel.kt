package domain.contract


import com.google.gson.annotations.SerializedName
import domain.contract.consumer.ConsumerDTO
import domain.contract.interactions.InteractionDTO
import domain.contract.interactions.providerStates.ProviderStateDTO
import domain.contract.metadata.MetadataDTO
import domain.contract.provider.ProviderDTO

data class ContractModel(
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