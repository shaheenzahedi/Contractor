package service.stub


import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.matching.StringValuePattern
import com.github.tomakehurst.wiremock.stubbing.StubImport
import com.github.tomakehurst.wiremock.stubbing.StubImport.stubImport
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

    private fun addHeaders(headers: LinkedHashMap<String, String>?): HttpHeaders {
        return HttpHeaders(headers?.map { HttpHeader(it.key, it.value) })
    }


    private fun makeStubWithInteraction(interaction: Interaction) =
        decideMappingBuilder(interaction)
            .willReturn(
                ResponseDefinitionBuilder()
                    .withBody(Gson().toJson(interaction.response?.body))
                    .withHeaders(addHeaders(interaction.response?.headers))
                    .withStatus(interaction.status ?: 200)
            ).withQueryParams(buildQueryParams(interaction.request?.params, interaction.request?.paramRules))
            .withHeaders(interaction.request?.headers, interaction.request?.headerParams)
            .withCookies(interaction.request?.cookies, interaction.request?.cookieParams)
            .build()


    private fun decideMappingBuilder(interaction: Interaction): MappingBuilder {
        val useExactPath =
            interaction.request?.params.isNullOrEmpty() && interaction.request?.paramRules.isNullOrEmpty()
        val path = interaction.path
        val pathPattern = urlPathEqualTo(path)
        return when (HTTPMethods.valueOf(interaction.method!!.uppercase())) {
            HTTPMethods.DELETE -> if (useExactPath) delete(path) else delete(pathPattern)
            HTTPMethods.GET -> if (useExactPath) get(path) else get(pathPattern)
            HTTPMethods.POST -> if (useExactPath) post(path) else post(pathPattern)
            HTTPMethods.PUT -> if (useExactPath) put(path) else put(pathPattern)
        }
    }

}

private fun MappingBuilder.withCookies(
    cookies: java.util.LinkedHashMap<String, String>?,
    cookieParams: List<Rule>?
): MappingBuilder {
    if (cookies.isNullOrEmpty() && cookieParams.isNullOrEmpty()) return this
    cookies?.forEach {
        withCookie(it.key, equalTo(it.value))
    }
    cookieParams?.forEach {
        withCookie(it.name, decideType(it))
    }
    return this
}

private fun MappingBuilder.withHeaders(
    headers: LinkedHashMap<String, String>?,
    headerParams: List<Rule>?
): MappingBuilder {
    if (headers.isNullOrEmpty() && headerParams.isNullOrEmpty()) return this
    headers?.forEach {
        withHeader(it.key, equalTo(it.value))
    }
    headerParams?.forEach {
        withHeader(it.name, decideType(it))
    }
    return this
}

private fun decideType(rule: Rule) =
    when (rule.getEnumType()) {
        RuleType.EQUALTO -> equalTo(rule.value)
        RuleType.CONTAINS -> containing(rule.value)
        RuleType.MATCHES -> matching(rule.value)
        RuleType.DOESNOTMATCH -> notMatching(rule.value)
    }