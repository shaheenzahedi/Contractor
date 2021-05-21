package service.io.resource.file

import service.io.resource.Resource
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class FileResource : Resource {
    override fun getResourceAsRawText(path: String) = File(path).readText()
    override fun write(path: String, content: String): Path? = Files.writeString(Paths.get(path), content)
}