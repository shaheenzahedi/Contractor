package domain.contract.pact.interactions


import com.google.gson.annotations.SerializedName
import domain.contract.pact.interactions.providerStates.ProviderStateDTO
import domain.contract.pact.interactions.request.RequestDTO
import domain.contract.pact.interactions.response.ResponsedDTO

data class InteractionDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("providerStates")
    val providerStateDTOS: List<ProviderStateDTO>,
    @SerializedName("request")
    val requestDTO: RequestDTO,
    @SerializedName("response")
    val responsedDTO: ResponsedDTO
)