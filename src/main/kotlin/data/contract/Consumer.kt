package data.contract


import com.google.gson.annotations.SerializedName

data class Consumer(
    @SerializedName("name")
    val name: String
)