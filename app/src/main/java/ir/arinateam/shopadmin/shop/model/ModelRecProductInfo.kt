package ir.arinateam.shopadmin.shop.model

import com.google.gson.annotations.SerializedName

data class ModelRecProductInfo(

    val id: Int,
    val image: String,
    val name: String,
    val writer: String,
    val amount: Int,
    val publisher: String,

    @SerializedName("category")
    val category: ModelSpCategory,
    val price: Int,
    val pages: Int,
    val publish_year: Int,
    val isbn: String,
    val discountPercent: Int,
    val description: String

)
