package de.HannesGames.HGBot.commands;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import de.HannesGames.HGBot.Main;
import de.HannesGames.HGBot.util.data.Config;

public class Commands {
    public static ICommand test = (user, channel, cmd) -> {
        if (channel.isMod(user)) {
            System.out.println("USER IS MOD");
        } else {
            Main.getBot().sendMessage("Command ist nur für Mods", channel);
            Main.getBot().timeout(user, channel, 1);
        }
    };

    public static ICommand longTimout = (user, channel, cmd) -> {
        if (channel.isMod(user)) {
            Main.getBot().sendMessage("no", channel);
        } else {
            Main.getBot().sendMessage("/me COMMAND IS ONLY FOR MODERATORS @" + user, channel);
            Main.getBot().timeout(user, channel, 10);
        }
    };

    public static ICommand multi = (user, channel, cmd) -> {
        if (channel.isMod(user)) {
            if (Main.getBot().existMulti())
                Main.getBot().sendMessage("/me Der Multistream ist unter " + Config.getMulti() + " zufinden.", channel);
            else
                Main.getBot().sendMessage("/me Es gibt keinen Multistream @" + user, channel);
        } else {
            if (Main.getBot().existMulti())
                Main.getBot().sendMessage("/me Der Multistream ist unter " + Config.getMulti() + " zufinden.", channel);
            else
                Main.getBot().sendMessage("/me Es gibt keinen Multistream. @" + user, channel);
        }
    };

    public static ICommand mitDabei = (user, channel, cmd) -> {
        if (Main.getBot().existMitDabei())
            Main.getBot().sendMessage("Mit dabei ist " + Config.getMitDabei(), channel);
        else
            Main.getBot().sendMessage("Es ist niemand mit dabei", channel);
    };

    public static ICommand slowMode = (user, channel, cmd) -> {
        if (user.isMod(channel)) {
            if (cmd != null && cmd.split(" ").length == 2) {
                String time = cmd.split(" ")[1];
                if (time.equals("0")) {
                    channel.slowOff();
                } else {
                    channel.slowMode(Integer.parseInt(time));
                    Main.getBot().sendMessage("/me @" + user + ", Slowmode set to " + time, channel);
                }
            }
        }
    };
}
