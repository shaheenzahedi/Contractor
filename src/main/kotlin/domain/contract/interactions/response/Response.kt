package domain.contract.interactions.response


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.response.matchingRules.MatchingRules

data class Response(
    @SerializedName("body")
    val body: Body,
    @SerializedName("headers")
    val headers: Headers,
    @SerializedName("matchingRules")
    val matchingRules: MatchingRules,
    @SerializedName("providerStates")
    val providerStates: List<ProviderState>,
    @SerializedName("status")
    val status: Int
)