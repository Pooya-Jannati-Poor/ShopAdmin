package ir.arinateam.shopadmin.admin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.arinateam.mpchart.charts.BarChart
import ir.arinateam.mpchart.components.XAxis
import ir.arinateam.mpchart.data.BarData
import ir.arinateam.mpchart.data.BarDataSet
import ir.arinateam.mpchart.data.BarEntry
import ir.arinateam.mpchart.utils.ColorTemplate
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.SellsFragmentBinding

class SellsFragment : Fragment() {

    private lateinit var bindingFragment: SellsFragmentBinding

    private lateinit var barChartWeek: BarChart
    private lateinit var barMonthYear: BarChart

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

        initView()

        setColorList()

        setWeekBarChart()

        setMonthBarChart()

    }

    private fun initView() {

        barChartWeek = bindingFragment.barChartWeek
        barMonthYear = bindingFragment.barMonthYear

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

    private fun setWeekBarChart() {

        val weekSell = ArrayList<BarEntry>()

        weekSell.add(BarEntry(0f, 7f))
        weekSell.add(BarEntry(1f, 5f))
        weekSell.add(BarEntry(2f, 12f))
        weekSell.add(BarEntry(3f, 0f))
        weekSell.add(BarEntry(4f, 4f))
        weekSell.add(BarEntry(5f, 8f))
        weekSell.add(BarEntry(6f, 2f))

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

    private fun setMonthBarChart() {

        val monthSell = ArrayList<BarEntry>()

        monthSell.add(BarEntry(0f, 70f))
        monthSell.add(BarEntry(1f, 50f))
        monthSell.add(BarEntry(2f, 120f))
        monthSell.add(BarEntry(3f, 10f))
        monthSell.add(BarEntry(4f, 40f))

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