package presentation

import service.generators.IntegrationTestGenerator
import service.generators.annotations.JAnnotationGenerator


fun main(args: Array<String>) {
//    val contractModel = JsonMapper<ContractModel>()
//        .getJson(
//            ContractModel::class.java,
//            "src/main/resources/contracts/sample-contract6.json"
//        )
    val x = IntegrationTestGenerator(JAnnotationGenerator()).getJavaITBuilder().build()
    println(x.toString())
}