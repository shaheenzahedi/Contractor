package domain.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ContractorResponse {
    @SerializedName("headers")
    @Expose
    val headers: LinkedHashMap<String, String>? = null

    @SerializedName("body")
    @Expose
    val body: LinkedHashMap<String, Any>? = null
}