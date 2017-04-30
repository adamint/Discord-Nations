package execution;

import com.vdurmont.emoji.EmojiParser;
import lombok.NonNull;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.time.Instant;
import java.util.HashMap;

public abstract class Command {
    private int time = 0;
    private String[] aliases;
    private String description;
    private String usage;
    private HashMap<String, Long> ratelimited = new HashMap<>();

    public void sm(@NonNull String content, @NonNull TextChannel channel, @NonNull User user) {
        try {
            if (content.length() <= 2000) {
                channel.sendMessage(content).queue();
            }
            else {
                for (int i = 0; i < content.length(); i += 2000) {
                    if ((i + 2000) <= content.length()) {
                        channel.sendMessage(content.substring(i, i + 2000)).queue();
                    }
                    else {
                        channel.sendMessage(content.substring(i, content.length() - 1)).queue();
                    }
                }
            }
        }
        catch (PermissionException ex) {
            sendFailed(user, false);
        }
    }

    public Message sendEmbed(EmbedBuilder embedBuilder, TextChannel channel, User user, String... reactions) {
        try {
            Message message = channel.sendMessage(embedBuilder.build()).complete();
            for (String reaction : reactions) {
                message.addReaction(EmojiParser.parseToUnicode(reaction)).queue();
            }
            return message;
        }
        catch (PermissionException ex) {
            sendFailed(user, true);
        }
        return null;
    }

    protected void sendFailed(User user, boolean embed) {
        if (user != null) {
            user.openPrivateChannel().queue(privateChannel -> {
                try {
                    if (!embed) {
                        privateChannel.sendMessage("I don't have permission to type in this channel!").queue();
                    }
                    else {
                        privateChannel.sendMessage("I don't have permission to send embeds in this channel!").queue();
                    }
                }
                catch (Exception e) {
                    new BotException(e);
                }
            });
        }
    }

    public Command(String... aliases) {
        this.aliases = aliases;
    }

    public Command setUsage(String s) {
        usage = s;
        return this;
    }

    public Command setDescription(String s) {
        description = s;
        return this;
    }

    public Command setRatelimit(int seconds) {
        this.time = seconds;
        return this;
    }

    public boolean containsAlias(String s) {
        for (String q : aliases) if (s.equalsIgnoreCase(q)) return true;
        return false;
    }

    public void pass(Guild guild, TextChannel channel, User user, String[] args, Message message) {
        if (time > 0) {
            long now = Instant.now().getEpochSecond();
            if (ratelimited.get(user.getId()) != null) {
                if (ratelimited.get(user.getId()) > now) {

                }
            }
            else ratelimited.remove(user.getId());
        }
        onUsage(guild, channel, user, args, message);
    }

    public abstract void onUsage(Guild guild, TextChannel channel, User user, String[] args, Message message)
}
