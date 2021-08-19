package presentation

class SummaryPresenter(private val results: List<Boolean>) {
    fun showSummary() {
        println("-----------\tSUMMARY\t-----------")
        println("Total ${results.count()} test cases executed...")
        colorPrint("${results.count { it }} tests passed\n",ConsoleColors.GREEN_BOLD)
        colorPrint("${results.count { !it }} tests failed",ConsoleColors.RED)
    }
}