package data


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("combine")
    val combine: String,
    @SerializedName("matchers")
    val matchers: List<Matcher>
)