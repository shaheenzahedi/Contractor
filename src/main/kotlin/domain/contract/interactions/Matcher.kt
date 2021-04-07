package domain.contract.interactions


import com.google.gson.annotations.SerializedName

data class Matcher(
    @SerializedName("match")
    val match: String,
    @SerializedName("regex")
    val regex: String
)