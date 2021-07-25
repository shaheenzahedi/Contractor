package domain.ready_to_generate.response

data class ReadyResponseModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
)