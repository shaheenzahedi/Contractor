package service.stub


import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.stubbing.StubImport
import com.github.tomakehurst.wiremock.stubbing.StubImport.stubImport
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.google.gson.Gson
import domain.contractor.Contract
import domain.contractor.Interaction
import kotlin.collections.LinkedHashMap


class StubGenerator(private val contract: Contract) {
    fun createAllStubs(): StubImport {
        val builder = stubImport()
            .ignoreExisting()
            .deleteAllExistingStubsNotInImport()
        contract.interactions?.forEach {
            builder.stub(makeStubWithInteraction(it))
        }
        return builder.build()
    }

    private fun makeStubWithInteraction(interaction: Interaction): StubMapping {
        return decideMappingBuilder(interaction.method, interaction.path)
            .willReturn(
                ok()
                    .withBody(Gson().toJson(interaction.response?.body))
                    .withHeaders(addHeaders(interaction.response?.headers))
                )
            .build()
    }

    private fun addHeaders(headers: LinkedHashMap<String, String>?): HttpHeaders {
        return HttpHeaders(
            headers?.map { HttpHeader(it.key,it.value) }
        )
    }


    private fun decideMappingBuilder(method: String?, path: String?): MappingBuilder {
        return when (HTTPMethods.valueOf(method!!.uppercase())) {
            HTTPMethods.DELETE -> delete(path)
            HTTPMethods.GET -> get(path)
            HTTPMethods.POST -> post(path)
            HTTPMethods.PUT -> put(path)
        }
    }
}