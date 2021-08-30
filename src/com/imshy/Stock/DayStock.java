package com.imshy.Stock;

import java.util.Random;

public class DayStock extends Stock
{
    public DayStock(String stockSymbol)
    {
        this.timeSeries = "Daily";
        this.stockSymbol = stockSymbol;
        this.stockUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockSymbol + "&apikey=" + apikeys[new Random().nextInt(apikeys.length-1)];
        this.stockData = requestStockData();
        this.reversedDayInfo = getStockPointInfoArray();
        this.dayInfo = getReversedStockPointInfoArray();

        System.out.println("Url used to get info on \"" + stockSymbol + "\" is: " + stockUrl);
    }
}
