import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import domain.contractor.Contract
import service.mapper.JsonMapper
import service.stub.StubGenerator
import java.util.*


fun main() {
    val port = 8080
    val wireMockServer = WireMockServer(options().port(port)) //No-args constructor will start on port 8080, no HTTPS
    val path = "../sample-contract.json"
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


