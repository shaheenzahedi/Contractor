package core.service.callback

import core.domain.ready_to_generate.HTTPMethod
import core.domain.ready_to_generate.ReadyToTestModel
import khttp.*
import khttp.responses.Response
import service.generators.callback.path.PathGenerate
import kotlin.system.exitProcess

class HTTPHandler(private val model: ReadyToTestModel) {
    fun retrieveResponse(isMutation: Boolean): Response {
        val pathToCall = PathGenerate(model.baseUrl, model.port, model.path).retrieveFullPath()
        val response = try {
            performCall(pathToCall)
        } catch (e: Exception) {
            println("There was an error making the request {${e.message}}")
            exitProcess(0)
        }
        if (!isMutation) println("------\t Executing tests on $pathToCall \t------")
        return response
    }

    private fun performCall(path: String): Response {
        return when (model.method) {
            HTTPMethod.PUT -> put(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.POST -> post(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.GET -> get(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.DELETE -> delete(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.PATCH -> patch(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
            HTTPMethod.OPTIONS -> options(
                url = path,
                headers = model.request?.safeGetHeaders() ?: mapOf(),
                params = model.request?.safeParamsGetter() ?: mapOf(),
                cookies = model.request?.safeCookiesGetter() ?: mapOf(),
                json = model.request?.body,
                data = model.request?.data
            )
        }
    }
}