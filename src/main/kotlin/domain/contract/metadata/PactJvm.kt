package domain.contract.metadata


import com.google.gson.annotations.SerializedName

data class PactJvm(
    @SerializedName("version")
    val version: String
)