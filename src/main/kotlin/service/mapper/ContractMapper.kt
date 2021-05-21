package service.mapper

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel
import domain.contract.GeneralContract
import domain.contract.SupportedTypes
import domain.contract.pact.PactContractModel
import domain.contract.pact.interactions.request.RequestDTO
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import java.lang.IllegalStateException


class ContractMapper(private val model: GeneralContract) {

    fun extreactReadyToTestModel(): List<ReadyToTestModel>? {
        return when (model.type) {
            SupportedTypes.PACT -> (model as PactContractModel).interactionDTOS?.map { buildModelWithPact(it.requestDTO) }
            SupportedTypes.SCC -> listOf(buildModelWithSpringCloudContract((model as SpringCloudContractModel)))
        }
    }

    private fun buildModelWithSpringCloudContract(dto: SpringCloudContractModel): ReadyToTestModel {
        return ReadyToTestModel(
            method = dto.request?.method?.let { HTTPMethod.valueOf(it) },
            path = dto.request?.url,
            body = dto.response?.jsonBody,
            url = dto.request?.url
        )
    }

    private fun buildModelWithPact(dto: RequestDTO): ReadyToTestModel {
        return ReadyToTestModel(
            method = HTTPMethod.valueOf(dto.method),
            path = dto.path,
            body = dto.query,
            url = dto.path
        )
    }
}

