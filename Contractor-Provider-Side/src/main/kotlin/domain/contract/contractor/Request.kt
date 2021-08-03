package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



data class Request(
    @SerializedName("headers")
    @Expose
    private val headers: LinkedHashMap<String,Any>? = null,
    @SerializedName("body")
    @Expose
    private val body: LinkedHashMap<String,Any>? = null,
    @SerializedName("queryParamRules")
    @Expose
    private val queryParamRules: List<Rule>? = null
)