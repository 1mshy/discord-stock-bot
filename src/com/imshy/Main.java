package com.imshy;

import com.imshy.Discord.Bot;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends ListenerAdapter
{
    public static final char BOTCHAR = '.';

    public static void main(String[] args) throws LoginException, InterruptedException, IOException
    {
        Config config = new Config();
        config.setArgs(args);
        /*Token is the first argument given*/
        Bot bot = new Bot();
        bot.init(args[0]);
    }
}