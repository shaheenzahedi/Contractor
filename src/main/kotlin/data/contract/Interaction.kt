package data.contract


import com.google.gson.annotations.SerializedName

data class Interaction(
    @SerializedName("description")
    val description: String,
    @SerializedName("request")
    val request: Request,
    @SerializedName("response")
    val response: Response
)