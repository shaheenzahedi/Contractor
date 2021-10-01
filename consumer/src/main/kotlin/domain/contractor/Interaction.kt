package domain.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import domain.contractor.ContractorRequest
import domain.contractor.ContractorResponse


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
    val request: ContractorRequest? = null,
    @SerializedName("response")
    @Expose
    val response: ContractorResponse? = null,
)
