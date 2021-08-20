import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import domain.contractor.Contract
import service.io.FileDialog
import service.mapper.JsonMapper
import service.stub.StubGenerator
import java.util.*
import javax.swing.filechooser.FileNameExtensionFilter


fun main() {
    val port = 8080
    val wireMockServer = WireMockServer(options().port(port)) //No-args constructor will start on port 8080, no HTTPS
    val path = FileDialog().open("Contract JSON file", false, FileNameExtensionFilter("Contract JSON", "json"))
    requireNotNull(path) { throw Exception("You need to select a contract in order to continue, closing...") }
    val generalContractPOJO = JsonMapper().getJson(Contract::class.java,path)
    wireMockServer.start()
    wireMockServer.importStubs(StubGenerator(generalContractPOJO).createAllStubs())
    println("""
Mock server is running! Access URLs:
Local:      http://localhost:$port/
External:   http://127.0.1.1:$port/
Press CTRL+C to stop
    """.trimIndent())
    Scanner(System.`in`).next()
}


