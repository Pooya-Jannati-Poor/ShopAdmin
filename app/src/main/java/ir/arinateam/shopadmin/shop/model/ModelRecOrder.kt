package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecOrder(


    val id: Int,
    val img: String,
    val username: String,

    @SerializedName("createdJal")
    val date: String,

    @SerializedName("total_amount")
    val orderCount: Int,

    @SerializedName("total_price")
    val price: Int
)
