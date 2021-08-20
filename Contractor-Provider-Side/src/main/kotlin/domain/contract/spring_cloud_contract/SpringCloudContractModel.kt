package domain.contract.spring_cloud_contract

import domain.contract.GeneralContract
import domain.contract.SupportedTypes


data class SpringCloudContractModel(
    val id: String?,
    val request: RequestDTO?,
    val response: ResponseDTO?,
    val uuid: String?,

    ) : GeneralContract {
    override val type
        get() = SupportedTypes.SCC

    override val isAllNull
        get() = listOf(
            id,
            request,
            response,
            uuid
        ).all { it == null }
}