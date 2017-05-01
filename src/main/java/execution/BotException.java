package execution;

import org.apache.commons.lang3.exception.ExceptionUtils;

import static main.DiscordNations.botLogsShard;

public class BotException {
    public BotException(Exception ex) {
        ex.printStackTrace();
        botLogsShard.jda.getTextChannelById("308381860327718912").sendMessage("```" + ExceptionUtils.getStackTrace(ex) + "```")
                .queue();
    }

    public BotException(String s) {
        botLogsShard.jda.getTextChannelById("308381860327718912").sendMessage("```" + s + "```")
                .queue();
    }
}
