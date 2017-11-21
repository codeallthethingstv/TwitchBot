package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;
import de.HannesGames.HGBot.coins.CoinManager;
import de.HannesGames.HGBot.commands.CommandListner;
import de.HannesGames.HGBot.commands.Commands;
import de.HannesGames.HGBot.util.CheckForFilter;
import de.HannesGames.HGBot.util.GetSecrets;
import de.HannesGames.HGBot.util.data.Config;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HGBot extends TwitchBot {
    private CoinManager coinManager;
    private FileHandler fh;
    private CommandListner commandListner;
    private Logger logger = Logger.getLogger(HGBot.class.getName());
    public HGBot() {
        setClientID(GetSecrets.getClientID());
        setOauth_Key(GetSecrets.getAuthKey());
        setUsername(GetSecrets.getUsername());
        initializeCommands();
    }

    public CoinManager getCoinManager() {
        return coinManager;
    }

    private void initializeCommands() {
        commandListner = new CommandListner();
        commandListner.registerCommand("test", Commands.test);
        commandListner.registerCommand("mit", Commands.mitDabei);
        commandListner.registerCommand("multi", Commands.multi);
        commandListner.registerCommand("longtimeout", Commands.longTimout);
        commandListner.registerCommand("slowmode", Commands.slowMode);
        commandListner.registerCommand("coins", Commands.coins);
    }


    private void timeoutslog() {
        try {
            fh = new FileHandler("timeouts.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void banlog() {
        try {
            fh = new FileHandler("bans.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void chatlog() {
        try {
            fh = new FileHandler("chat.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void userJoins(User user, Channel channel) {
        super.userJoins(user, channel);
        if (coinManager == null) {
            coinManager = new CoinManager(channel);
            coinManager.startUp();
        }
        if (coinManager.getCoinsForUser(user) == null) {
            coinManager.setCoins(user, 0.5);
        }
    }

    @Override
    protected void onMessage(User user, Channel channel, String message) {


    }
    @Override
    protected void onCommand(User user, Channel channel, String cmd) {
        commandListner.launchCommand(user, channel, cmd);
    }

    public boolean existMitDabei() {
        return !Objects.equals(Config.getMitDabei(), "niemand");
    }

    public boolean existMulti() {
        return !Objects.equals(Config.getMulti(), "Es gibt keinen Multi-stream");
    }

    public void timeout(User user, Channel channel, int time) {
        timeoutslog();
        channel.timeOut(user, time);
        logger.info("USER HAS GOT A TIMEOUT FOR " + time + " USER: " + user);
    }

    public void ban(User user, Channel channel, String reason) {
        banlog();
        channel.ban(user);
        logger.info("User: " + user + " was BANNED for: " + reason);
    }
}
