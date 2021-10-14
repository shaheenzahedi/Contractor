package presentation

import service.generators.callback.CallbackCase

class MutationPresenter(private val mutations: List<CallbackCase>?) {
    fun retrieveSummary(): List<Boolean> {
        return mutations?.map {
            val result = it.callback.invoke()
            if (!it.mutationName.isNullOrEmpty())
                println("${it.tagName}--${it.mutationName}--${if (!result) "KILLED" else "SURVIVED"}")
            result
        } ?: emptyList()
    }

}
