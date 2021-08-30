package com.imshy.Stock;

public class Vector
{
    public double x;
    public double y;
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;

    }
    public Vector()
    {
        this.x = 0;
        this.y = 0;
    }
    @Override
    public String toString()
    {
        return "x=" + x + ",y=" + y;
    }


}
