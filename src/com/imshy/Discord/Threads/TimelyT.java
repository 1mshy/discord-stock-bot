package com.imshy.Discord.Threads;

import com.imshy.Config;
import com.imshy.Discord.Embeds.DayEmbed;
import com.imshy.Main;
import com.imshy.Stock.DayStock;
import com.imshy.Stock.Stock;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class TimelyT extends Thread
{
    private final long minutes;
    public TimelyT(long minutes)
    {
        this.minutes=minutes;
    }

    @Override
    public void run()
    {
        while (true)
        {
            Config config = new Config();
            Stock[] stockArr = new Stock[config.getArgs().length - 1];

            for (int i = 1; i < config.getArgs().length; i++)
            {
                Stock s = new DayStock(config.getArgs()[i]);
                stockArr[i - 1] = s;
            }

            DayEmbed dayEmbed = new DayEmbed();
            MessageEmbed[] messageEmbeds = dayEmbed.get(stockArr);
            for (MessageEmbed embed : messageEmbeds)
            {
                assert embed!=null;
                config.getDayChannel().sendMessage(embed).queue();
            }

            config.getDayChannel().sendMessage("◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━◦○◦━━◦○◦━◦○◦━◦○◦━◦").queue();
            try
            {
                // 60000 is the amount of milliseconds in a minute
                Thread.sleep(minutes*60000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}