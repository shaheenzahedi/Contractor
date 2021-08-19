package presentation

import service.generators.callback.CallbackCase

class CallbackPresenter(private val callbacks: List<CallbackCase>) {
    fun retrieveSummary(): List<Boolean> {
        return callbacks.map {
            val result = it.callback.invoke()
            print("[")
            when {
                result -> colorPrint("SUCCESS", ConsoleColors.GREEN_BOLD)
                else -> colorPrint("FAILED", ConsoleColors.RED)
            }
            print("]\t")
            println(it.name)
            if (!result) {
                println("EXPECTED:")
                println(it.expected)
                println("RECEIVED:")
                println(it.actual)
            }
            println("----------------------------")
            result
        }
    }
}