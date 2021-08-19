package presentation

import service.generators.callback.CallbackCase

class CallbackPresenter(private val callbacks: List<CallbackCase>) {
    fun retrieveSummary(): List<Boolean> {
        return callbacks.mapIndexed { index, it ->
            println("-----------\tTEST ${index+1}\t-----------")
            colorPrint(it.doc, ConsoleColors.YELLOW)
            val result = it.callback.invoke()
            print("[")
            when {
                result -> colorPrint("SUCCESS", ConsoleColors.BACKGROUND_GREEN)
                else -> colorPrint("FAILED", ConsoleColors.BACKGROUND_RED)
            }
            print("]\t")
            println(it.name)
            if (!result) {
                print("REASON:\t")
                colorPrint(it.reason + "\n", ConsoleColors.RED)
                println("EXPECTED:")
                println(it.expected)
                println("RECEIVED:")
                println(it.actual)
            }
            Thread.sleep(500)
            result
        }
    }
}