package com.imshy.Discord.Embeds;

import com.imshy.Stock.Stock;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class DayEmbed
{
    public MessageEmbed[] get(Stock[] stocks)
    {
        MessageEmbed[] messageEmbeds = new MessageEmbed[stocks.length];
        ShowStockEmbed showStockEmbed = new ShowStockEmbed();
        for(int i = 0; i < stocks.length; i++)
        {
           messageEmbeds[i]=showStockEmbed.get(stocks[i]);
        }
        System.out.println(messageEmbeds);
       return messageEmbeds;
    }
}
