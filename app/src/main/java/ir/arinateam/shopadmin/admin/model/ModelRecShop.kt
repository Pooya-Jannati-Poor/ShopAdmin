package ir.arinateam.shopadmin.admin.model

import com.google.gson.annotations.SerializedName

data class ModelRecShop(

    val id: Int,

    @SerializedName("name")
    val shopName: String,

    @SerializedName("active")
    val isActivated: Int
)