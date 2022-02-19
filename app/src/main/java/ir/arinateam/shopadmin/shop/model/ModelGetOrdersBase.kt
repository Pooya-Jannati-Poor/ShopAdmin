package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelGetOrdersBase(

    @SerializedName("orders")
    val ordersBase: ModelGetOrdersDataBase

)
