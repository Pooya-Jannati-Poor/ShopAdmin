package ir.arinateam.mpchart.interfaces.dataprovider;


import ir.arinateam.mpchart.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
