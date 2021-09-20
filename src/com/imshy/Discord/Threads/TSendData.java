package com.imshy.Discord.Threads;

import com.imshy.Config;
import com.imshy.Discord.Embeds.DayEmbed;
import com.imshy.Discord.SendData;
import com.imshy.Main;
import com.imshy.Stock.DayStock;
import com.imshy.Stock.Stock;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.sql.Time;

public class TSendData extends Thread
{
    private final long minutes;
    public TSendData(long minutes)
    {
        this.minutes=minutes;
    }

    @Override
    public void run()
    {
        while(this.isAlive())
        {


            SendData sendData = new SendData();
            sendData.sendData();
            try
            {
                sleep(4);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
