package ir.arinateam.mpchart.interfaces.dataprovider;


import ir.arinateam.mpchart.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
