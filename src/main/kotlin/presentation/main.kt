package presentation

import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication


fun main(args: Array<String>) {
    val application = CDCTestGenApplication()
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(application.integrationTestJavaModule)
    }
//    val contractModel = JsonMapper<ContractModel>()
//        .getJson(
//            ContractModel::class.java,
//            "src/main/resources/contracts/sample-contract6.json"
//        )
    println(application.integrationTestGenerator.getJavaITBuilder().build())
}