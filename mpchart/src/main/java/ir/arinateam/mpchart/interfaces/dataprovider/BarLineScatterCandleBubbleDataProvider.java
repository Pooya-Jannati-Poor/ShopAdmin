package ir.arinateam.mpchart.interfaces.dataprovider;


import ir.arinateam.mpchart.components.YAxis;
import ir.arinateam.mpchart.data.BarLineScatterCandleBubbleData;
import ir.arinateam.mpchart.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
