package execution;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;

public class CommandFactory {
    private ArrayList<Command> commands = new ArrayList<>();

    public CommandFactory() {
    }

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public void pass(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        Guild guild = event.getGuild();
        Message message = event.getMessage();
        String[] rawArgs = message.getRawContent().split(" ");
        if (rawArgs.length > 0) {
            if (rawArgs[0].equalsIgnoreCase("nations")) {
                commands.forEach(command -> {
                    if (command.containsAlias(rawArgs[1])) {
                        ArrayList<String> argsList = new ArrayList<>();
                        for (int i = 1; i < rawArgs.length; i++) argsList.add(rawArgs[i]);
                        String[] args = argsList.toArray(new String[argsList.size()]);
                        command.pass(guild, event.getChannel(), user, args, message);
                    }
                });
            }
        }
    }
}
