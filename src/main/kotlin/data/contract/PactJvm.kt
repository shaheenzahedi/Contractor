package data.contract


import com.google.gson.annotations.SerializedName

data class PactJvm(
    @SerializedName("version")
    val version: String
)