package core.domain.ready_to_generate.request


data class ReadyRequestModel(
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val params: LinkedHashMap<String, String>?,
    val cookies: LinkedHashMap<String, String>?,
    val data: Any?
) {
    fun safeGetHeaders(): Map<String, String>? {
        return safeMapGetter(headers)
    }

    fun safeParamsGetter(): Map<String, String>? {
        return safeMapGetter(params)
    }

    fun safeCookiesGetter(): Map<String, String>? {
        return safeMapGetter(cookies)
    }


    private fun safeMapGetter(inputMap: java.util.LinkedHashMap<String, String>?): Map<String, String>? {
        return inputMap?.entries?.associate { it.key to if (it.value !is String) "${it.value}" else it.value }
    }


}