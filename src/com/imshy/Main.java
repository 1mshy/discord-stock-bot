package com.imshy;

import com.imshy.Discord.Bot;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.stream.Stream;

public class Main extends ListenerAdapter
{
    public static final char BOTCHAR = '.';
    public static void main(String[] args) throws LoginException, InterruptedException
    {
        Config config = new Config();
        config.setArgs(args);
        /*Token is the first argument given*/
        Bot bot = new Bot();
        bot.init(args[0]);
    }
}
