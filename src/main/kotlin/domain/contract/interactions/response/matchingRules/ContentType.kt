package domain.contract.interactions.response.matchingRules


import com.google.gson.annotations.SerializedName

data class ContentType(
    @SerializedName("combine")
    val combine: String,
    @SerializedName("matchers")
    val matchers: List<MatcherX>
)