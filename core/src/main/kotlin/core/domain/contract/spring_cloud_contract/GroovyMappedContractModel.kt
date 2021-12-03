package core.domain.contract.spring_cloud_contract

import core.domain.contract.GeneralContract
import core.domain.contract.SupportedTypes
import org.springframework.cloud.contract.spec.Contract

class GroovyMappedContractModel : GeneralContract, Contract() {
    override val type: SupportedTypes
        get() = SupportedTypes.SCC
    override val isAllNull: Boolean
        get() = false
}