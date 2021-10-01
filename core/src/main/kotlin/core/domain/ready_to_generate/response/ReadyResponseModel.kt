package core.domain.ready_to_generate.response

import core.service.mapper.pact.PredicateModel

data class ReadyResponseModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val bodyPredicates: List<PredicateModel>?,
    val headerPredicates: List<PredicateModel>?,
)