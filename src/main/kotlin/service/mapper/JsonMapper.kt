package service.mapper


import com.google.gson.Gson
import service.io.Resource


class JsonMapper(
    private val resource: Resource
) {
    fun <T> getJson(clazz: Class<T>, path: String): T =
        Gson().fromJson(resource.getResourceAsRawText(path), clazz)
}