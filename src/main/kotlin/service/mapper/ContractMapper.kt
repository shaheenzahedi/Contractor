package service.mapper

import domain.contract.GeneralContract


class ContractMapper(private val model: GeneralContract) {

//    fun extreactReadyToTestModel(): List<ReadyToTestModel> {
//        return model.interactionDTOS.map { buildModelWith(it.requestDTO) }
//    }
//
//    private fun buildModelWith(dto: RequestDTO): ReadyToTestModel {
//        return ReadyToTestModel(
//            method = HTTPMethod.valueOf(dto.method),
//            path = dto.path,
//            name = "get_person_from_service_contract",
//            body = dto.query
//        )
//    }
}

