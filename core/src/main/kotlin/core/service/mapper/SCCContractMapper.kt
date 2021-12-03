package core.service.mapper

import core.domain.contract.spring_cloud_contract.RequestDTO
import core.domain.contract.spring_cloud_contract.ResponseDTO
import core.domain.contract.spring_cloud_contract.SpringCloudContractModel
import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.Header
import org.springframework.cloud.contract.spec.internal.QueryParameters

class SCCContractMapper {
    fun fromGroovy(contract: Contract): SpringCloudContractModel {
        return SpringCloudContractModel(
            id = contract.name,
            uuid = null,
            request = RequestDTO(
                url = contract.request.url.clientValue.toString(),
                body = contract.request.body?.clientValue as LinkedHashMap<String, Any>?,
                headers = getHeaders(contract.request.headers?.entries),
                method = contract.request.method.clientValue.toString(),
                queryParameters = getQueryParams(contract.request.url.queryParameters),//contract.request
            ),
            response = ResponseDTO(
                status = contract.response.status.clientValue as Int,
                body = contract.response.body?.clientValue as LinkedHashMap<String, Any>?,
                headers = getHeaders(contract.response.headers.entries),
                transformers = null//contract.response.
            )
        )
    }

    private fun getQueryParams(queryParameters: QueryParameters?): LinkedHashMap<String, LinkedHashMap<String, Any>>? {
        return null
    }

    private fun getHeaders(entries: MutableSet<Header>?): LinkedHashMap<String, String> {
        return entries?.toList()?.associate { it.name to it.clientValue.toString() } as LinkedHashMap<String, String>
    }
}