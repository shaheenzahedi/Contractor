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
        return null
    }

    fun getResponseBodyPredicates(): PactPredicateModel? {
        return null
    }
}