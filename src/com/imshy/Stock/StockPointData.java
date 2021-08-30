package com.imshy.Stock;

public class StockPointData
{
    public float open;
    public float close;
    public float high;
    public float low;
    public long volume;
    /*placement is used to identify the x value of a stock*/
    public int placement;

    public StockPointData(float open, float close, float high, float low, long volume, int placement)
    {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.placement = placement;
    }
    public StockPointData()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("open=").append(open).append(",");
        sb.append("close=").append(close).append(",");
        sb.append("high=").append(high).append(",");
        sb.append("low=").append(low).append(",");
        sb.append("volume=").append(volume).append(",");
        sb.append("placement=").append(placement);

        return sb.toString();
    }

}
