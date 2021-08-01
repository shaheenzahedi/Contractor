package service.mapper.pact

import domain.contract.pact.interactions.InteractionDTO


class PactContractMapper(
    private val interaction: InteractionDTO
) {

    fun getRequestBodyPredicates(): PactPredicateModel? {
        return null
    }

    fun getRequestHeaderPredicates(): PactPredicateModel? {
        return null
    }

    fun getResponseHeaderPredicates(): PactPredicateModel? {
        val rules = interaction.responsedDTO.responseMatchingRules
        if (rules.isNullOrEmpty()) return null
        return null
    }

    fun getResponseBodyPredicates(): List<PactPredicateModel>? {
        val rules = interaction.responsedDTO.responseMatchingRules
        if (rules.isNullOrEmpty()) return null
        return rules.map {
            val value = it.value.entries.first()
            PactPredicateModel(
                fieldName = extractName(it.key, "body"),
                type = PactPredicateType.valueOf(value.key.toUpperCase()),
                value = value.value
            )
        }
    }

    private fun extractName(fieldName: String, type: String): String? {
        val prefix = "$.${type}."
        if (!fieldName.startsWith(prefix)) return null
        return fieldName.substring(startIndex = prefix.length)
    }
}