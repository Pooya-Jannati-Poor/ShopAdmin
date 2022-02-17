package ir.arinateam.shopadmin.admin.model

import ir.arinateam.shopadmin.shop.model.ModelBarChart

data class ModelGetAdminSell(

    val deliveredOrderCount: Int,
    val lastSevenDaysChartData: List<ModelBarChart>,
    val lastWeekSaleDate: String,
    val lastFourWeeksChartData: List<ModelBarChart>,
    val lastMonthSaleDate: String,

    )
