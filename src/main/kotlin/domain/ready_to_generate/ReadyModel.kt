package domain.ready_to_generate

import domain.ready_to_generate.request.ReadyRequestModel

data class ReadyToTestModel(
    val method: HTTPMethod?,
    val path: String?,
    val request:ReadyRequestModel?,
    val response:ReadyRequestModel?,
    val status: Int?,
)
