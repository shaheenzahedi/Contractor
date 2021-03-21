package data.contract


import com.google.gson.annotations.SerializedName

data class ProviderState(
    @SerializedName("name")
    val name: String
)