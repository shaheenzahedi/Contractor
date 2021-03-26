package presentation

import domain.contract.ContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication
import service.mapper.JsonMapper
import java.nio.file.Files
import java.nio.file.Paths


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
    Files.writeString(Paths.get("src/main/resources/generated_tests/test.java"),application.integrationTestGenerator.getJavaITBuilder().build().toString())
}