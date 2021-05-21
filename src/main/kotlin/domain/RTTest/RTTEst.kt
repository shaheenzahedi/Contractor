package domain.RTTest

data class ReadyToTestModel(
    val method: HTTPMethod,
    val name: String,
    val path: String,
    val body: HashMap<String, Any>
)
