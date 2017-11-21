package io.github.hannesgames.hgbot;

import com.cavariux.twitchirc.Chat.Channel;

import io.github.hannesgames.hgbot.util.data.Config;
import io.github.hannesgames.hgbot.util.data.xml.Configs;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    private static HGBot bot;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        /*
         * adding settings XML!
         * a simple way to do this!
         */
        /*new Settings().save();*/
        new Configs().read();
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
