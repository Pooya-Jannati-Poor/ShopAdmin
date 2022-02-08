package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecProductBase(

    @SerializedName("data")
    val productInfo: List<ModelRecProduct>

)
