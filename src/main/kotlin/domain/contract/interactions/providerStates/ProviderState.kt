package domain.contract.interactions.providerStates


import com.google.gson.annotations.SerializedName

data class ProviderState(
    @SerializedName("name")
    val name: String
)