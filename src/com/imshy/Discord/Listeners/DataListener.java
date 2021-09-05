package com.imshy.Discord.Listeners;

import com.imshy.Discord.ChannelManager;
import com.imshy.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DataListener extends ListenerAdapter {
    private final int DEFAULTTIME = 1440;
    private final ChannelManager channelManager = new ChannelManager();
    private final String HELPMESSAGE = "Sorry, but the command format you entered was incorrect.\nTry using: **.add/remove stock**";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // checks to see if user is a bot (ignored)
        if (event.getAuthor().isBot())
            return;
        // splits up the message into an array to easily use params
        String[] msgArr = event.getMessage().getContentDisplay().split(" ");
        //checks to see if command was **.add** or **.remove**

        if (msgArr.length != 2) {
            event.getChannel().sendMessage(HELPMESSAGE).queue();
            return;
        }

        String stock = msgArr[1];
        switch (msgArr[0]) {

            case Main.BOTCHAR + "add": {
                String channelId = event.getChannel().getId();
                channelManager.updateList(channelId, stock);
                break;
            }

            case Main.BOTCHAR + "rm":
            case Main.BOTCHAR + "remove": {
                String channelId = event.getChannel().getId();

                channelManager.removeFromList(channelId, stock);
            }
        }
    }
}
