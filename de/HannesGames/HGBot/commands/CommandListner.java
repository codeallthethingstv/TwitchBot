package de.HannesGames.HGBot.commands;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;

import java.util.HashMap;

/**
 * Class written by felix
 */

public class CommandListner {
    private HashMap<String, ICommand> commands;

    public CommandListner() {
        commands = new HashMap<>();
    }

    /**
     * Use this for adding a command
     *
     * @param invokeCommand Command when it is invoked.
     * @param iCommand      Code what should happen.
     * @return if it is succesful
     */
    public boolean registerCommand(String invokeCommand, ICommand iCommand) {
        if (!commands.containsKey(invokeCommand)) {
            commands.put(invokeCommand, iCommand);
            return commands.containsKey(invokeCommand);
        }
        return false;
    }

    /**
     * Launch command in own thread
     *
     * @param user    User which executed
     * @param channel Channel where executed
     * @param command Complete command
     * @return Succesful
     */
    public boolean launchCommandAsync(User user, Channel channel, String command) {
        ICommand iCommand = getICommand(command);
        if (iCommand == null) return false;
        new Thread(() -> iCommand.execute(user, channel, command)).start();
        return true;
    }

    /**
     * Launch command in main Thread
     *
     * @param user    User which executed
     * @param channel Channel where executed
     * @param command Complete command
     * @return Succesful
     */
    public boolean launchCommand(User user, Channel channel, String command) {
        ICommand iCommand = getICommand(command);
        if (iCommand == null) return false;
        iCommand.execute(user, channel, command);
        return true;
    }

    private ICommand getICommand(String command) {
        //TODO Not only split by space
        if (command.contains(" ")) {
            return commands.get(command.split(" ")[0]);
        } else {
            return commands.get(command);
        }
    }
}
