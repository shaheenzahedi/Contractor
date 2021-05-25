package domain.contract.pact.interactions


import com.google.gson.annotations.SerializedName

data class CombineMatchersDTO(
    @SerializedName("combine")
    val combine: String,
    @SerializedName("matchers")
    val matcherDTOS: List<MatcherDTO>
)