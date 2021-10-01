package core.domain.contract.pact.consumer
//package core.domain.contract.pact.consumer


import com.google.gson.annotations.SerializedName

data class ConsumerDTO(
    @SerializedName("name")
    val name: String
)