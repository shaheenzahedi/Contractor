package core.domain.contract.pact.interactions


import com.google.gson.annotations.SerializedName

data class MatcherDTO(
    @SerializedName("match")
    val match: String,
    @SerializedName("regex")
    val regex: String
)