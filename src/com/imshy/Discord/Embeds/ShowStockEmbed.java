package com.imshy.Discord.Embeds;

import com.imshy.Stock.Stock;
import com.imshy.Stock.StockPointData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class ShowStockEmbed
{
    public MessageEmbed get(Stock stock)
    {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(stock.stockSymbol.toUpperCase());

        Color color = new Color(6, 102, 213);
        embedBuilder.setColor(color);

        StockPointData current = stock.getReversedDayInfo(0);
        StockPointData yesterday = stock.getReversedDayInfo(1);

        float openDif = current.open - yesterday.open;
        float closeDif = current.close - yesterday.close;
        float highDif = current.high - yesterday.high;
        float lowDif = current.low - yesterday.low;
        long volumeDif = current.volume - yesterday.volume;


        String positiveStatement = "Has Risen by ";
        String downStatement = "Flunked by ";

        StringBuilder sb = new StringBuilder();


        if (closeDif > 0)
            sb.append("Close:  ").append(positiveStatement).append(closeDif).append(" -> ").append("**$").append(current.close).append("**").append("\n");
        else
            sb.append("Close:  ").append(downStatement).append(closeDif).append(" -> ").append("**$").append(current.close).append("**").append("\n");
        if (openDif > 0)
            sb.append("Open:  ").append(positiveStatement).append(openDif).append(" -> ").append("**$").append(current.open).append("**").append("\n");
        else
            sb.append("Open:  ").append(downStatement).append(openDif).append(" -> ").append("**$").append(current.open).append("**").append("\n");
        if (highDif > 0)
            sb.append("High:  ").append(positiveStatement).append(highDif).append(" -> ").append("**$").append(current.high).append("**").append("\n");
        else
            sb.append("High:  ").append(downStatement).append(highDif).append(" -> ").append("**$").append(current.high).append("**").append("\n");
        if (lowDif > 0)
            sb.append("Low:  ").append(positiveStatement).append(lowDif).append(" -> ").append("**$").append(current.low).append("**").append("\n");
        else
            sb.append("Low:  ").append(downStatement).append(lowDif).append(" -> ").append("**$").append(current.low).append("**").append("\n");
        if (volumeDif > 0)
            sb.append("Volume:  ").append(positiveStatement).append(volumeDif).append(" -> ").append("**").append(current.volume).append("**").append("\n");
        else
            sb.append("Volume:  ").append(downStatement).append(volumeDif).append(" -> ").append("**").append(current.volume).append("**").append("\n");

        embedBuilder.addField("Data", sb.toString(), false);
        return embedBuilder.build();
    }
}
