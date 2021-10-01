package core.domain.contract.pact.metadata


import com.google.gson.annotations.SerializedName

data class PactJvmDTO(
    @SerializedName("version")
    val version: String
)