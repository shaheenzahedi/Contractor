package presentation

import service.generators.callback.CallbackCase

class MutationPresenter(private val mutations: List<CallbackCase>?) {
    fun retrieveSummary(): List<Boolean> {
        return mutations?.map {
            if (!it.mutationName.isNullOrEmpty()) println(it.mutationName)
            it.callback.invoke()
        } ?: emptyList()
    }

}
