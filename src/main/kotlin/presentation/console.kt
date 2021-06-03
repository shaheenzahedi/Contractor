package presentation

import domain.contract.pact.PactContractModel
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication
import service.io.FileDialog
import service.io.FileFilter
import service.mapper.ContractMapper


fun main(args: Array<String>) {
    val application = CDCTestGenApplication()
    startKoin {
        printLogger()
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
//    val path = "src/main/resources/contracts/spring_cloud_contract/spring-cloud-sample.json"
    val path = "src/main/resources/contracts/pact/pact-sample.json"
    val contractModel = application.jsonMapper.makeGeneralContract(path)
//    val pathToRoot = FileDialog().open("Please select root folder", isDir = true, null)
//    requireNotNull(pathToRoot) { throw IllegalStateException("You have to choose the root folder.") }
//    val filterFiles = FileFilter().filter(pathToRoot, "regex:*repository*.kt")
//    filterFiles?.forEach(System.out::println)
    application.fileResource.write(
        "src/main/resources/generated_tests/SampleIntegrationTest.java",
        application.integrationTestGenerator.buildJavaTest(ContractMapper(contractModel).extreactReadyToTestModel()!!).build().toString()
    )


}


