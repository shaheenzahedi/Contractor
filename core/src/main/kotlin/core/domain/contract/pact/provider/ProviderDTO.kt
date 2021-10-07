package core.domain.contract.pact.provider


import com.google.gson.annotations.SerializedName

data class ProviderDTO(
    @SerializedName("name")
    val name: String
)