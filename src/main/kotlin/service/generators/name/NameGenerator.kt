package service.generators.name

import domain.RTTest.ReadyToTestModel

class NameGenerator(
    private val model: ReadyToTestModel
) {
    fun getStatusTestName(): String {
        return manipulateNameWithMethod("StatusCodeEquals${model.status}",extractNameFromPath())
    }

    fun getHeaderTestName(): String {
        return manipulateNameWithMethod("Header",extractNameFromPath())
    }

    fun getBodyTestName(): String {
        return manipulateNameWithMethod("Body",extractNameFromPath())
    }

    private fun manipulateNameWithMethod(midString: String, rawName: String) =
        "${model.method?.name}${rawName}${midString}Test"

    private val idInPathRegex = "/[0-9]+/*"
    private fun extractNameFromPath(): String {
        var result = model.path
        requireNotNull(result) { return "sample" }
        fun removeBackSlashFromString(inp: String) = inp.replace("/", "_")
        if (result.startsWith('/')) result = result.substring(1)
        result = result.replace(Regex(idInPathRegex)) { "WithId${removeBackSlashFromString(it.value)}" }
        result = removeBackSlashFromString(result).capitalize()
        if (result.isEmpty()) result = "RootPath"
        return result
    }
}