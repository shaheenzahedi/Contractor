package domain.contract.pact.interactions.response


import com.google.gson.annotations.SerializedName
import domain.contract.pact.interactions.MatchingRulesDTO
import domain.contract.pact.interactions.providerStates.ProviderStateDTO

data class ResponsedDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("body")
    val body: LinkedHashMap<String,Any>,
    @SerializedName("headers")
    val headers: LinkedHashMap<String,Any>,
    @SerializedName("matchingRules")
    val matchingRulesDTO: MatchingRulesDTO,
    @SerializedName("providerStates")
    val providerStateDTOS: List<ProviderStateDTO>
)