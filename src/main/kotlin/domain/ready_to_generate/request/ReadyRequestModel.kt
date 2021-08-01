package domain.ready_to_generate.request

import service.mapper.pact.PactPredicateModel

data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
    val bodyPredicates: PactPredicateModel?,
    val headerPredicates: PactPredicateModel?,
)