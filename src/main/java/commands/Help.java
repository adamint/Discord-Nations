package commands;

import execution.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class Help extends Command {
    @Override
    public void onUsage(Guild guild, TextChannel channel, User user, String[] args, Message message) {
        sendMessage("**Discord Nations** is a Discord MOBA where the primary goal is to work with your teammates (server) " +
                "to become the most powerful server in the world. Fight other servers through battles, make alliances, and train your " +
                "skills and armies - can you be #1?", channel, user);
    }

}
