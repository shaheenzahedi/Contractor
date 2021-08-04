import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import domain.contractor.Contract
import service.mapper.JsonMapper
import java.util.*


fun main() {
    val wireMockServer = WireMockServer(options().port(8080)) //No-args constructor will start on port 8080, no HTTPS
    val path = "../sample-contract.json"
    val generalContractPOJO = JsonMapper().getJson(Contract::class.java,path)
    wireMockServer.start()
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/some/thing"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", "text/plain")
                    .withBody("Hello world!")
            ),
    )
    Scanner(System.`in`).next()
}


