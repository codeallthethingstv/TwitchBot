package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;
import de.HannesGames.HGBot.util.GetSecrets;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HGBot extends TwitchBot {
    FileHandler fh;
    private Logger logger = Logger.getLogger(HGBot.class.getName());
    public HGBot() {
        setClientID(GetSecrets.getClientID());
        setOauth_Key(GetSecrets.getAuthKey());
        setUsername(GetSecrets.getUsername());
    }

    private void timeoutslog() {
        FileHandler fh;
        try {
            fh = new FileHandler("D:/java/TwitchBot/logs/timeouts.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void banlog() {
        FileHandler fh;
        try {
            fh = new FileHandler("D:/java/TwitchBot/logs/bans.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onMessage(User user, Channel channel, String message) {
        timeoutslog();
        banlog();
        if (channel.isMod(user)) {
            System.out.println("USER IS MOD");
        } else {
            logger.info("USER HAS GOT A TIMEOUT FOR 1 SECOND  \n USER: " + user);
        }
    }

    @Override
    protected void onCommand(User user, Channel channel, String cmd) {
        timeoutslog();

        if (cmd.equalsIgnoreCase("test")) {
            if (channel.isMod(user)) {
                System.out.println("USER IS MOD");
            } else {
                sendMessage("Command ist nur für Mods", channel);
                channel.timeOut(user, 1);
                logger.info("USER HAS GOT A TIMEOUT FOR 1 SECOND  \n USER: " + user);
                System.out.println("USER HAS GOT A TIMEOUT FOR 1 SECOND  \n USER: " + user);
            }
        }
    }
}
