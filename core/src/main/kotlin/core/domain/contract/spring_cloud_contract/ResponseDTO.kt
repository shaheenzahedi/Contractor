package core.domain.contract.spring_cloud_contract

import com.fasterxml.jackson.databind.ObjectMapper


data class ResponseDTO(
    val status: Int?,
    val body: String?,
    val headers: LinkedHashMap<String, String>?,
    val transformers: List<String>?,
) {
    val jsonBody: LinkedHashMap<String, Any>?
        get() {
            if (body.isNullOrEmpty()) return null
            return ObjectMapper().readValue(body, LinkedHashMap::class.java) as LinkedHashMap<String, Any>
        }
}
