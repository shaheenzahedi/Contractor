package domain.contract.scc


data class SpringCloudContractModel (
    val id: String,
    val request: RequestDTO,
    val response: ResponseDTO,
    val uuid: String,
)