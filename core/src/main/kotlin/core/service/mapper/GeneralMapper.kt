package core.service.mapper


import com.google.gson.Gson
import core.domain.contract.GeneralContract
import core.domain.contract.contractor.Contract
import core.domain.contract.pact.PactContractModel
import core.domain.contract.spring_cloud_contract.SpringCloudContractModel
import core.service.io.resource.Resource


class GeneralMapper(
    private val resource: Resource
) {

    private fun <T> getJson(clazz: Class<T>, path: String): T =
        Gson().fromJson(resource.getResourceAsRawText(path), clazz)

    fun makeGeneralContract(path: String): GeneralContract {
        if (path.endsWith(".groovy")) {
            return SCCContractMapper().fromGroovy(SCCGroovyLoader(path).getContract())
        } else {
            val contractorModel = getJson(Contract::class.java, path)
            if (!contractorModel.isAllNull) return contractorModel
            val springCloudContractModel = getJson(SpringCloudContractModel::class.java, path)
            if (!springCloudContractModel.isAllNull) return springCloudContractModel
            val pactModel = getJson(PactContractModel::class.java, path)
            if (!pactModel.isAllNull) return pactModel
            throw IllegalStateException("Couldn't recognize the contract")
        }
    }
}