package domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Rule(
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("value")
    @Expose
    val value: String
)