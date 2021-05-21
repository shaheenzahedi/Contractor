package domain.contract.scc

import com.fasterxml.jackson.databind.ObjectMapper


data class RequestDTO (
    val url: String,
    val body: String?,
    val headers: HeadersDTO?,
    val method: String,
){
    val jsonBody: java.util.LinkedHashMap<*, *>?
        get() {
            if (body.isNullOrEmpty())return null
            return ObjectMapper().readValue(body, LinkedHashMap::class.java)
        }
}