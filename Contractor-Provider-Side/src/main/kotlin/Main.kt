import org.koin.core.context.startKoin
import presentation.CallbackPresenter
import presentation.SummaryPresenter
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

    val results = CallbackPresenter(application.callbackMapper.callbacks(model)).retrieveSummary()
    SummaryPresenter(results).showSummary()
}
