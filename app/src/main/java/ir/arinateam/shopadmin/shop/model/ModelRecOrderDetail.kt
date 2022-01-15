package ir.arinateam.shopadmin.shop.model

data class ModelRecOrderDetail(

    val img: String,
    val bookName: String,
    val bookCount: Int,
    val bookPrice: Int,
    val bookSeller: String,
    val orderState: Boolean

)
