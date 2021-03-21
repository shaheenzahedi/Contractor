package domain.contract.interactions


import com.google.gson.annotations.SerializedName

data class MatchingRules(
    @SerializedName("query")
    val query: LinkedHashMap<String,CombineMatchers>,
    @SerializedName("body")
    val body: LinkedHashMap<String,CombineMatchers>,
    @SerializedName("header")
    val header: LinkedHashMap<String,CombineMatchers>
)