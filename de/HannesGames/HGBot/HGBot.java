package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;
import de.HannesGames.HGBot.util.CheckForFilter;
import de.HannesGames.HGBot.util.GetSecrets;
import de.HannesGames.HGBot.util.data.Config;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HGBot extends TwitchBot {
    private FileHandler fh;
    private Logger logger = Logger.getLogger(HGBot.class.getName());
    public HGBot() {
        setClientID(GetSecrets.getClientID());
        setOauth_Key(GetSecrets.getAuthKey());
        setUsername(GetSecrets.getUsername());
    }

    private void timeoutslog() {
        try {
            fh = new FileHandler("D:/java/TwitchBot/logs/timeouts/timeouts.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void banlog() {
        try {
            fh = new FileHandler("D:/java/TwitchBot/logs/bans/bans.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void chatlog() {
        try {
            fh = new FileHandler("D:/java/TwitchBot/logs/chat/chat.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onMessage(User user, Channel channel, String message) {
        if (message.length() >= 1) {
            chatlog();
            new CheckForFilter(message);
        }
    }
    @Override
    protected void onCommand(User user, Channel channel, String cmd) {
        banlog();
        if (cmd.equalsIgnoreCase("test")) {
            if (channel.isMod(user)) {
                System.out.println("USER IS MOD");
            } else {
                sendMessage("Command ist nur für Mods", channel);
                timeout(user, channel, 1);
            }
        } else if (cmd.equalsIgnoreCase("longtimeout")) {
            if (channel.isMod(user)) {
                sendMessage("no", channel);
            } else {
                sendMessage("/me COMMAND IS ONLY FOR MODERATORS @" + user, channel);
                timeout(user, channel, 10);
            }
        } else if (cmd.equalsIgnoreCase("multi")) {
            if (channel.isMod(user)) {
                if (existMulti())
                    sendMessage("/me Der Multistream ist unter " + Config.getMulti() + " zufinden.", channel);
                else
                    sendMessage("/me Es gibt keinen Multistream @" + user, channel);
            } else {
                if (existMulti())
                    sendMessage("/me Der Multistream ist unter " + Config.getMulti() + " zufinden.", channel);
                else
                    sendMessage("/me Es gibt keinen Multistream. @" + user, channel);
            }
        } else if (cmd.equalsIgnoreCase("mit dabei")) {
            if (existMitDabei())
                sendMessage("Mit dabei ist " + Config.getMitDabei(), channel);
            else
                sendMessage("Es ist niemand mit dabei", channel);
        }
    }

    private boolean existMitDabei() {
        return !Objects.equals(Config.getMitDabei(), "niemand");
    }

    private boolean existMulti() {
        return !Objects.equals(Config.getMulti(), "Es gibt keinen Multi-stream");
    }

    private void timeout(User user, Channel channel, int time) {
        timeoutslog();
        channel.timeOut(user, time);
        logger.info("USER HAS GOT A TIMEOUT FOR " + time + " USER: " + user);
    }

    private void ban(User user, Channel channel, String reason) {
        banlog();
        channel.ban(user);
        logger.info("User: " + user + " was BANNED for: " + reason);
    }
}
