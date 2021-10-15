package presentation

import core.service.callback.CallbackCase

class MutationPresenter(private val mutations: List<CallbackCase>?) {
    fun retrieveSummary(): List<Boolean> {
        return mutations?.map {
            val result = it.callback.invoke()
            if (!it.mutationName.isNullOrEmpty())
                println("[${if (!result) "KILLED" else "SURVIVED"}]\t${it.tagName}\t\t${it.mutationName}")
            result
        } ?: emptyList()
    }

}
