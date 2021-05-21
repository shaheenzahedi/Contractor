package presentation

import com.fasterxml.jackson.databind.ObjectMapper
import domain.contract.scc.SpringCloudContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication


fun main(args: Array<String>) {
    val application = CDCTestGenApplication()
    startKoin {
        printLogger()
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
    val contractModel = application.jsonMapper
        .getJson(
            SpringCloudContractModel::class.java,
            "src/main/resources/contracts/scc/spring-cloud-sample.json"
        )
//    val pathToRoot = FileDialog().open("Please select root folder", isDir = true, null)
//    requireNotNull(pathToRoot) { throw IllegalStateException("You have to choose the root folder.") }
//    val filterFiles = FileFilter().filter(pathToRoot, "regex:*repository*.kt")
//    filterFiles?.forEach(System.out::println);
//    application.fileResource.write(
//        "src/main/resources/generated_tests/SampleIntegrationTest.java",
//        application.integrationTestGenerator.buildJavaTest(ContractMapper(contractModel).extreactReadyToTestModel()).build().toString()
//    )


}


