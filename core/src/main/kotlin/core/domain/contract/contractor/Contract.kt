package core.domain.contract.contractor


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import core.domain.contract.GeneralContract
import core.domain.contract.SupportedTypes


data class Contract(
    @SerializedName("base_url")
    @Expose
    var baseUrl: String?,

    @SerializedName("port")
    @Expose
    var port: Int?,

    @SerializedName("provider")
    @Expose
    var provider: String?,

    @SerializedName("consumer")
    @Expose
    var consumer: String?,

    @SerializedName("interactions")
    @Expose
    var interactions: List<Interaction>?
) : GeneralContract {
    override val type
        get() = SupportedTypes.CONTRACTOR

    override val isAllNull
        get() = listOf(provider, consumer, interactions).all { it == null }

}