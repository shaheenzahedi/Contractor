package domain.contract.scc

import com.fasterxml.jackson.databind.ObjectMapper


data class ResponseDTO(
    val status: Int?,
    val body: String?,
    val headers: HeadersDTO?,
    val transformers: List<String>?,
){
    val jsonBody: java.util.LinkedHashMap<*, *>?
        get() {
            if (body.isNullOrEmpty())return null
            return ObjectMapper().readValue(body, LinkedHashMap::class.java)
        }
}
