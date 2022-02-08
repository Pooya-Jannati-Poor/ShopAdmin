package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecOrderBase(

    @SerializedName("data")
    val orders: ArrayList<ModelRecOrder>

)
