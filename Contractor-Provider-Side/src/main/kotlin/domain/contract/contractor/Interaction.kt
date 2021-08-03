package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Interaction(
    @SerializedName("method")
    @Expose
    private val method: String? = null,
    @SerializedName("path")
    @Expose
    private val path: String? = null,
    @SerializedName("status")
    @Expose
    private val status: Int? = null,
    @SerializedName("request")
    @Expose
    private val request: Request? = null,
    @SerializedName("response")
    @Expose
    private val response: Response? = null
)
