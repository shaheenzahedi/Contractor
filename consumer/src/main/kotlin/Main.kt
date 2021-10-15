import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import core.domain.contract.contractor.Contract
import core.service.io.FileDialog
import core.service.io.resource.file.FileResource
import core.service.mapper.ContractMapper
import core.service.mapper.JsonMapper
import core.service.callback.CallbackMapper
import service.stub.StubGenerator
import java.util.*
import javax.swing.filechooser.FileNameExtensionFilter


fun main() {
    println("Could not find the contract, select contract file?")
    val path = FileDialog().open("Contract JSON file", false, FileNameExtensionFilter("Contract JSON", "json"))
    requireNotNull(path) { throw Exception("You need to select a contract in order to continue, closing...") }
    val contract = JsonMapper(FileResource()).getJson(Contract::class.java, path)
    val port = contract.port ?: 8080
    val wireMockServer = WireMockServer(
        options().port(port).notifier(ConsoleNotifier(true)).extensions(ResponseTemplateTransformer(true))
    )
    wireMockServer.start()
    wireMockServer.importStubs(StubGenerator(contract).createAllStubs())
    println(
        """
        Mock server is running! Access URLs:
        Local:      http://localhost:$port/
        External:   http://127.0.1.1:$port/
        Press CTRL+C to stop
    """.trimIndent()
    )
    val model = ContractMapper(contract).extreactReadyToTestModel()
    requireNotNull(model) {
        throw Exception("We could not extract model from the contract, check that you're contract is in standard format")
    }
    val mapper = CallbackMapper(model)
mapper.callbacks().forEach { println(it.callback.invoke()) }
    Scanner(System.`in`).next()
}


