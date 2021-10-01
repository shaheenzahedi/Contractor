package core.domain.contract.contractor

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Rule(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("value")
    @Expose
    val value: String
) {
    fun getEnumType(): RuleType {
        return RuleType.valueOf(type.uppercase())
    }
}

enum class RuleType {
    EQUALTO,
    CONTAINS,
    MATCHES,
    DOESNOTMATCH
}