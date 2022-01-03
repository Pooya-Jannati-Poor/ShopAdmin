package ir.arinateam.mpchart.interfaces.dataprovider;


import ir.arinateam.mpchart.components.YAxis;
import ir.arinateam.mpchart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
