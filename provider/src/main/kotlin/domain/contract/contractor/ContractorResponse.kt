package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ContractorResponse {
    @SerializedName("headers")
    @Expose
    val headers: LinkedHashMap<String, String>? = null

    @SerializedName("body")
    @Expose
    val body: LinkedHashMap<String, Any>? = null

    @SerializedName("bodyRules")
    @Expose
    val bodyRules: List<Rule>? = null

    @SerializedName("headerRules")
    @Expose
    val headerRules: List<Rule>? = null
}