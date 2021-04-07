package domain.contract.interactions.response


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.MatchingRules
import domain.contract.interactions.providerStates.ProviderState

data class Response(
    @SerializedName("status")
    val status: Int,
    @SerializedName("body")
    val body: LinkedHashMap<String,Any>,
    @SerializedName("headers")
    val headers: LinkedHashMap<String,Any>,
    @SerializedName("matchingRules")
    val matchingRules: MatchingRules,
    @SerializedName("providerStates")
    val providerStates: List<ProviderState>
)