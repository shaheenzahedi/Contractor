package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Response {
    @SerializedName("headers")
    @Expose
    private val headers: LinkedHashMap<String,Any>? = null

    @SerializedName("body")
    @Expose
    private val body: LinkedHashMap<String,Any>? = null

    @SerializedName("bodyRules")
    @Expose
    private val bodyRules: List<Rule>? = null

    @SerializedName("headerRules")
    @Expose
    private val headerRules: List<Rule>? = null
}