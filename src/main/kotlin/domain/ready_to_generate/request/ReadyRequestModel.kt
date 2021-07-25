package domain.ready_to_generate.request

data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
)