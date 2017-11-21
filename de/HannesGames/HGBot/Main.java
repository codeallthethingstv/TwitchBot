package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import de.HannesGames.HGBot.util.data.Config;

public class Main {
    private static HGBot bot;
    public static void main(String[] args) {
        /**
         * adding settings XML!
         * a simple way to do this!
         */
        /*  try {
            new Settings().save();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }*/
        bot = new HGBot();
        bot.connect();
        Channel channel = bot.joinChannel("#" + Config.getTwitchAccount());
        bot.sendMessage("HannesGames Bot started!", channel);
        bot.start();
    }

    public static HGBot getBot() {
        return bot;
    }
}
