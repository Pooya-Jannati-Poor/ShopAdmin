package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelGetOrdersDataBase(

    @SerializedName("data")
    val data: List<ModelGetOrdersData>

)
