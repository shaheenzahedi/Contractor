package core.service.engine

import core.domain.ready_to_generate.ReadyToTestModel

class ContractMutationEngine(private val contracts: List<ReadyToTestModel>?) {
    private val mutationCount = 5
    fun generateStatusMutation(position: Int): List<Int>? {
        val status = contracts?.get(position)?.status
        requireNotNull(status) { return null }
        return getGeneratedStatus(status)
    }

    private fun getGeneratedStatus(status: Int): List<Int> {
        fun generateNotSameNumber(number: Int): Int {
            var result = (100..999).random()
            while (result == number) {
                result = (100..999).random()
            }
            return result
        }
        return mutableListOf<Int>()
            .apply { for (i in 1..mutationCount) add(generateNotSameNumber(status)) }
    }

}