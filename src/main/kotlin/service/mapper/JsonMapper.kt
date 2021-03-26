package service.mapper


import com.google.gson.Gson
import service.io.IResource


class JsonMapper(
    private val resource: IResource
) {
    fun <T> getJson(clazz: Class<T>, path: String): T = Gson().fromJson(resource.getResourceAsRawText(path), clazz)
}