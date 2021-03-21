package domain.contract.consmer


import com.google.gson.annotations.SerializedName

data class Consumer(
    @SerializedName("name")
    val name: String
)