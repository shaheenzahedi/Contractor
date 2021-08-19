package presentation

class SummaryPresenter(private val results: List<Boolean>) {
    fun showSummary() {
        println("Total ${results.count()} test cases")
        println("${results.count { it }} tests passed")
        println("${results.count { !it }} tests failed")
    }
}