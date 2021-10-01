package service.generators.callback

import core.domain.ready_to_generate.HTTPMethod
import core.domain.ready_to_generate.ReadyToTestModel
import khttp.responses.Response
import service.generators.callback.path.PathGenerate

class HTTPHandler(private val model: ReadyToTestModel) {
    fun retrieveResponse(): Response {
        val pathToCall = PathGenerate(model.baseUrl, model.port, model.path).retrieveFullPath()
        val response = performCall(pathToCall)
        println("------\t Executing tests on ${model.baseUrl} \t------")
        Thread.sleep(1000)
        return response
    }

    private fun performCall(path: String): Response {

        return when (model.method) {
            HTTPMethod.PUT -> khttp.put(
                url = path,
                headers = model.request?.headers ?: mapOf(),
                params = model.request?.params ?: mapOf(),
                cookies = model.request?.cookies ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.POST -> khttp.post(
                url = path,
                headers = model.request?.headers ?: mapOf(),
                params = model.request?.params ?: mapOf(),
                cookies = model.request?.cookies ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.GET -> khttp.get(
                url = path,
                headers = model.request?.headers ?: mapOf(),
                params = model.request?.params ?: mapOf(),
                cookies = model.request?.cookies ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.DELETE -> khttp.delete(
                url = path,
                headers = model.request?.headers ?: mapOf(),
                params = model.request?.params ?: mapOf(),
                cookies = model.request?.cookies ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.PATCH -> khttp.patch(
                url = path,
                headers = model.request?.headers ?: mapOf(),
                params = model.request?.params ?: mapOf(),
                cookies = model.request?.cookies ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
        }
    }
}