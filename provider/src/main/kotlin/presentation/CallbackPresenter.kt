package presentation

import service.generators.callback.CallbackCase

class CallbackPresenter(private val callbacks: List<CallbackCase>) {
    fun retrieveSummary(): List<Boolean> {
        return callbacks.map {
            println("\n----------------------\t<${it.tagName}>\t----------------------")
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
                if (it.reason != null) {
                    print("REASON:\t")
                    colorPrint(it.reason + "\n", ConsoleColors.RED)
                }
                if (it.expected != null) {
                    println("EXPECTED:")
                    println(it.expected)
                }
                if (it.actual != null) {
                    println("RECEIVED:")
                    println(it.actual)
                }
            }
            println("----------------------\t<${it.tagName} />\t----------------------")
            Thread.sleep(500)
            result
        }
    }
}