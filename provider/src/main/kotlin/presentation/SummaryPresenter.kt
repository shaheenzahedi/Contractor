package presentation

class SummaryPresenter(private val results: List<Boolean>,private val mutationResults: List<Boolean>) {
    fun showSummary() {
        println("\n----------------------\tSUMMARY\t----------------------")
        println("Total ${results.count()} test cases executed")
        colorPrint("${results.count { it }} tests passed\n",ConsoleColors.GREEN_BOLD)
        colorPrint("${results.count { !it }} tests failed\n",ConsoleColors.RED)
        colorPrint("${((results.count { it }.toDouble() / results.count()) * 100).toInt()}% of tests were passed\n\n\n",ConsoleColors.BLUE_BOLD)
        println("Mutations Executed: ${mutationResults.count()}")
        println("Mutations survived: ${mutationResults.count { it }}")
        println("Mutations killed: ${mutationResults.filterNot { it }.count()}")
        println("Mutation score: ${((mutationResults.filterNot { it }.count().toDouble() / mutationResults.count()) * 100).toInt()}")
    }
}