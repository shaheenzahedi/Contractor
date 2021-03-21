package data


import com.google.gson.annotations.SerializedName

data class PactSpecification(
    @SerializedName("version")
    val version: String
)