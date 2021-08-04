package service.mapper

import com.google.gson.Gson
import service.io.resource.file.FileResource

class JsonMapper {
    fun <T> getJson(clazz: Class<T>, path: String): T =
        Gson().fromJson(FileResource().getResourceAsRawText(path), clazz)
}