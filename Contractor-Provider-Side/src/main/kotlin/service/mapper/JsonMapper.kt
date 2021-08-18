package service.mapper


import com.google.gson.Gson
import domain.contract.GeneralContract
import domain.contract.contractor.Contract
import domain.contract.pact.PactContractModel
import domain.contract.spring_cloud_contract.SpringCloudContractModel
import service.io.resource.Resource


class JsonMapper(
    private val resource: Resource
) {

    private fun <T> getJson(clazz: Class<T>, path: String): T =
        Gson().fromJson(resource.getResourceAsRawText(path), clazz)

    fun makeGeneralContract(path: String): GeneralContract {
        val contractorModel = getJson(Contract::class.java, path)
        if (!contractorModel.isAllNull) return contractorModel
        val springCloudContractModel = getJson(SpringCloudContractModel::class.java, path)
        if (!springCloudContractModel.isAllNull) return springCloudContractModel
        val pactModel = getJson(PactContractModel::class.java, path)
        if (!pactModel.isAllNull) return pactModel
        throw IllegalStateException("Couldn't recognize the contract")
    }
}