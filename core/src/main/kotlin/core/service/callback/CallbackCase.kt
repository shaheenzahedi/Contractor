package core.service.callback

data class CallbackCase(
    val callback: TestCaseCallback,
    val expected: String?,
    val actual: String?,
    val name: String,
    val doc: String,
    val reason: String?,
    val tagName:String,
    val mutationName:String?
)