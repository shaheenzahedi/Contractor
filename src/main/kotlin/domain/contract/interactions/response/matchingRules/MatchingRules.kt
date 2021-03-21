package domain.contract.interactions.response.matchingRules


import com.google.gson.annotations.SerializedName

data class MatchingRules(
    @SerializedName("body")
    val body: BodyX,
    @SerializedName("header")
    val header: Header
)