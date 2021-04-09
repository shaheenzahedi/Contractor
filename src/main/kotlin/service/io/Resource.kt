package service.io

import java.nio.file.Path

interface Resource {
    fun getResourceAsRawText(path: String): String
    fun write(path: String, content: String): Path?
}