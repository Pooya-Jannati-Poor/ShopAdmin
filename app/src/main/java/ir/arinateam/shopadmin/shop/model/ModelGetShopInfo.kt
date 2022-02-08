package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelGetShopInfo(

    val id: Int,

    @SerializedName("profileImage")
    val shopImage: String,

    @SerializedName("name")
    val shopName: String,

    @SerializedName("phone")
    val phoneNumber: String,

    val shop: ModelGetShopInfoShop

)
