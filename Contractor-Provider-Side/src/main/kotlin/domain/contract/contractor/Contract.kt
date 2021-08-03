package domain.contract.contractor


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import domain.contract.GeneralContract
import domain.contract.SupportedTypes


data class Contract(
    @SerializedName("provider")
    @Expose
    var provider: String? = null,

    @SerializedName("consumer")
    @Expose
    var consumer: String? = null,

    @SerializedName("interactions")
    @Expose
    var interactions: List<Interaction>? = null
): GeneralContract {
    override val type
        get() = SupportedTypes.CONTRACTOR

    override val isAllNull
        get() = listOf(provider, consumer, interactions).all { it==null }

}