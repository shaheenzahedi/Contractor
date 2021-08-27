package service.stub


import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.matching.StringValuePattern
import com.github.tomakehurst.wiremock.stubbing.StubImport
import com.github.tomakehurst.wiremock.stubbing.StubImport.stubImport
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.google.gson.Gson
import domain.contractor.Contract
import domain.contractor.Interaction


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
        return decideMappingBuilder(interaction)
            .willReturn(
                ResponseDefinitionBuilder()
                    .withBody(Gson().toJson(interaction.response?.body))
                    .withHeaders(addHeaders(interaction.response?.headers))
                    .withStatus(interaction.status ?: 200)
            ).withQueryParams(buildQueryParams(interaction.request?.params))
            .build()
    }

    private fun buildQueryParams(params: LinkedHashMap<String, String>?): MutableMap<String, StringValuePattern> {
        if (params == null) return emptyMap<String, StringValuePattern>().toMutableMap()
        return params.map {
            it.key to equalTo(it.value)
        }.toMap().toMutableMap()
    }

    private fun addHeaders(headers: LinkedHashMap<String, String>?): HttpHeaders {
        return HttpHeaders(
            headers?.map { HttpHeader(it.key, it.value) }
        )
    }


    private fun decideMappingBuilder(interaction: Interaction): MappingBuilder {
        val isParamExist = interaction.request?.params == null || interaction.request.params.isEmpty()
        val path = interaction.path
        val pathPattern = urlPathEqualTo(interaction.path)
        return when (HTTPMethods.valueOf(interaction.method!!.uppercase())) {
            HTTPMethods.DELETE -> if (isParamExist) delete(path) else delete(pathPattern)
            HTTPMethods.GET -> if (isParamExist) get(path) else get(pathPattern)
            HTTPMethods.POST -> if (isParamExist) post(path) else post(pathPattern)
            HTTPMethods.PUT -> if (isParamExist) put(path) else put(pathPattern)
        }
    }
}