package core.service.callback

fun interface TestCaseCallback {
    fun invoke(): Boolean
}