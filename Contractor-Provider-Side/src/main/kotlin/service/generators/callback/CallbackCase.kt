package service.generators.callback

data class CallbackCase(
    val callback: TestCaseCallback,
    val expected: String?,
    val actual: String?,
    val name: String
)