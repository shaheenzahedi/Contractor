package service.generators.name

import domain.RTTest.HTTPMethod
import domain.RTTest.ReadyToTestModel

class NameGenerator(
    private val model: ReadyToTestModel
) {
    private val idInPathRegex = "/[0-9]+/*"
    fun getStatusTestName(): String {
        val changedPath = extractNameFromPath(model.method,model.path)
        return changedPath
    }

    fun getHeaderTestName(): String {
        val changedPath = extractNameFromPath(model.method,model.path)
        return changedPath
    }

    fun getBodyTestName(): String {
        val changedPath = extractNameFromPath(model.method,model.path)
        return changedPath
    }


    private fun extractNameFromPath(method: HTTPMethod?, path: String?): String {
        requireNotNull(path) { return "sampleTest" }
        requireNotNull(method) { return "sampleTest" }
        fun removeBackSlashFromString(inp: String) = inp.replace("/", "_")
        var result = path
        if (result.startsWith('/')) result = result.substring(1)
        result = result.replace(Regex(idInPathRegex)) { "WithId${removeBackSlashFromString(it.value)}" }
        result = removeBackSlashFromString(result).capitalize()
        if (result.isEmpty())result = "RootPath"
        result = "${method.name}$result"
        return result
    }
}