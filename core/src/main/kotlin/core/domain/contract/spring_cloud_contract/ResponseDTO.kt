package core.domain.contract.spring_cloud_contract

import com.fasterxml.jackson.databind.ObjectMapper


data class ResponseDTO(
    val status: Int?,
    val body: LinkedHashMap<String, Any>?,
    val headers: LinkedHashMap<String, String>?,
    val transformers: List<String>?,
)
