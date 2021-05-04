package domain.contract.interactions


import com.google.gson.annotations.SerializedName
import domain.contract.interactions.providerStates.ProviderStateDTO
import domain.contract.interactions.request.RequestDTO
import domain.contract.interactions.response.ResponsedDTO

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