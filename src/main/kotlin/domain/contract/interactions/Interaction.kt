package domain.contract.interactions


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.providerStates.ProviderState
import domain.contract.interactions.request.Request
import domain.contract.interactions.response.Response

data class Interaction(
    @SerializedName("description")
    val description: String,
    @SerializedName("providerStates")
    val providerStates: List<ProviderState>,
    @SerializedName("request")
    val request: Request,
    @SerializedName("response")
    val response: Response
)