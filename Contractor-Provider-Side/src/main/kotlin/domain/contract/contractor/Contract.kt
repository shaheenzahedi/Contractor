package domain.contract.contractor


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import domain.contract.GeneralContract
import domain.contract.SupportedTypes


data class Contract(
    @SerializedName("base_url")
    @Expose
    var baseUrl: String = "http://localhost/",

    @SerializedName("port")
    @Expose
    var port: Int = 8080,

    @SerializedName("provider")
    @Expose
    var provider: String?,

    @SerializedName("consumer")
    @Expose
    var consumer: String?,

    @SerializedName("interactions")
    @Expose
    var interactions: List<Interaction>?
): GeneralContract {
    override val type
        get() = SupportedTypes.CONTRACTOR

    override val isAllNull
        get() = listOf(provider, consumer, interactions).all { it==null }

}