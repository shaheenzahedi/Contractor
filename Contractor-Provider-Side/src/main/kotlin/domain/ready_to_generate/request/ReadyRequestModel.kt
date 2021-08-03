package domain.ready_to_generate.request

import service.mapper.pact.PredicateModel

data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
    val bodyPredicates: PredicateModel?,
    val headerPredicates: PredicateModel?,
)