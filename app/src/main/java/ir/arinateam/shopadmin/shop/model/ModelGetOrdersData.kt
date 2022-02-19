package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelGetOrdersData(

    val id: Int,

    val total_amount: Int,

    val total_price: Int,

    val createdJal: String,

    val stateName: String,

    @SerializedName("details")
    val details: ArrayList<ModelGetOrdersDataDetail>

)
