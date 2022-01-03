package ir.arinateam.shopadmin.admin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.arinateam.mpchart.charts.BarChart
import ir.arinateam.mpchart.data.BarData
import ir.arinateam.mpchart.data.BarDataSet
import ir.arinateam.mpchart.data.BarEntry
import ir.arinateam.mpchart.utils.ColorTemplate
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.SellsFragmentBinding

class SellsFragment : Fragment() {

    private lateinit var bindingFragment: SellsFragmentBinding

    private lateinit var barChartWeek: BarChart

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

        setWeekBarChart()

    }

    private fun initView() {

        barChartWeek = bindingFragment.barChartWeek

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

        val barDataSet = BarDataSet(weekSell, "فروش هفته")

//        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChartWeek.data = barData

    }


}