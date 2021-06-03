package service.mapper

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel
import domain.contract.GeneralContract
import domain.contract.SupportedTypes
import domain.contract.pact.PactContractModel
import domain.contract.pact.interactions.InteractionDTO
import domain.contract.spring_cloud_contract.SpringCloudContractModel


class ContractMapper(private val model: GeneralContract) {

    fun extreactReadyToTestModel(): List<ReadyToTestModel>? {
        return when (model.type) {
            SupportedTypes.PACT -> (model as PactContractModel).interactionDTOS?.map { buildModelWithPact(it) }
            SupportedTypes.SCC -> listOf(buildModelWithSpringCloudContract((model as SpringCloudContractModel)))
        }
    }

    private fun buildModelWithSpringCloudContract(dto: SpringCloudContractModel): ReadyToTestModel {
        return ReadyToTestModel(
            method = dto.request?.method?.let { HTTPMethod.valueOf(it) },
            path = dto.request?.url,
            body = dto.response?.jsonBody,
            status = dto.response?.status,
            headers = dto.response?.headers
        )
    }

    private fun buildModelWithPact(dto: InteractionDTO): ReadyToTestModel {
        return ReadyToTestModel(
            method = HTTPMethod.valueOf(dto.requestDTO.method),
            path = dto.requestDTO.path,
            body = dto.responsedDTO.body,
            status = dto.responsedDTO.status,
            headers = dto.responsedDTO.headers
        )
    }
}

