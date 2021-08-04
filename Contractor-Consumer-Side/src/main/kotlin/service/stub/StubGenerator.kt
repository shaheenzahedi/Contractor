package service.stub


import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.stubbing.StubImport
import com.github.tomakehurst.wiremock.stubbing.StubImport.stubImport
import domain.contractor.Contract


class StubGenerator(contract: Contract) {
    fun createAllStubs(): StubImport? {
        return stubImport()
            .stub(get("/one").willReturn(ok().withBody("sssssssssssssssssssssssssssss")))
            .stub(post("/two").willReturn(ok("Body content")))
            .stub(put("/three").willReturn(ok()))
            .ignoreExisting()
            .deleteAllExistingStubsNotInImport().build()
    }
}