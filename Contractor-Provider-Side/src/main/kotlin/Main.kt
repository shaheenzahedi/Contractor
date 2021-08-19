import domain.presentation.ConsoleColors
import domain.presentation.colorPrint
import org.koin.core.context.startKoin
import service.di.CDCTestGenApplication
import service.mapper.ContractMapper


fun main() {
    val application = CDCTestGenApplication()
    startKoin {
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
//    val path = "src/test/resources/contracts/spring_cloud_contract/scc_complex_query_params.json"
    val path = "../sample-contract.json"
    val generalContractPOJO = application.jsonMapper.makeGeneralContract(path)
//    val pathToRoot = FileDialog().open("Please select root folder", isDir = true, null)
//    requireNotNull(pathToRoot) { throw IllegalStateException("You have to choose the root folder.") }
//    val filterFiles = FileFilter().filter(pathToRoot, "regex:*repository*.kt")
//    filterFiles?.forEach(System.out::println)
    val model = ContractMapper(generalContractPOJO).extreactReadyToTestModel()
    requireNotNull(model) {
        throw Exception(
            "We could not extract model from the contract, " +
                    "check that you're contract is in standard format"
        )
    }

    application.callbackMapper.callbacks(model).forEach {
        val result = it.callback.invoke()
        print("${it.name}\t[")
        when {
            result -> colorPrint("SUCCESS", ConsoleColors.GREEN_BOLD)
            else -> colorPrint("FAILURE", ConsoleColors.RED)
        }
        print("]\n")
        println("----------------------------")
    }
//    application.fileResource.write(
//        "src/main/resources/generated_tests/SampleIntegrationTest.java",
//        application.testGenerator.buildJavaTest(model).build().toString()
//    )

}
