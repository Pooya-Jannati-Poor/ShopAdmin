package ir.arinateam.shopadmin.admin.model

import com.google.gson.annotations.SerializedName

data class ModelAdminShopsInfoBase(

    @SerializedName("data")
    val shops: List<ModelRecShop>

)
