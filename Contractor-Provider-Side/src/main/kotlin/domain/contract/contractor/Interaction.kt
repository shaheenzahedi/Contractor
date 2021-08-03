package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Interaction(
    @SerializedName("method")
    @Expose
    val method: String? = null,
    @SerializedName("path")
    @Expose
    val path: String? = null,
    @SerializedName("status")
    @Expose
    val status: Int? = null,
    @SerializedName("request")
    @Expose
    val request: Request? = null,
    @SerializedName("response")
    @Expose
    val response: Response? = null
)
