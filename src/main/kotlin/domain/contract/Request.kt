package domain.contract


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("method")
    val method: String,
    @SerializedName("path")
    val path: String
)