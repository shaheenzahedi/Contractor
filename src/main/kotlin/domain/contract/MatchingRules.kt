package domain.contract


import com.google.gson.annotations.SerializedName

data class MatchingRules(
    @SerializedName("body")
    val body: BodyX,
    @SerializedName("header")
    val header: Header
)