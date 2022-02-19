package ir.arinateam.shopadmin.admin

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import ir.arinateam.mpchart.charts.BarChart
import ir.arinateam.mpchart.components.XAxis
import ir.arinateam.mpchart.data.BarData
import ir.arinateam.mpchart.data.BarDataSet
import ir.arinateam.mpchart.data.BarEntry
import ir.arinateam.mpchart.utils.ColorTemplate
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.admin.model.ModelGetAdminSell
import ir.arinateam.shopadmin.api.ApiClient
import ir.arinateam.shopadmin.api.ApiInterface
import ir.arinateam.shopadmin.databinding.SellsFragmentBinding
import ir.arinateam.shopadmin.utils.Loading
import ir.arinateam.shopadmin.utils.JalaliCal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SellsFragment : Fragment() {

    private lateinit var bindingFragment: SellsFragmentBinding

    private lateinit var tvShopsCount: TextView
    private lateinit var barChartWeek: BarChart
    private lateinit var tvWeekSellDate: TextView
    private lateinit var barMonthYear: BarChart
    private lateinit var tvMonthSellDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment =
            DataBindingUtil.inflate(inflater, R.layout.sells_fragment, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(
            "data",
            Context.MODE_PRIVATE
        )

        token = sharedPreferences.getString("token", "")!!

        initView()

        setColorList()

        getAdminSells()

    }

    private fun initView() {

        tvShopsCount = bindingFragment.tvShopsCount
        barChartWeek = bindingFragment.barChartWeek
        tvWeekSellDate = bindingFragment.tvWeekSellDate
        barMonthYear = bindingFragment.barMonthYear
        tvMonthSellDate = bindingFragment.tvMonthSellDate

    }

    private lateinit var apiClient: ApiClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    private fun getAdminSells() {

        val loadingLottie = Loading(requireActivity())

        apiClient = ApiClient()

        val apiInterface: ApiInterface = ApiClient.retrofit.create(ApiInterface::class.java)

        val callLoading = apiInterface.adminSell("Bearer $token")

        callLoading.enqueue(object : Callback<ModelGetAdminSell> {

            override fun onResponse(
                call: Call<ModelGetAdminSell>,
                response: Response<ModelGetAdminSell>
            ) {

                loadingLottie.hideDialog()

                if (response.code() == 200) {

                    val data = response.body()!!

                    tvShopsCount.text =
                        data.deliveredOrderCount.toString().plus(" سفارش تحویل داده شده")

                    weekSell = ArrayList()
                    monthSell = ArrayList()

                    data.lastSevenDaysChartData.forEachIndexed { index, modelBarChartSale ->

                        weekSell.add(
                            BarEntry(
                                index.toFloat(),
                                modelBarChartSale.totalAmount.toFloat()
                            )
                        )

                    }

                    data.lastFourWeeksChartData.forEachIndexed { index, modelBarChartSale ->

                        monthSell.add(
                            BarEntry(
                                index.toFloat(),
                                modelBarChartSale.totalAmount.toFloat()
                            )
                        )

                    }


                    setWeekBarChart()
                    setMonthBarChart()

                } else {

                    Toast.makeText(
                        requireActivity(),
                        resources.getText(R.string.error_receive_data).toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

            override fun onFailure(call: Call<ModelGetAdminSell>, t: Throwable) {

                loadingLottie.hideDialog()

                Toast.makeText(
                    requireActivity(),
                    resources.getText(R.string.error_send_data).toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }

    private val COLORFUL_COLORS = ArrayList<Int>()

    private fun setColorList() {

        COLORFUL_COLORS.add(ColorTemplate.rgb("#0263FF"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#FF7723"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#8E30FF"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#3AF54E"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#98977F"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#7F8665"))
        COLORFUL_COLORS.add(ColorTemplate.rgb("#DCC6EC"))
    }

    private lateinit var weekSell: ArrayList<BarEntry>
    private lateinit var weekSellDate: String

    private fun setWeekBarChart() {

        val dateLastWeek = Calendar.getInstance()
        dateLastWeek.add(Calendar.DATE, -7)

        val yearLastWeek = dateLastWeek.get(Calendar.YEAR)
        val monthLastWeek = dateLastWeek.get(Calendar.MONTH)
        val dayLastWeek = dateLastWeek.get(Calendar.DAY_OF_MONTH)


        val newDateLastWeek = JalaliCal.gregorianToJalali(
            JalaliCal.YearMonthDate(
                yearLastWeek,
                monthLastWeek,
                dayLastWeek
            )
        )

        weekSellDate =
            newDateLastWeek.year.toString() + "/" + newDateLastWeek.month.toString() + "/" + newDateLastWeek.day

        tvWeekSellDate.text = "از تاریخ $weekSellDate تا امروز"

        val barDataSet = BarDataSet(weekSell, "")

        barDataSet.colors = COLORFUL_COLORS
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChartWeek.setFitBars(true)
        barChartWeek.description.text = ""
        barChartWeek.animateY(1000)
        barChartWeek.data = barData
        barChartWeek.xAxis.position = XAxis.XAxisPosition.BOTTOM

        barChartWeek.setTouchEnabled(false)
        barChartWeek.isClickable = false
        barChartWeek.isDoubleTapToZoomEnabled = false
        barChartWeek.isDoubleTapToZoomEnabled = false

        barChartWeek.setDrawGridBackground(false)

        barChartWeek.description.isEnabled = false
        barChartWeek.legend.isEnabled = false

        barChartWeek.axisLeft.setDrawAxisLine(false)
        barChartWeek.axisLeft.enableGridDashedLine(10f, 8f, 0f)
        barChartWeek.axisLeft.enableAxisLineDashedLine(10f, 8f, 0f)

        barChartWeek.xAxis.enableGridDashedLine(10f, 8f, 0f)
        barChartWeek.xAxis.enableAxisLineDashedLine(10f, 8f, 0f)

        barChartWeek.axisRight.setDrawLabels(false)
        barChartWeek.axisRight.setDrawAxisLine(false)
        barChartWeek.axisRight.enableGridDashedLine(10f, 8f, 0f)
        barChartWeek.axisRight.enableAxisLineDashedLine(10f, 8f, 0f)

    }

    private lateinit var monthSell: ArrayList<BarEntry>
    private lateinit var monthSellDate: String

    private fun setMonthBarChart() {

        val dateLastMonth = Calendar.getInstance()
        dateLastMonth.add(Calendar.DATE, -30)

        val yearLastMonth = dateLastMonth.get(Calendar.YEAR)
        val monthLastMonth = dateLastMonth.get(Calendar.MONTH)
        val dayLastMonth = dateLastMonth.get(Calendar.DAY_OF_MONTH)


        val newDateLastMonth = JalaliCal.gregorianToJalali(
            JalaliCal.YearMonthDate(
                yearLastMonth,
                monthLastMonth,
                dayLastMonth
            )
        )

        monthSellDate =
            newDateLastMonth.year.toString() + "/" + newDateLastMonth.month.toString() + "/" + newDateLastMonth.day

        tvMonthSellDate.text = "از تاریخ $monthSellDate تا امروز"

        val barDataSet = BarDataSet(monthSell, "")

        barDataSet.colors = COLORFUL_COLORS
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barMonthYear.setFitBars(true)
        barMonthYear.description.text = ""
        barMonthYear.animateY(1000)
        barMonthYear.data = barData
        barMonthYear.xAxis.position = XAxis.XAxisPosition.BOTTOM

        barMonthYear.setTouchEnabled(false)
        barMonthYear.isClickable = false
        barMonthYear.isDoubleTapToZoomEnabled = false
        barMonthYear.isDoubleTapToZoomEnabled = false

        barMonthYear.setDrawGridBackground(false)

        barMonthYear.description.isEnabled = false
        barMonthYear.legend.isEnabled = false

        barMonthYear.axisLeft.setDrawAxisLine(false)
        barMonthYear.axisLeft.enableGridDashedLine(10f, 8f, 0f)
        barMonthYear.axisLeft.enableAxisLineDashedLine(10f, 8f, 0f)

        barMonthYear.xAxis.enableGridDashedLine(10f, 8f, 0f)
        barMonthYear.xAxis.enableAxisLineDashedLine(10f, 8f, 0f)
        barMonthYear.xAxis.labelCount = 4

        barMonthYear.axisRight.setDrawLabels(false)
        barMonthYear.axisRight.setDrawAxisLine(false)
        barMonthYear.axisRight.enableGridDashedLine(10f, 8f, 0f)
        barMonthYear.axisRight.enableAxisLineDashedLine(10f, 8f, 0f)


    }

}