package domain.contract.pact.interactions.providerStates


import com.google.gson.annotations.SerializedName

data class ProviderStateDTO(
    @SerializedName("name")
    val name: String
)