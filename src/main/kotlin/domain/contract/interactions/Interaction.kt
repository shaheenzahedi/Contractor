package domain.contract.interactions


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.response.Response

data class Interaction(
    @SerializedName("description")
    val description: String,
    @SerializedName("request")
    val request: Request,
    @SerializedName("response")
    val response: Response
)