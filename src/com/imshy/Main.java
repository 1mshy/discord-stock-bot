package com.imshy;

import com.imshy.Discord.Bot;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter
{
    public static final char BOTCHAR = '.';

    public static void main(String[] args) throws LoginException, InterruptedException
    {
//        Config config = new Config();
//        config.setArgs(args);
        /*Token is the first argument given*/
//        Bot bot = new Bot();
//        bot.init(args[0]);
        System.out.println(new Main().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
    }
}