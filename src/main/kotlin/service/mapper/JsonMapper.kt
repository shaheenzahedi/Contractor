package service.mapper


import com.google.gson.Gson
import java.io.File


class JsonMapper<T> {
    fun getJson(clazz: Class<T>, path: String): T = path.asResource { Gson().fromJson(it, clazz) }
    private inline fun String.asResource(work: (String) -> T) = work(getResourceAsText(this))
    private fun getResourceAsText(path: String) = File(path).readText()
}