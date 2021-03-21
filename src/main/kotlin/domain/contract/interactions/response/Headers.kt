package domain.contract.interactions.response


import com.google.gson.annotations.SerializedName

data class Headers(
    @SerializedName("Content-Type")
    val contentType: String
)