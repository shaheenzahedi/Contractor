package core.domain.contract.spring_cloud_contract

import com.fasterxml.jackson.databind.ObjectMapper


data class RequestDTO(
    val url: String,
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val method: String,
    val queryParameters: LinkedHashMap<String, LinkedHashMap<String, Any>>?
)