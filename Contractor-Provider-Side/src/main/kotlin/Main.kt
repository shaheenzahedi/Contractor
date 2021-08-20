import org.koin.core.context.startKoin
import presentation.CallbackPresenter
import presentation.ConsoleColors
import presentation.SummaryPresenter
import presentation.colorPrint
import service.di.CDCTestGenApplication
import service.io.FileDialog
import service.mapper.ContractMapper
import javax.swing.filechooser.FileNameExtensionFilter


fun main() {
    val application = CDCTestGenApplication()
    startKoin {
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
    colorPrint("Could not find the contract, select contract file?\n",ConsoleColors.BLUE_BOLD)
    val path = FileDialog().open("Contract JSON file", false, FileNameExtensionFilter("Contract JSON", "json"))
    requireNotNull(path) { throw Exception("You need to select a contract in order to continue, closing...") }
    val generalContractPOJO = application.jsonMapper.makeGeneralContract(path)
    val model = ContractMapper(generalContractPOJO).extreactReadyToTestModel()
    requireNotNull(model) {
        throw Exception("We could not extract model from the contract, check that you're contract is in standard format")
    }

    val results = CallbackPresenter(application.callbackMapper.callbacks(model)).retrieveSummary()
    SummaryPresenter(results).showSummary()
}
