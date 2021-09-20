package com.imshy.Discord;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.imshy.Config;
import com.imshy.Discord.Embeds.DayEmbed;
import com.imshy.Stock.DayStock;
import com.imshy.Stock.Stock;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendData
{
    public void sendData()
    {
        HashMap<String, Stock> sharedStockList = new HashMap<>();
        final DayEmbed dayEmbed = new DayEmbed();
        final ChannelManager cm = new ChannelManager();
        final Config config = new Config();

/*
        This way of reading the info is shit, but changing it would require recoding
        my storage system from start
*/
        // get the json object as an entry set
        var x = cm.getChannelData().entrySet();
        for (Map.Entry er : x)
        {
            List<Stock> localStockList = new ArrayList<>();
            {
                // converts the entry set to a json object of the channel with this format:
//          Ex: {"StockList":["tsla"],"Time":1440,"ChannelId":"880958485821550613"}
                JsonObject ob = new JsonParser().parse(er.getValue().toString()).getAsJsonObject();
//               This loop will get all stocks into the localStockList
                for (JsonElement stockElement : ob.get("StockList").getAsJsonArray())
                {
                    // stock symbol is given as  "tsla" and transformed to tsla
                    String stockSymbol = removeQuotes(stockElement.toString());
                    // try to find the stock symbol in the list of stocks
                    Stock stock = sharedStockList.get(stockSymbol);
                    // if the stock was not found (null)
                    if (stock == null)
                    {
                        System.out.println(stockSymbol);
                        stock = new DayStock(stockSymbol);
                        sharedStockList.put(stockSymbol, stock);
                    }

                    localStockList.add(stock);
                }
                String channelId = removeQuotes(ob.get("ChannelId").toString());
                System.out.println(channelId);
                TextChannel textChannel = config.getBot().getTextChannelById(channelId);
                MessageEmbed[] messageEmbeds = dayEmbed.get(localStockList);
                for (MessageEmbed embed : messageEmbeds)
                {
//                    making sure nothing is null
                    assert embed != null;
                    assert textChannel != null;

                    textChannel.sendMessage(embed).queue();
                }

                textChannel.sendMessage("◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━━◦○◦━◦○◦━◦○◦━◦").queue();
            }
        }
    }
    private String removeQuotes(String string)
    {
        return string.replace("\"", "");
    }
}
