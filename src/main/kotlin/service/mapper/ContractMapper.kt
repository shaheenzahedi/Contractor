package service.mapper

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel
import domain.contract.pact.PactContractModel
import domain.contract.pact.interactions.request.RequestDTO


class ContractMapper(private val model: PactContractModel) {
    fun extreactReadyToTestModel(): List<ReadyToTestModel> {
        return model.interactionDTOS.map { buildModelWith(it.requestDTO) }
    }

    private fun buildModelWith(dto: RequestDTO): ReadyToTestModel {
        return ReadyToTestModel(
            method = HTTPMethod.valueOf(dto.method),
            path = dto.path,
            name = "get_person_from_service_contract",
            body = dto.query
        )
    }
}

