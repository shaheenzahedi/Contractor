package presentation

class SummaryPresenter(private val results: List<Boolean>) {
    fun showSummary() {
        println("\n-----------\tSUMMARY\t-----------")
        println("Total ${results.count()} test cases executed...")
        colorPrint("${results.count { it }} tests passed\n",ConsoleColors.GREEN_BOLD)
        colorPrint("${results.count { !it }} tests failed\n",ConsoleColors.RED)
        colorPrint("${(results.count { it }.toDouble() / results.count()) * 100} % of tests were passed",ConsoleColors.BLUE_BOLD)
    }
}