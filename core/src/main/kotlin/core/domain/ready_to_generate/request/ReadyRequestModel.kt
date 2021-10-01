package core.domain.ready_to_generate.request


data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val params: LinkedHashMap<String, String>?,
    val cookies: LinkedHashMap<String, String>?,
    val data: Any?
) {
}