package ir.arinateam.mpchart.interfaces.dataprovider;


import ir.arinateam.mpchart.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
