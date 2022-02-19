package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecOrderDetailData(

    val amount: Int,

    @SerializedName("price")
    val bookPrice: Int,

    val product: ModelGetOrdersDataDetailProduct

)
