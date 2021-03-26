package service.io

interface IResource {
    fun getResourceAsRawText(path:String): String
}