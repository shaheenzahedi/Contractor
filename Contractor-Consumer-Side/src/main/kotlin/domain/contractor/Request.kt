package domain.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



data class Request(
    @SerializedName("headers")
    @Expose
    val headers: LinkedHashMap<String,Any>? = null,
    @SerializedName("body")
    @Expose
    val body: LinkedHashMap<String,Any>? = null,
    @SerializedName("queryParamRules")
    @Expose
    val queryParamRules: List<Rule>? = null
)