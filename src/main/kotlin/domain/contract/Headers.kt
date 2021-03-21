package domain.contract


import com.google.gson.annotations.SerializedName

data class Headers(
    @SerializedName("Content-Type")
    val contentType: String
)