package domain.contract.pact.interactions.request


import com.google.gson.annotations.SerializedName
import domain.contract.pact.interactions.MatchingRulesDTO

data class RequestDTO(
    @SerializedName("method")
    val method: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("query")
    val query: LinkedHashMap<String, Any>,
    @SerializedName("matchingRules")
    val matchingRulesDTO: MatchingRulesDTO,
    @SerializedName("headers")
    val headers: LinkedHashMap<String, Any>,
)