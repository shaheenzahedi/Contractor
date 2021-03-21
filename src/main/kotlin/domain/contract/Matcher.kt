package domain.contract


import com.google.gson.annotations.SerializedName

data class Matcher(
    @SerializedName("match")
    val match: String
)