package domain.contract.provider


import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("name")
    val name: String
)