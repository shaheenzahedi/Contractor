package service.generators.name

import domain.RTTest.ReadyToTestModel

class NameGenerator(
    private val model: ReadyToTestModel
) {
    fun getStatusTestName(): String {
        val changedPath = extractNameFromPath(model.path)
        return changedPath
    }

    fun getHeaderTestName(): String {
        return "HEADERTESTNAME"
    }

    fun getBodyTestName(): String {
        return "BODYTESTNAME"
    }


    private fun extractNameFromPath(path: String?): String {
        requireNotNull(path) { return "" }
        return path.replace("/","_")
    }
}