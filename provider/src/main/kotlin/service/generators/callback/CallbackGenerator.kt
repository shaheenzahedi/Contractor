package service.generators.callback

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.stream.MalformedJsonException
import core.domain.ready_to_generate.ReadyToTestModel
import core.service.mapper.pact.PactPredicateType
import core.service.mapper.pact.PredicateModel
import core.service.mapper.pact.ValueType
import khttp.responses.Response

class CallbackGenerator(
    private val model: ReadyToTestModel
) {

    private val response: Response = HTTPHandler(model).retrieveResponse()

    fun headerTest(): CallbackCase? {
        if (model.response?.headers.isNullOrEmpty()) return null
        return CallbackCase(
            doc = "`${model.method.name}\t${model.path}\n\n",
            name = "Asserts that the response has the desired header",
            callback = { matchesMap(response.headers, model.response?.headers!!) },
            expected = GsonBuilder().setPrettyPrinting().create().toJson(model.response!!.headers),
            actual = GsonBuilder().setPrettyPrinting().create().toJson(response.headers),
            reason = "Response did not include those header defined in contract",
            tagName = "HeaderTest"
        )
    }

    fun bodyTest(): CallbackCase? {
        if (model.response?.body.isNullOrEmpty()) return null
        val parser = JsonParser()
        val response = try {
            parser.parse(response.text).asJsonObject
        } catch (ex: MalformedJsonException) {
            return CallbackCase(
                doc = "`${model.method.name}\t${model.path}\n\n",
                tagName = "BodyTest",
                name = "Asserts that the response has the desired body",
                callback = { false },
                expected = "A valid Json response",
                actual = ex.message,
                reason = "The response is not present in the packet"
            )
        } catch (e: Exception) {
            return CallbackCase(
                doc = "`${model.method.name}\t${model.path}\n\n",
                tagName = "BodyTest",
                name = "Asserts that the response has the desired body",
                callback = { false },
                expected = "A valid Json response",
                actual = "An unexpected error happened ${e.message}",
                reason = "The response is not present in the packet"
            )
        }
        val actual = parser.parse(Gson().toJson(model.response?.body)).asJsonObject
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
        if (model.response?.bodyPredicates.isNullOrEmpty())
            return emptyList()
        val doc = "`${model.method.name}\t${model.path}\n\n"
        return model.response?.bodyPredicates!!.map {
            when (it.type) {
                PactPredicateType.MATCH -> buildBodyPredicateWithMatch(it, doc)
                PactPredicateType.REGEX -> buildBodyPredicateWithRegex(it, doc)
                PactPredicateType.TYPE -> buildBodyPredicateWithType(it, doc)
            }
        }
    }

    private fun buildBodyPredicateWithType(model: PredicateModel, doc: String): CallbackCase {
        val type = model.getValueAsType() ?: return CallbackCase(
            doc = doc,
            tagName = "BodyRuleTypeTest",
            name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
            callback = { false },
            expected = null,
            actual = null,
            reason = "Type `${model.value}` is not one of the predefined types"
        )
        val parser = JsonParser()
        val response = parser.parse(response.text).asJsonObject.get(model.fieldName)
            ?: return CallbackCase(
                doc = doc,
                tagName = "BodyRuleTypeTest",
                name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
                callback = { false },
                expected = null,
                actual = null,
                reason = "The field with name ${model.fieldName} does not exist in response"
            )

        return CallbackCase(
            doc = doc,
            tagName = "BodyRuleTest",
            name = "Asserts that `${model.fieldName}` is type of ${model.value}",
            callback = { typeCheck(response, type) },
            expected = null,
            actual = null,
            reason = "Specified `${model.fieldName}` field does not match the `${model.value}` type"
        )
    }

    private fun typeCheck(response: JsonElement, type: ValueType): Boolean {
        return try {
            when (type) {
                ValueType.SHORT -> response.asShort
                ValueType.JSONOBJECT -> response.asJsonObject
                ValueType.BIGDECIMAL -> response.asBigDecimal
                ValueType.BYTE -> response.asByte
                ValueType.BOOLEAN -> response.asBoolean
                ValueType.BIGINTEGER -> response.asBigInteger
                ValueType.DOUBLE -> response.asDouble
                ValueType.FLOAT -> response.asFloat
                ValueType.INT -> response.asInt
                ValueType.JSONARRAY -> response.asJsonArray
                ValueType.JSONNULL -> response.asJsonNull
                ValueType.LONG -> response.asLong
                ValueType.NUMBER -> response.asNumber
                ValueType.STRING -> response.asString
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun buildBodyPredicateWithRegex(model: PredicateModel, doc: String): CallbackCase {
        val parser = JsonParser()
        val response = parser.parse(response.text).asJsonObject.get(model.fieldName)
            ?: return CallbackCase(
                doc = doc,
                tagName = "BodyRuleRegexTest",
                name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
                callback = { false },
                expected = null,
                actual = null,
                reason = "The field with name ${model.fieldName} is not present in the response body"
            )
        return CallbackCase(
            doc = doc,
            tagName = "BodyRuleTest",
            name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
            callback = { regexCheck(response, model.value) },
            expected = null,
            actual = null,
            reason = "Specified `${model.fieldName}` field does not match the regex"
        )
    }

    private fun regexCheck(response: JsonElement, regex: Any): Boolean {
        if (regex !is String) return false
        val responseAssured = try {
            response.asString
        } catch (e: Exception) {
            return false
        }
        return responseAssured.matches(Regex(regex))
    }

    private fun buildBodyPredicateWithMatch(model: PredicateModel, doc: String): CallbackCase {
        val parser = JsonParser()
        val response = parser.parse(response.text).asJsonObject.get(model.fieldName)
            ?: return CallbackCase(
                doc = doc,
                tagName = "BodyRuleRegexTest",
                name = "Asserts that `${model.fieldName}` is equal to ${model.value}",
                callback = { false },
                expected = null,
                actual = null,
                reason = "The field with name ${model.fieldName} is not present in the response body"
            )
        return CallbackCase(
            doc = doc,
            tagName = "BodyRuleTest",
            name = "Asserts that `${model.fieldName}` is equal to `${model.value}`",
            callback = { matchCheck(response, model.value) },
            expected = model.value.toString(),
            actual = response.toString(),
            reason = "Specified `${model.fieldName}` field does not match the value `${model.value}`"
        )
    }

    private fun matchCheck(response: JsonElement, value: Any): Boolean {
        if (value !is String && value !is Number && value !is Boolean) return false
        if (!response.isJsonPrimitive) return false
        val jsonPrimitive = response.asJsonPrimitive
        return when {
            jsonPrimitive.isNumber -> jsonPrimitive.asNumber == value
            jsonPrimitive.isBoolean -> jsonPrimitive.asBoolean == value
            jsonPrimitive.isString -> jsonPrimitive.asString.equals(value)
            else -> false
        }
    }

    fun generateHeaderRulesTest(): List<CallbackCase?> {
        if (model.response?.headerPredicates.isNullOrEmpty())
            return emptyList()
        val doc = "`${model.method.name}\t${model.path}\n\n"
        return model.response?.headerPredicates!!.map {
            when (it.type) {
                PactPredicateType.MATCH -> buildHeaderPredicateWithMatch(it)
                PactPredicateType.REGEX -> buildHeaderPredicateWithRegex(it)
                PactPredicateType.TYPE -> buildHeaderPredicateWithType(it, doc)
            }
        }
    }

    private fun buildHeaderPredicateWithType(model: PredicateModel, doc: String): CallbackCase? {
        return null
//        val type = model.getValueAsType() ?: return CallbackCase(
//            doc = doc,
//            tagName = "HeaderRuleTypeTest",
//            name = "Asserts that `${model.fieldName}` has the type ${model.value}",
//            callback = { false },
//            expected = null,
//            actual = null,
//            reason = "Type `${model.value}` is not one of the predefined types"
//        )
//        val parser = JsonParser()
//        val response = parser.parse(response.text).asJsonObject.get(model.fieldName)
//            ?: return CallbackCase(
//                doc = doc,
//                tagName = "HeaderRuleTypeTest",
//                name = "Asserts that `${model.fieldName}` validates with the pattern ${model.value}",
//                callback = { false },
//                expected = null,
//                actual = null,
//                reason = "The field with name ${model.fieldName} does not exist in response"
//            )
//
//        return CallbackCase(
//            doc = doc,
//            tagName = "BodyRuleTest",
//            name = "Asserts that `${model.fieldName}` is type of ${model.value}",
//            callback = { typeCheck(response, type) },
//            expected = null,
//            actual = null,
//            reason = "Specified `${model.fieldName}` field does not match the `${model.value}` type"
//        )
    }

    private fun buildHeaderPredicateWithRegex(model: PredicateModel): CallbackCase? {
        return null
    }

    private fun buildHeaderPredicateWithMatch(model: PredicateModel): CallbackCase? {
        return null
    }

    fun statusTest(): CallbackCase? {
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


    private fun matchesMap(map: Map<String, Any>, reference: Map<String, Any>): Boolean {
        return reference.all { (k, v) -> map[k] == v }
    }
}