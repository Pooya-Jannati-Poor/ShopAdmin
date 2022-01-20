package ir.arinateam.shopadmin.shop.model

data class ModelGetShopDashboard(

    val shopImage: String,
    val shopName: String,
    val todaySale: Int,
    val productCount: Int,
    val lsLastWeekSale: List<ModelBarChartSale>,
    val lastWeekSaleDate: String,
    val lsLastMonthSale: List<ModelBarChartSale>,
    val lastMonthSaleDate: String,

    )
