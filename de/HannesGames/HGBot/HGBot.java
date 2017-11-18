package de.HannesGames.HGBot;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;
import com.cavariux.twitchirc.Core.TwitchBot;
import de.HannesGames.HGBot.util.getSecrets;

public class HGBot extends TwitchBot {
    public HGBot() {
        setClientID(getSecrets.getClientID());
        setOauth_Key(getSecrets.getAuthKey());
        setUsername(getSecrets.getUsername());
    }

    @Override
    protected void onMessage(User user, Channel channel, String message) {
        if (message.equalsIgnoreCase("test")) {
            sendMessage("test", channel);
            sendMessage("tesst", channel);
        }
    }
}
