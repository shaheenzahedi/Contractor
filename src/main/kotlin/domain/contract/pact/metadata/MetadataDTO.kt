package domain.contract.pact.metadata


import com.google.gson.annotations.SerializedName

data class MetadataDTO(
    @SerializedName("pact-jvm")
    val pactJvmDTO: PactJvmDTO,
    @SerializedName("pactSpecification")
    val pactSpecificationDTO: PactSpecificationDTO
)