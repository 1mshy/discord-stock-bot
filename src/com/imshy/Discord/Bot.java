package com.imshy.Discord;

import com.imshy.Config;
import com.imshy.Discord.Embeds.DayEmbed;
import com.imshy.Discord.Listeners.*;
import com.imshy.Discord.Threads.DayThread;
import com.imshy.Main;
import com.imshy.Stock.DayStock;
import com.imshy.Stock.Stock;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter
{
    public void init(String token) throws LoginException, InterruptedException
    {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(
                new StockListener(),
                new Ping()
        );

        JDA bot = builder.build().awaitReady();

        Config config = new Config();

        config.setBot(bot);
        config.setDayChannel(bot.getTextChannelById(881305539722768436L));

        new DayThread().start();
    }
}
