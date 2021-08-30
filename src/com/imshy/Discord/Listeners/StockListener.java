package com.imshy.Discord.Listeners;

import com.imshy.Discord.Embeds.ShowStockEmbed;
import com.imshy.Main;
import com.imshy.Stock.DayStock;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StockListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot())
            return;

        String[] messageArgs = event.getMessage().getContentDisplay().split(" ");

        if (!messageArgs[0].equals(Main.BOTCHAR + "stock") || messageArgs.length > 2)
            return;

        ShowStockEmbed showStockEmbed = new ShowStockEmbed();
        DayStock stock = new DayStock(messageArgs[1]);
        MessageChannel channel = event.getChannel();
        MessageEmbed embed = showStockEmbed.get(stock);

        channel.sendMessage(embed).queue();
    }
}
