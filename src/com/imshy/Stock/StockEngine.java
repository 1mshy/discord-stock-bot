package com.imshy.Stock;

import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

public class StockEngine implements IStockEngine
{
    /*This integer is the amount of times the trend has to go in a certain way to be validated*/
    private int trendValidity = 3;

    @Override
    public boolean isAboveUptrend(Stock stock)
    {
        int count = 0;
        double lasthigh = 0f;

        StockPointData lastPoint = stock.dayInfo[stock.dayInfo.length - 1];

        List<StockPointData> points = new ArrayList<>();

        for (int i = stock.dayInfo.length - 1 - 1; i >= stock.dayInfo.length - 15; i--)
        {
            StockPointData currentPoint = stock.dayInfo[i];

            if (count == trendValidity)
            {
                StockPointData latest = stock.dayInfo[stock.dayInfo.length - 1];
                StockPointData startingPoint;
                StockPointData endingPoint;
                float slope1;
                float slope2;

                startingPoint = points.get(0);
                endingPoint = points.get(points.size() - 1);

                slope1 = calculateSlope(startingPoint, endingPoint);
                slope2 = calculateSlope(startingPoint, lastPoint);
                System.out.println(slope1);
                System.out.println(slope2);

                System.out.println(latest);
                System.out.println(startingPoint);
                System.out.println(endingPoint);
                return slope2 > slope1;
            }

            if (lastPoint.high > currentPoint.high)
            {
                points.add(lastPoint);
                lastPoint = currentPoint;
                count++;
                continue;
            }
            points.clear();
            lastPoint = currentPoint;
            count = 0;
        }
        return false;
    }

    @Override
    public void showLineChart()
    {


    }

    @Override
    public XYSeries calculateMovingAverage(Stock stock, int movingAverage)
    {
        ArrayList<Float> floatArr = new ArrayList<>();


            for (int i = 0; i < movingAverage - 1; i++)
            {
                floatArr.add(stock.getDayInfo(i).close);
            }
            XYSeries movingAverageSeries = new XYSeries(movingAverage + "Day Moving Average");/* integer starts at 9 because of moving average needing 10 days [index 0 -> 9 = 10 increments]*/
            for (int i = movingAverage - 1; i < stock.dayInfo.length; i++)
            {
                floatArr.add(stock.getDayInfo(i).close);
                StockPointData sPD = stock.getDayInfo(i);
                float sum = 0;
                for (float f : floatArr) sum += f;/*                System.out.println(sum);*/
                float average = sum / movingAverage;/*                System.out.println(average);*/
                movingAverageSeries.add(sPD.placement, average);
                floatArr.remove(0);

            }
            return movingAverageSeries;
    }

    @Override
    public XYSeries calculateWeightedMovingAverage(Stock stock, int weightedMovingAverage)
    {
        XYSeries weightedMovingAverageSeries = new XYSeries(weightedMovingAverage + "Day Weighted Moving Average");

            ArrayList<Float> floatArr = new ArrayList<>();


            for (int i = 0; i < weightedMovingAverage - 1; i++)
            {
                floatArr.add(stock.getDayInfo(i).close);
            }

            for (int i = weightedMovingAverage - 1; i < stock.dayInfo.length; i++)
            {
                floatArr.add(stock.getDayInfo(i).close);

                StockPointData sPD = stock.getDayInfo(i);
                System.out.println(sPD);
                float product = 0;
                int divisor = 0;
                float average;

                for (int f = 0; f < floatArr.size(); )
                {
                    product += floatArr.get(f) * ++f;
                }
                for (int f = 0; f < floatArr.size(); )
                {
                    divisor += ++f;
                }
                average = product / divisor;
                weightedMovingAverageSeries.add(sPD.placement, average);


                floatArr.remove(0);
            }

            return weightedMovingAverageSeries;

    }

    private float calculateSlope(StockPointData a, StockPointData b)
    {
        return (a.close - b.close) / (a.placement - b.placement);
    }
}
