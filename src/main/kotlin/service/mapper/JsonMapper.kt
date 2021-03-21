package service.mapper


import com.google.gson.Gson
import java.io.File


class JsonMapper<T> {
    fun getJson(clazz: Class<T>,path:String): T =
        Gson()
            .fromJson(
                File(path).readText(),
                clazz
            )
}