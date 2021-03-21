package domain.contract.interactions.request


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.MatchingRules

data class Request(
    @SerializedName("method")
    val method: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("query")
    val query: LinkedHashMap<String,Any>,
    @SerializedName("matchingRules")
    val matchingRules: MatchingRules
)