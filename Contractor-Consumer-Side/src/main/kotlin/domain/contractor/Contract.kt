package domain.contractor


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


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
)