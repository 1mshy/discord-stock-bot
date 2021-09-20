package com.imshy;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.imshy.Discord.Bot;
import com.imshy.Discord.ChannelManager;
import com.imshy.Discord.SendData;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Main extends ListenerAdapter
{
    public static final char BOTCHAR = '.';

    public static void main(String[] args)
    {
        final String TOKEN = args[0];

        if(TOKEN == null)
        {
            System.out.println("the first argument should be the discord bot token");
            System.exit(0);
        }


        Config config = new Config();
        config.setArgs(args);
        /*Token is the first argument given*/
        Bot bot = new Bot();

        try
        {
            bot.init(TOKEN);
        } catch (LoginException e)
        {
            System.out.println("There was an error logging in, incorrect login token?");
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(Time.valueOf("9:02:20"));
    }
}
