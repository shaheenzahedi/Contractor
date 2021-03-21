package domain.contract


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("name")
    val name: String
)