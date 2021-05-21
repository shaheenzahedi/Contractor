package domain.RTTest

data class ReadyToTestModel(
    val method: HTTPMethod?,
    val url: String?,
    val path: String?,
    val body: LinkedHashMap<String, Any>?
)
