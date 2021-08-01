package domain.ready_to_generate.response

import service.mapper.pact.PactPredicateModel

data class ReadyResponseModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
    val bodyPredicates: List<PactPredicateModel>?,
    val headerPredicates: PactPredicateModel?,
)