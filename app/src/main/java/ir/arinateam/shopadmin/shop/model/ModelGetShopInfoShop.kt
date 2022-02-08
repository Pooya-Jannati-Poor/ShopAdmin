package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelGetShopInfoShop(

    @SerializedName("name")
    val username: String,

    val address: String

)
