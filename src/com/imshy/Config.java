package com.imshy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class Config
{
    static JDA bot;
    static String[] args;

    public String[] getArgs()
    {
        return args;
    }

    public void setArgs(String[] args)
    {
        Config.args = args;
    }

    public JDA getBot()
    {
        return bot;
    }

    public void setBot(JDA bot)
    {
        Config.bot = bot;
    }
}
