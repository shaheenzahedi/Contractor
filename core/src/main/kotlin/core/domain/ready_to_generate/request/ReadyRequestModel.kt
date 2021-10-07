package core.domain.ready_to_generate.request


data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val params: LinkedHashMap<String, String>?,
    val cookies: LinkedHashMap<String, String>?,
    val data: Any?
) {
    fun safeGetHeaders(): Map<String, String>? {
        return headers?.entries?.associate { it.key to if (it.value !is String) "${it.value}" else it.value }
    }

}