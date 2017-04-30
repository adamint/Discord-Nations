package execution;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class BotException {
    public BotException(Exception ex) {
        ex.printStackTrace();
        botLogsShard.help.sendTranslatedMessage("```" + ExceptionUtils.getStackTrace(ex) + "```", botLogsShard.jda
                .getTextChannelById("270572632343183361"), null);
    }

    public BotException(String s) {
        botLogsShard.help.sendTranslatedMessage("```" + s + "```", botLogsShard.jda.getTextChannelById
                ("270572632343183361"), null);
    }
}
