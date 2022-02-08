package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecProduct(

    val id: Int,

    @SerializedName("image")
    val img: String,

    @SerializedName("name")
    val bookName: String,

    @SerializedName("writer")
    val bookWriter: String,

    @SerializedName("amount")
    val availableCount: Int,

)
