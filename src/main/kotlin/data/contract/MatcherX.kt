package data.contract


import com.google.gson.annotations.SerializedName

data class MatcherX(
    @SerializedName("match")
    val match: String,
    @SerializedName("regex")
    val regex: String
)