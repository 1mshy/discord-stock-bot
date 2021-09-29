package com.imshy.Discord.Threads;

import com.imshy.Discord.ISendData;
import com.imshy.Discord.SendDataAlpha;

public class TSendData extends Thread
{
    @Override
    public void run()
    {
//        while(this.isAlive())
        {


            ISendData sendDataAlpha = new SendDataAlpha();
            sendDataAlpha.sendData();
        }

    }
}
