package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;

public class Main {
    public static void main(String[] args) {
        HGBot bot = new HGBot();
        bot.connect();
        Channel channel = bot.joinChannel("#hannesgames_");
        bot.sendMessage("test", channel);
        bot.start();
    }
}
