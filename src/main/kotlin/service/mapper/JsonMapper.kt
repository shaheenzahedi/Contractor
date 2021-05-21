package service.mapper


import com.google.gson.Gson
import domain.contract.GeneralContract
import domain.contract.pact.PactContractModel
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import service.io.resource.Resource
import java.lang.IllegalStateException


class JsonMapper(
    private val resource: Resource
) {

    private fun <T> getJson(clazz: Class<T>, path: String): T =
        Gson().fromJson(resource.getResourceAsRawText(path), clazz)

    fun makeGeneralContract(path: String): GeneralContract {
        val springCloudContractModel = getJson(SpringCloudContractModel::class.java, path)
        val pactModel = getJson(PactContractModel::class.java, path)
        if (!pactModel.isAllNull) return pactModel
        if (!springCloudContractModel.isAllNull) return springCloudContractModel
        throw IllegalStateException("Couldn't recognize the contract")
    }
}