package com.imshy.Discord.Listeners;

import com.google.gson.*;
import com.imshy.Discord.ChannelManager;
import com.imshy.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DailyListManager extends ListenerAdapter
{
    private final int DEFAULTTIME = 1440;
    private final ChannelManager channelManager = new ChannelManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // checks to see if user is a bot (ignored)
        if (event.getAuthor().isBot())
            return;
        // splits up the message into an array to easily use params
        String[] msgArr = event.getMessage().getContentDisplay().split(" ");
        //checks to see if command was **.add** or **.remove**
        // if so, th
        if (!(msgArr[0].equals(Main.BOTCHAR + "add") || (msgArr[0].equals(Main.BOTCHAR + "remove"))))
            return;

        String channelId = event.getChannel().getId();
        JsonObject obj = channelManager.getChannelData();


        if (msgArr[0].equals(Main.BOTCHAR + "add"))
        {

            JsonElement channelElement = obj.get(channelId);
            if(channelElement==null)
            {
                obj = new JsonObject();
                obj.add(channelId, channelManager.createDefaultObject(channelId, DEFAULTTIME));
            }

            JsonObject channelObject = obj.get(channelId).getAsJsonObject();

            JsonArray stockList = channelObject.get("StockList").getAsJsonArray();
            stockList.add(msgArr[1]);

            channelManager.addToList(channelObject);
        }
    }
}
