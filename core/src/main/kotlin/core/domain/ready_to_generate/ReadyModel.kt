package core.domain.ready_to_generate

import core.domain.ready_to_generate.request.ReadyRequestModel
import core.domain.ready_to_generate.response.ReadyResponseModel

data class ReadyToTestModel(
    var mutationMetaData: MutationMetaData?,
    var baseUrl: String = "http://localhost",
    var port: Int?,
    val method: HTTPMethod,
    val path: String,
    val request: ReadyRequestModel?,
    val response: ReadyResponseModel?,
    val status: Int?,
)

data class MutationMetaData(
    val name: String,
    val type: MutationType
)

enum class MutationType {
    STATUS,
    HTTP_METHOD,
    REQUEST_HEADER,
    RESPONSE_HEADER,
    REQUEST_BODY,
    RESPONSE_BODY,
    REQUEST_PARAMS,
    RESPONSE_PARAMS,
    REQUEST_COOKIES
}
