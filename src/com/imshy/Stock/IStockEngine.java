package com.imshy.Stock;

import org.jfree.data.xy.XYSeries;

public interface IStockEngine
{
    boolean isAboveUptrend(Stock stock);
    void showLineChart();
    XYSeries calculateMovingAverage(Stock stock, int movingAverage);
    XYSeries calculateWeightedMovingAverage(Stock stock, int weightedMovingAverage);
}
