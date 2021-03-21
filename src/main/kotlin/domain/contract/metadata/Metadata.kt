package domain.contract.metadata


import com.google.gson.annotations.SerializedName
import domain.contract.metadata.PactJvm
import domain.contract.metadata.PactSpecification

data class Metadata(
    @SerializedName("pact-jvm")
    val pactJvm: PactJvm,
    @SerializedName("pactSpecification")
    val pactSpecification: PactSpecification
)