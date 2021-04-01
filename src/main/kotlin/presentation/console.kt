package presentation

import domain.contract.ContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication


fun main(args: Array<String>) {
    val application = CDCTestGenApplication()
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
    val contractModel = application.jsonMapper
        .getJson(
            ContractModel::class.java,
            "src/main/resources/contracts/sample-contract6.json"
        )

    application.fileResource.write(
        "src/main/resources/generated_tests/test.java",
        application.integrationTestGenerator.getJavaBuilder().build().toString()
    )

}