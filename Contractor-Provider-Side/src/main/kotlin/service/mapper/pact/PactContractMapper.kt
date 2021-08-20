package service.mapper.pact

import domain.contract.pact.interactions.InteractionDTO


class PactContractMapper(
    private val interaction: InteractionDTO
) {

    fun getRequestBodyPredicates(): PredicateModel? {
        return null
    }

    fun getRequestHeaderPredicates(): PredicateModel? {
        return null
    }

//    fun getResponseHeaderPredicates(): List<PredicateModel>? {
//        val rules = interaction.responsedDTO.responseMatchingRules
//        if (rules.isNullOrEmpty()) return null
//        return rules.map {
//            val value = it.value.entries.first()
//            val type = PactPredicateType.valueOf(value.key.toUpperCase())
//            val name = extractName(it.key, "body")
//            PredicateModel(
//                fieldName = name,
//                type = type,
//                value = if (type == PactPredicateType.MATCH) detectResponseType(name) else value.value
//            )
//        }
//    }
//
//    fun getResponseBodyPredicates(): List<PredicateModel>? {
//        val rules = interaction.responsedDTO.responseMatchingRules
//        if (rules.isNullOrEmpty()) return null
//        return rules.map {
//            val value = it.value.entries.first()
//            val type = PactPredicateType.valueOf(value.key.toUpperCase())
//            val name = extractName(it.key, "body")
//            PredicateModel(
//                fieldName = name,
//                type = type,
//                value = if (type == PactPredicateType.MATCH) detectResponseType(name) else value.value
//            )
//        }
//    }

    private fun detectResponseType(name: String?): String? {
        if (name == null) return null
        val value = checkNotNull(interaction.responsedDTO.body.get(name)) { return null }
        return value.javaClass.simpleName
    }

    private fun extractName(fieldName: String, type: String): String? {
        val prefix = "$.${type}."
        if (!fieldName.startsWith(prefix)) return null
        return fieldName.substring(startIndex = prefix.length)
    }
}