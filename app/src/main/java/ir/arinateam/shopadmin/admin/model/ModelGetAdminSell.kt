package ir.arinateam.shopadmin.admin.model

data class ModelGetAdminSell(

    val deliveredOrder: Int,
    val lsLastWeekSale: List<Float>,
    val lastWeekSaleDate: String,
    val lsLastMonthSale: List<Float>,
    val lastMonthSaleDate: String,

    )
