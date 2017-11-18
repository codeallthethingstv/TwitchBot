package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import de.HannesGames.HGBot.util.data.Config;

public class Main {
    public static void main(String[] args) {
        HGBot bot = new HGBot();
        bot.connect();
        Channel channel = bot.joinChannel("#" + Config.getTwitchAccount());
        bot.sendMessage("test", channel);
        bot.start();
    }
}
