package domain.contract.provider


import com.google.gson.annotations.SerializedName

data class ProviderDTO(
    @SerializedName("name")
    val name: String
)