package service.generators.callback.path

class PathGenerate(
    private val baseUrl: String,
    private val port: Int?,
    private val path: String
) {
    fun retrieveFullPath(): String {
        return "${retrieveBaseUrl(baseUrl)}${retrievePort(port)}${retrievePath(path)}"
    }

    private fun retrieveBaseUrl(rawBaseUrl: String): String {
        return if (rawBaseUrl.endsWith("/")) rawBaseUrl.substring(0, rawBaseUrl.length - 1)
        else rawBaseUrl
    }

    private fun retrievePort(rawPort: Int?): String {
        return if (rawPort != null) ":${rawPort}"
        else ""
    }

    private fun retrievePath(rawPath: String): String {
        return if (rawPath.startsWith("/")) rawPath
        else "/${rawPath}"
    }
}