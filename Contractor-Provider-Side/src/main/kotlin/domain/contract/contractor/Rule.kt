package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Rule(
    @SerializedName("name")
    @Expose
    private val name: String? = null,
    @SerializedName("type")
    @Expose
    private val type: String? = null,
    @SerializedName("value")
    @Expose
    private val value: String? = null
)