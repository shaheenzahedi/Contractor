package domain.contract


import com.google.gson.annotations.SerializedName
import domain.contract.consumer.Consumer
import domain.contract.interactions.Interaction
import domain.contract.interactions.providerStates.ProviderState
import domain.contract.metadata.Metadata
import domain.contract.provider.Provider

data class ContractModel(
    @SerializedName("consumer")
    val consumer: Consumer,
    @SerializedName("interactions")
    val interactions: List<Interaction>,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("providerStates")
    val providerStates: List<ProviderState>
)