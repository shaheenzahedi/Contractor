package core.domain.contract.pact.interactions


import com.google.gson.annotations.SerializedName

data class MatchingRulesDTO(
    @SerializedName("query")
    val query: LinkedHashMap<String, CombineMatchersDTO>,
    @SerializedName("body")
    val body: LinkedHashMap<String, CombineMatchersDTO>,
    @SerializedName("header")
    val header: LinkedHashMap<String, CombineMatchersDTO>
)