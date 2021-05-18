package presentation

import domain.contract.ContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication
import service.mapper.ContractMapper


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
//    val pathToRoot = FileDialog().open("Please select root folder", isDir = true, null)
//    requireNotNull(pathToRoot) { throw IllegalStateException("You have to choose the root folder.") }
//    val filterFiles = FileFilter().filter(pathToRoot, "regex:*repository*.kt")
//    filterFiles?.forEach(System.out::println);
    application.fileResource.write(
        "src/main/resources/generated_tests/SampleIntegrationTest.java",
        application.integrationTestGenerator.buildJavaTest(ContractMapper(contractModel).extreactReadyToTestModel()).build().toString()
    )

}


