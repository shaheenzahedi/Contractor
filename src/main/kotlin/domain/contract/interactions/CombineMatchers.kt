package domain.contract.interactions


import com.google.gson.annotations.SerializedName

data class CombineMatchers(
    @SerializedName("combine")
    val combine: String,
    @SerializedName("matchers")
    val matchers: List<Matcher>
)