package ir.arinateam.shopadmin.shop.model

data class ModelRecProduct(

    val id: Int,
    val img: String,
    val bookName: String,
    val bookWriter: String,
    val availableCount: Int,
    val publisher: String,
    val categoryId: Int,
    val price: Int,
    val pageCount: Int,
    val publishYear: Int,
    val isbn: String,
    val discountPercent: Int,
    val description: String

)
