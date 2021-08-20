package service.generators.callback

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response
import service.mapper.pact.PactPredicateType
import service.mapper.pact.PredicateModel


class CallbackGenerator(
    private val model: ReadyToTestModel
) {

    private val response: Response

    init {
        val handler = HTTPHandler(model)
        response = handler.retrieveResponse()
    }

    fun headerTest(): CallbackCase? {
        if (model.response?.headers == null) return null
        if (model.response.headers.isEmpty()) return null
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            name = "Asserts that the response has the desired header",
            callback = { matchesMap(response.headers, model.response.headers) },
            expected = GsonBuilder().setPrettyPrinting().create().toJson(model.response.headers),
            actual = GsonBuilder().setPrettyPrinting().create().toJson(response.headers),
            reason = "Response did not include those header defined in contract",
            tagName = "HeaderTest"
        )
    }

    fun bodyTest(): CallbackCase? {
        if (model.response?.body == null) return null
        if (model.response.body.isEmpty()) return null
        val parser = JsonParser()
        val response = parser.parse(response.text).asJsonObject
        val actual = parser.parse(Gson().toJson(model.response.body)).asJsonObject
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            tagName = "BodyTest",
            name = "Asserts that the response has the desired body",
            callback = { response.equals(actual) },
            expected = GsonBuilder().setPrettyPrinting().create().toJson(response),
            actual = GsonBuilder().setPrettyPrinting().create().toJson(actual),
            reason = "Response did not match the body defined in the contract"
        )
    }

    fun generateBodyRulesTest(): List<CallbackCase?> {
        if (model.response?.bodyPredicates == null || model.response.bodyPredicates.isEmpty())
            return emptyList()
        val doc = "`${model.method.name}\t${model.path}\n\n"
        return model.response.bodyPredicates.map {
            when (it.type) {
                PactPredicateType.MATCH -> buildBodyPredicateWithMatch(it, doc)
                PactPredicateType.REGEX -> buildBodyPredicateWithRegex(it, doc)
            }
        }
    }

    private fun buildBodyPredicateWithRegex(model: PredicateModel, doc: String): CallbackCase {
        return CallbackCase(
            doc = doc,
            tagName = "BodyRuleTest",
            name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
            callback = { false },
            expected = null,
            actual = null,
            reason = "Specified `${model.fieldName}` field does not match the regex"
        )
    }

    private fun buildBodyPredicateWithMatch(model: PredicateModel, doc: String): CallbackCase? {
        return null
    }

    fun generateHeaderRulesTest(): List<CallbackCase?> {
        if (model.response?.headerPredicates == null || model.response.headerPredicates.isEmpty())
            return emptyList()
        return model.response.headerPredicates.map {
            when (it.type) {
                PactPredicateType.MATCH -> buildHeaderPredicateWithMatch(it)
                PactPredicateType.REGEX -> buildHeaderPredicateWithRegex(it)
            }
        }
    }

    private fun buildHeaderPredicateWithRegex(model: PredicateModel): CallbackCase? {
        return null
    }

    private fun buildHeaderPredicateWithMatch(model: PredicateModel): CallbackCase? {
        return null
    }

    fun generateStatusTest(): CallbackCase? {
        if (model.status == null) return null
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            name = "Assert if the status equals to ${model.status}",
            callback = { response.statusCode == model.status },
            expected = model.status.toString(),
            actual = response.statusCode.toString(),
            reason = "Status is not ${model.status} as defined in the contract",
            tagName = "StatusTest"
        )
    }


    private fun matchesMap(map1: Map<String, Any>, reference: Map<String, Any>): Boolean {
        return reference.all { (k, v) -> map1[k] == v }
    }
}