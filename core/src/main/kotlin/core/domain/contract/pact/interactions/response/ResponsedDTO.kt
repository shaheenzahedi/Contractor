package core.domain.contract.pact.interactions.response


import com.google.gson.annotations.SerializedName
import core.domain.contract.pact.interactions.MatchingRulesDTO
import core.domain.contract.pact.interactions.providerStates.ProviderStateDTO

data class ResponsedDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("body")
    val body: LinkedHashMap<String, Any>,
    @SerializedName("headers")
    val headers: LinkedHashMap<String, String>,
    @SerializedName("matchingRules")
    val matchingRulesDTO: MatchingRulesDTO,
    @SerializedName("responseMatchingRules")
    val responseMatchingRules: LinkedHashMap<String, LinkedHashMap<String, String>>?,
    @SerializedName("providerStates")
    val providerStateDTOS: List<ProviderStateDTO>
)