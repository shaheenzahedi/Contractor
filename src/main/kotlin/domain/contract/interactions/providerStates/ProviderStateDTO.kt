package domain.contract.interactions.providerStates


import com.google.gson.annotations.SerializedName

data class ProviderStateDTO(
    @SerializedName("name")
    val name: String
)