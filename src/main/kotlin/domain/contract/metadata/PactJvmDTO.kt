package domain.contract.metadata


import com.google.gson.annotations.SerializedName

data class PactJvmDTO(
    @SerializedName("version")
    val version: String
)