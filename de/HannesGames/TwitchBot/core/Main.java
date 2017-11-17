package de.HannesGames.TwitchBot.core;

import com.cavariux.twitchirc.Core.TwitchBot;
import de.HannesGames.TwitchBot.util.data.getSecrets;
import com.cavariux.twitchirc.Chat.Channel;

public class Main extends TwitchBot{
    public static TwitchBot bot = new TwitchBot();
    public static Channel channel = bot.joinChannel("#channel");
    public static void main(String[] args){
        bot.setUsername(getSecrets.getUsername());
        bot.setOauth_Key(getSecrets.getAuthKey());
        bot.setClientID(getSecrets.getClientID());
        bot.connect();
        bot.joinChannel("#hannesgames_");
        bot.start();
    }
}
