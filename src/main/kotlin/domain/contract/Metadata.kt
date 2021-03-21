package domain.contract


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("pact-jvm")
    val pactJvm: PactJvm,
    @SerializedName("pactSpecification")
    val pactSpecification: PactSpecification
)