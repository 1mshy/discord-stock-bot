package com.imshy.Stock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public abstract class Stock
{
    public String stockSymbol;
    public String stockUrl;
    public JsonObject stockData;
    public String timeSeries;
    public StockPointData[] dayInfo;
    public StockPointData[] reversedDayInfo;
    public String[] apikeys = {"8YCFRKRSFUDWJF7B", "CFLMYAONR8Y78KTL", "4M296R7OJ2BAS39F", "R3CBMFOAQZQ3K01J", "PE3HFJ05736ANVWO", "LMTXRHO0WOIYNP3Z", "9CSMLZAJDTQJVM7W", "D4ML5TD98MM8GJWR", "INXOO4ACIJLSC89R", "TWOH8AIPFBQITIH7", "LS82YMQPGC2BXTGV", "QZ6OJSH79L5OEG9R"};


    public Stock()
    {

    }


    public JsonObject requestStockData()
    {
        JsonObject jsonObject;
        try
        {
            jsonObject = JsonParser.parseString(request(stockUrl)).getAsJsonObject();
        } catch (Exception e)
        {

            System.out.println("The request to the url " + stockUrl + " from \"requestStockData\" has thrown an exception");
            jsonObject = null;
        }
        return jsonObject;

    }

    public StockPointData getReversedDayInfo(int day)
    {
        if (day >= reversedDayInfo.length)
        {
            System.out.println("ERROR IN STOCK: date given excedes the array length");
            return null;
        }
        return reversedDayInfo[day];
    }

    public StockPointData getDayInfo(int day)
    {
        if (day >= dayInfo.length)
        {
            System.out.println("ERROR IN STOCK: date given excedes the array length");
            return null;
        }
        return dayInfo[day];
    }

    public void compare(Stock s)
    {
        StringBuilder sb = new StringBuilder();

    }

    public void graph()
    {
    }

    public String request(String url) throws Exception
    {
        /* new java 14 way to complete a get request */
        String line;
        StringBuilder sb = new StringBuilder();
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }

        br.close();
        con.disconnect();

        return sb.toString();
    }

    public StockPointData[] getStockPointInfoArray()
    {
        System.out.println(stockData);
        JsonObject timeSeriesData = stockData.get("Time Series (" + timeSeries + ")").getAsJsonObject();
        /*returns a map of the string and JsonElement for every key/value pair
         * this is then stored in a set*/
        Set<Map.Entry<String, JsonElement>> entries = timeSeriesData.entrySet();

        StockPointData[] stockArr = new StockPointData[entries.size()];

        /*Turned into an arraylist*/
        List<Map.Entry<String, JsonElement>> al = new ArrayList<>(entries);
        /*It is then reversed as the data needs to be from old to recent (given as recent to old) */
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : al)
        {
//            turns the JsonElement to a JsonObject to be able to work with it's methods
            JsonObject jo = entry.getValue().getAsJsonObject();
//            StockPointData is a class to store the different variables such as:
//            open, close, high, low, volume, x value in a graph(aka placement)
            StockPointData s = new StockPointData();

            s.open = jo.get("1. open").getAsFloat();
            s.close = jo.get("4. close").getAsFloat();
            s.high = jo.get("2. high").getAsFloat();
            s.low = jo.get("3. low").getAsFloat();
            s.volume = jo.get("5. volume").getAsLong();
            s.placement = i;

            stockArr[i++] = s;
        }
        return stockArr;
    }

    public StockPointData[] getReversedStockPointInfoArray()
    {

        JsonObject timeSeriesData = stockData.get("Time Series (" + timeSeries + ")").getAsJsonObject();
        /*returns a map of the string and JsonElement for every key/value pair
         * this is then stored in a set*/
        Set<Map.Entry<String, JsonElement>> entries = timeSeriesData.entrySet();

        StockPointData[] stockArr = new StockPointData[entries.size()];

        /*Turned into an arraylist*/
        List<Map.Entry<String, JsonElement>> al = new ArrayList<>(entries);
        /*It is then reversed as the data needs to be from old to recent (given as recent to old) */
        Collections.reverse(al);
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : al)
        {
//            turns the JsonElement to a JsonObject to be able to work with it's methods
            JsonObject jo = entry.getValue().getAsJsonObject();
//            StockPointData is a class to store the different variables such as:
//            open, close, high, low, volume, x value in a graph(aka placement)
            StockPointData s = new StockPointData();

            s.open = jo.get("1. open").getAsFloat();
            s.close = jo.get("4. close").getAsFloat();
            s.high = jo.get("2. high").getAsFloat();
            s.low = jo.get("3. low").getAsFloat();
            s.volume = jo.get("5. volume").getAsLong();
            s.placement = i;

            stockArr[i++] = s;
        }
        return stockArr;
    }
}