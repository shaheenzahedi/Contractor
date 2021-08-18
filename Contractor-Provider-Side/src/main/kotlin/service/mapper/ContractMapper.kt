package service.mapper

import domain.contract.GeneralContract
import domain.contract.SupportedTypes
import domain.contract.contractor.Contract
import domain.contract.contractor.Interaction
import domain.contract.contractor.Rule
import domain.contract.pact.PactContractModel
import domain.contract.pact.interactions.InteractionDTO
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import domain.ready_to_generate.HTTPMethod
import domain.ready_to_generate.ReadyToTestModel
import domain.ready_to_generate.request.ReadyRequestModel
import domain.ready_to_generate.response.ReadyResponseModel
import service.mapper.pact.PactContractMapper
import service.mapper.pact.PactPredicateType
import service.mapper.pact.PredicateModel


class ContractMapper(
    private val model: GeneralContract,
) {

    fun extreactReadyToTestModel(): List<ReadyToTestModel>? {
        return when (model.type) {
            SupportedTypes.CONTRACTOR -> (model as Contract).interactions?.map { buildModelWithContractor(it) }
            SupportedTypes.PACT -> (model as PactContractModel).interactionDTOS?.map { buildModelWithPact(it) }
            SupportedTypes.SCC -> listOf(buildModelWithSpringCloudContract((model as SpringCloudContractModel)))
        }
    }

    private fun buildModelWithContractor(dto: Interaction): ReadyToTestModel {
        return ReadyToTestModel(
            method = dto.method?.let { HTTPMethod.valueOf(it) },
            path = dto.path,
            status = dto.status,
            request = ReadyRequestModel(
                body = dto.request?.body,
                headers = dto.request?.headers,
                bodyPredicates = null,
                headerPredicates = null
            ),
            response = ReadyResponseModel(
                body = dto.response?.body,
                headers = dto.response?.headers,
                bodyPredicates = dto.response?.bodyRules?.map { mapToBodyPredicate(it) },
                headerPredicates = dto.response?.headerRules?.map { mapToBodyPredicate(it) },
            )
        )
    }

    private fun mapToBodyPredicate(rule: Rule) = PredicateModel(
        rule.name,
        PactPredicateType.valueOf(rule.type!!.uppercase()),
        rule.value
    )

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
                bodyPredicates = pactMapper.getResponseBodyPredicates(),
                headerPredicates = pactMapper.getResponseHeaderPredicates(),
            ),
            request = ReadyRequestModel(
                body = dto.requestDTO.query,
                headers = dto.requestDTO.headers,
                bodyPredicates = pactMapper.getRequestBodyPredicates(),
                headerPredicates = pactMapper.getRequestHeaderPredicates(),
            )
        )
    }
}

