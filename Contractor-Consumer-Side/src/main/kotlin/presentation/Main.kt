import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import domain.contractor.Contract
import service.mapper.JsonMapper
import service.stub.StubGenerator
import java.util.*


fun main() {
    val wireMockServer = WireMockServer(options().port(8080)) //No-args constructor will start on port 8080, no HTTPS
    val path = "../sample-contract.json"
    val generalContractPOJO = JsonMapper().getJson(Contract::class.java,path)
    wireMockServer.start()
//    wireMockServer.importStubs(StubGenerator(generalContractPOJO).createAllStubs())
    Scanner(System.`in`).next()
}


