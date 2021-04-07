package domain.contract.metadata


import com.google.gson.annotations.SerializedName

data class PactSpecification(
    @SerializedName("version")
    val version: String
)