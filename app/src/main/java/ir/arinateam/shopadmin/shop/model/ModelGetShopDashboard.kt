package ir.arinateam.shopadmin.shop.model

data class ModelGetShopDashboard(

    val todayOrdersCount: Int,
    val productsCount: Int,
    val lastSevenDaysChartData: List<ModelBarChart>,
    val lastWeekSaleDate: String,
    val lastFourWeeksChartData: List<ModelBarChart>,
    val lastMonthSaleDate: String,

    )
