package data.contract


import com.google.gson.annotations.SerializedName

data class BodyX(
    @SerializedName("$.name")
    val name: Name
)