package domain.contract.spring_cloud_contract

import com.fasterxml.jackson.databind.ObjectMapper


data class RequestDTO(
    val url: String,
    val body: String?,
    val headers: LinkedHashMap<String, Any>?,
    val method: String,
    val queryParameters: LinkedHashMap<String, LinkedHashMap<String, Any>>?
) {
    val jsonBody: LinkedHashMap<String, Any>?
        get() {
            if (body.isNullOrEmpty()) return null
            return ObjectMapper().readValue(body, LinkedHashMap::class.java) as LinkedHashMap<String, Any>
        }
}