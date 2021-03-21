package presentation

import domain.contract.ContractModel
import service.mapper.JsonMapper

fun main(args: Array<String>) {
    val x = JsonMapper<ContractModel>()
        .getJson(ContractModel::class.java,
            "src/main/resources/contracts/sample-contract1.json")
    println(x.interactions)
}