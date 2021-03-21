package domain.contract.interactions.response.matchingRules


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("Content-Type")
    val contentType: ContentType
)