package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecOrderDetailBase(

    @SerializedName("details")
    val orderDetails: ModelRecOrderDetail,

    val order: ModelOrder

)
