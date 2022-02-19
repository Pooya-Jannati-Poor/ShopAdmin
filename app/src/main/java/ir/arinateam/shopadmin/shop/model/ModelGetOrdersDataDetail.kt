package ir.arinateam.shopadmin.shop.model

data class ModelGetOrdersDataDetail(

    val id: Int,

    val price: Int,

    val amount: Int,

    val product: ModelGetOrdersDataDetailProduct

)
