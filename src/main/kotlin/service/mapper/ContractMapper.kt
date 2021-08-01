package service.mapper

import domain.ready_to_generate.HTTPMethod
import domain.ready_to_generate.ReadyToTestModel
import domain.contract.GeneralContract
import domain.contract.SupportedTypes
import domain.contract.pact.PactContractModel
import domain.contract.pact.interactions.InteractionDTO
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import domain.ready_to_generate.request.ReadyRequestModel
import domain.ready_to_generate.response.ReadyResponseModel
import service.mapper.pact.PactContractMapper


class ContractMapper(
    private val model: GeneralContract,
) {

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
            status = dto.response?.status,
            response = ReadyResponseModel(
                body = dto.response?.jsonBody,
                headers = dto.response?.headers,
                bodyPredicates = null,
                headerPredicates = null
            ),
            request = ReadyRequestModel(
                body = dto.request?.jsonBody,
                headers = dto.request?.headers,
                bodyPredicates = null,
                headerPredicates = null
            )

        )
    }

    private fun buildModelWithPact(dto: InteractionDTO): ReadyToTestModel {
        val pactMapper = PactContractMapper(dto)
        return ReadyToTestModel(
            method = HTTPMethod.valueOf(dto.requestDTO.method),
            path = dto.requestDTO.path,
            status = dto.responsedDTO.status,
            response = ReadyResponseModel(
                body = dto.responsedDTO.body,
                headers = dto.responsedDTO.headers,
                bodyPredicates = pactMapper.getResponseBodyPredicates() ,
                headerPredicates = pactMapper.getResponseHeaderPredicates() ,
            ),
            request = ReadyRequestModel(
                body = dto.requestDTO.query,
                headers = dto.requestDTO.headers,
                bodyPredicates = pactMapper.getRequestBodyPredicates() ,
                headerPredicates = pactMapper.getRequestHeaderPredicates() ,
            )
        )
    }
}

