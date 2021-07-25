package domain.ready_to_generate

data class ReadyToTestModel(
    val method: HTTPMethod?,
    val path: String?,
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, Any>?,
    val status: Int?,
)
