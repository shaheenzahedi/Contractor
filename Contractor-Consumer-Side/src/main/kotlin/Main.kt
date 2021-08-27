import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import domain.contractor.Contract
import service.io.FileDialog
import service.mapper.JsonMapper
import service.stub.StubGenerator
import java.util.*
import javax.swing.filechooser.FileNameExtensionFilter


fun main() {
    println("Could not find the contract, select contract file?")
    val path = FileDialog().open("Contract JSON file", false, FileNameExtensionFilter("Contract JSON", "json"))
    requireNotNull(path) { throw Exception("You need to select a contract in order to continue, closing...") }
    val contract = JsonMapper().getJson(Contract::class.java, path)
    val port = contract.port ?: 8080
    val wireMockServer = WireMockServer(
        options().port(port)
            .notifier(ConsoleNotifier(true)).extensions(ResponseTemplateTransformer(true))
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
    Scanner(System.`in`).next()
}


