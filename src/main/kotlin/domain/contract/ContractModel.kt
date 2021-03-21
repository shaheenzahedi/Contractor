package domain.contract


import com.google.gson.annotations.SerializedName
import domain.contract.consmer.Consumer
import domain.contract.interactions.Interaction
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
    val provider: Provider
)