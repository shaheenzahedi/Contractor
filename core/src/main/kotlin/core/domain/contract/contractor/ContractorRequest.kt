package core.domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import core.domain.contract.contractor.Rule


data class ContractorRequest(
    @SerializedName("headers")
    @Expose
    val headers: LinkedHashMap<String, String>? = null,
    @SerializedName("body")
    @Expose
    val body: LinkedHashMap<String, Any>? = null,
    @SerializedName("params")
    @Expose
    val params: LinkedHashMap<String, String>? = null,
    @SerializedName("cookies")
    @Expose
    val cookies: LinkedHashMap<String, String>? = null,
    @SerializedName("data")
    @Expose
    val data: Any?,
    @SerializedName("queryParamRules")
    @Expose
    val queryParamRules: List<Rule>? = null
)