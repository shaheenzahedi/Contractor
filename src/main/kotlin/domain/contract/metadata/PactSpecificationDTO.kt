package domain.contract.metadata


import com.google.gson.annotations.SerializedName

data class PactSpecificationDTO(
    @SerializedName("version")
    val version: String
)