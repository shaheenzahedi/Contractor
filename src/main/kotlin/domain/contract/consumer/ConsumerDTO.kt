package domain.contract.consumer


import com.google.gson.annotations.SerializedName

data class ConsumerDTO(
    @SerializedName("name")
    val name: String
)