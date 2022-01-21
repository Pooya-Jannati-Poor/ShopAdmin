package ir.arinateam.shopadmin.shop.model

data class ModelGetShopDashboard(

    val shopImage: String,
    val shopName: String,
    val todaySale: Int,
    val productCount: Int,
    val lsLastWeekSale: List<Float>,
    val lastWeekSaleDate: String,
    val lsLastMonthSale: List<Float>,
    val lastMonthSaleDate: String,

    )
