package service.io

import java.io.File

class FileResource:IResource {
    override fun getResourceAsRawText(path:String) = File(path).readText()
}