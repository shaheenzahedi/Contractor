import org.koin.core.context.startKoin
import service.di.ProviderApplication
import core.service.io.FileDialog
import core.service.mapper.GeneralMapper
import presentation.*
import core.service.callback.CallbackMapper
import core.service.mapper.ContractMapper
import javax.swing.filechooser.FileNameExtensionFilter

fun main() {
//    val x = GroovyService().init()
    val application = ProviderApplication()
    startKoin {
        modules(application.integrationTestJavaModule)
        modules(application.fileResourceModule)
    }
    colorPrint("Could not find the contract, select contract file?\n",ConsoleColors.BLUE_BOLD)
    val path = FileDialog().open("Contract JSON or groovy files", false, FileNameExtensionFilter("Contract JSON", "json","groovy"))
    requireNotNull(path) { throw Exception("You need to select a contract in order to continue, closing...") }
    val generalContractPOJO = application.contractMapper.makeGeneralContract(path)
    val model = ContractMapper(generalContractPOJO).extreactReadyToTestModel()
    requireNotNull(model) {
        throw Exception("We could not extract model from the contract, check that you're contract is in standard format")
    }
//    println("Do you want to perform mutation testing?(Y/N)")
//    val input = Scanner(System.`in`).next()
    val mapper = CallbackMapper(model)
    val testResults = TestPresenter(mapper.callbacks()).retrieveSummary()
    val mutationResults = MutationPresenter(mapper.mutations()).retrieveSummary()
    SummaryPresenter(testResults, mutationResults).showSummary()
}
