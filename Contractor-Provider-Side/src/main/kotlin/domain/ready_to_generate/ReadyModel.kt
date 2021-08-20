package domain.ready_to_generate

import domain.ready_to_generate.request.ReadyRequestModel
import domain.ready_to_generate.response.ReadyResponseModel

data class ReadyToTestModel(
    var baseUrl: String = "http://localhost",
    var port: Int?,
    val method: HTTPMethod,
    val path: String,
    val request: ReadyRequestModel?,
    val response: ReadyResponseModel?,
    val status: Int?,
)
