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

import java.util.*;

public class SendDataAlpha implements ISendData
{
    public void sendData()
    {
        // stock symbol / stock variable
        HashMap<String, Stock> sharedStockList = new HashMap<>();
        final DayEmbed dayEmbed = new DayEmbed();
        final ChannelManager cm = new ChannelManager();
        final Config config = new Config();

/*
        This way of reading the info is shit, but changing it would require recoding
        my storage system from start
*/
        // get the json object as an entry set
        Set<Map.Entry<String, JsonElement>> x = cm.getChannelData().entrySet();
        int i = 1;
        for (Map.Entry<String, JsonElement> er : x)
        {
            List<Stock> localStockList = new ArrayList<>();
            {
                // converts the entry set to a json object of the channel with this format:
//          Ex: {"StockList":["tsla"],"Time":1440,"ChannelId":"880958485821550613"}
                JsonObject ob = new JsonParser().parse(er.getValue().toString()).getAsJsonObject();
//               This loop will get all stocks into the localStockList
                for (JsonElement stockElement : ob.get("StockList").getAsJsonArray())
                {
                    if(i!=0 && i%5==0 )
                    {
                        try
                        {
                            wait(303000);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    // stock symbol is given as  ""tsla"" and transformed to "tsla"
                    String stockSymbol = removeQuotes(stockElement.toString());
                    // try to find the stock symbol in the list of stocks
                    Stock stock = sharedStockList.get(stockSymbol);

                    System.out.println(stock);
                    // if the stock was not found (null)
                    if (stock == null)
                    {
//                        System.out.println(stockSymbol);
                        stock = new DayStock(stockSymbol);
                        sharedStockList.put(stockSymbol, stock);

                        i++;
                    }

                    localStockList.add(stock);
                }
                String channelId = removeQuotes(ob.get("ChannelId").toString());
                TextChannel textChannel = config.getBot().getTextChannelById(channelId);
                MessageEmbed[] messageEmbeds = dayEmbed.get(localStockList);
                
                for (MessageEmbed embed : messageEmbeds)
                {
//                    making sure nothing is null
                    assert embed != null;
                    assert textChannel != null;

                    textChannel.sendMessage(embed).queue();
                }
                System.out.println(sharedStockList);
                textChannel.sendMessage("◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━━◦○◦━◦○◦━◦○◦━◦").queue();
            }
//            for(Stock s : localStockList)
//            {
//                sharedStockList.put(s.stockSymbol, s);
//            }

        }
    }
    private String removeQuotes(String string)
    {
        return string.replace("\"", "");
    }
}
