package io.github.hannesgames.hgbot.commands;

import com.cavariux.twitchirc.Chat.*;

public interface ICommand {
    void execute(User user, Channel channel, String cmd);
}
