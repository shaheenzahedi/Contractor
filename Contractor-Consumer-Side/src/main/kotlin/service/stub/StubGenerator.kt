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
import domain.contractor.Rule
import domain.contractor.RuleType


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
            ).withQueryParams(buildQueryParams(interaction.request?.params, interaction.request?.queryParamRules))
            .build()
    }

    private fun buildQueryParams(
        params: LinkedHashMap<String, String>?,
        queryParamRules: List<Rule>?
    ): MutableMap<String, StringValuePattern> {
        val res = emptyMap<String, StringValuePattern>().toMutableMap()
        if (params.isNullOrEmpty() && queryParamRules.isNullOrEmpty()) return res
        params?.map {
            it.key to equalTo(it.value)
        }?.toMap()?.let(res::putAll)
        queryParamRules?.associate {
            it.name to decideType(it)
        }?.let(res::putAll)
        return res
    }

    private fun decideType(rule: Rule): StringValuePattern {
        return when (rule.getEnumType()) {
            RuleType.EQUALTO -> equalTo(rule.value)
            RuleType.CONTAINS -> containing(rule.value)
            RuleType.MATCHES -> matching(rule.value)
            RuleType.DOESNOTMATCH -> notMatching(rule.value)
        }
    }

    private fun addHeaders(headers: LinkedHashMap<String, String>?): HttpHeaders {
        return HttpHeaders(
            headers?.map { HttpHeader(it.key, it.value) }
        )
    }


    private fun decideMappingBuilder(interaction: Interaction): MappingBuilder {
        val isParamExist = interaction.request?.params == null || interaction.request.params.isEmpty()
        val path = interaction.path
        val pathPattern = urlPathEqualTo(path)
        return when (HTTPMethods.valueOf(interaction.method!!.uppercase())) {
            HTTPMethods.DELETE -> if (isParamExist) delete(path) else delete(pathPattern)
            HTTPMethods.GET -> if (isParamExist) get(path) else get(pathPattern)
            HTTPMethods.POST -> if (isParamExist) post(path) else post(pathPattern)
            HTTPMethods.PUT -> if (isParamExist) put(path) else put(pathPattern)
        }
    }
}