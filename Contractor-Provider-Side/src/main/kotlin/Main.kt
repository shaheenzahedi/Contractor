import org.koin.core.context.startKoin
import presentation.ConsoleColors
import presentation.colorPrint
import service.di.CDCTestGenApplication
import service.mapper.ContractMapper


fun main() {
    val application = CDCTestGenApplication()
    startKoin {
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
    val path = "../sample-contract.json"
    val generalContractPOJO = application.jsonMapper.makeGeneralContract(path)
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
            else -> colorPrint("FAILED", ConsoleColors.RED)
        }
        print("]\n")
        if (!result) {
            println("EXPECTED:")
            println(it.expected)
            println("RECEIVED:")
            println(it.actual)
        }
        println("----------------------------")
    }
//    application.fileResource.write(
//        "src/main/resources/generated_tests/SampleIntegrationTest.java",
//        application.testGenerator.buildJavaTest(model).build().toString()
//    )

}
