package presentation

import domain.contract.ContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication
import service.io.FileDialog
import service.io.FileFilter
import java.nio.file.FileSystems
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
            "src/main/resources/contracts/sample-contract3.json"
        )
    val pathToRoot = FileDialog().open("Please select root folder", isDir = true, null)
    val filterFiles = FileFilter().filter(pathToRoot!!, "regex:*repository*.kt")
    filterFiles?.forEach(System.out::println);
    application.fileResource.write(
        "src/main/resources/generated_tests/SampleIntegrationTest.java",
        application.integrationTestGenerator.buildJavaTest().build().toString()
    )

}


