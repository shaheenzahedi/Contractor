package domain.contract.contractor


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated


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
)