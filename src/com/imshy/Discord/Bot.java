package com.imshy.Discord;

import com.imshy.Config;
import com.imshy.Discord.Listeners.DataListener;
import com.imshy.Discord.Listeners.Ping;
import com.imshy.Discord.Listeners.StockListener;
import com.imshy.Discord.Threads.TSendData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    public void init(String token) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(
                new StockListener(),
                new Ping(),
                new DataListener()
        );

        JDA bot = builder.build().awaitReady();

        Config config = new Config();

        config.setBot(bot);

        new TSendData(3600).start();

    }
}
