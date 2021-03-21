package domain.contract


import com.google.gson.annotations.SerializedName

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