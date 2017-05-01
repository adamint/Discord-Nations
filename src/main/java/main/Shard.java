package main;

import commands.Help;
import events.OnMessage;
import execution.CommandFactory;
import lombok.Getter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import org.apache.commons.io.IOUtils;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Shard {
    private int shardNumber;
    @Getter
    private int shardCount;
    public JDA jda;
    public CommandFactory factory = new CommandFactory();

    public Shard(int shardNumber, int shardCount) throws IOException, LoginException, InterruptedException, RateLimitedException {
        this.shardNumber = shardNumber;
        this.shardCount = shardCount;
        jda = new JDABuilder(AccountType.BOT)
                .setEventManager(new AnnotatedEventManager())
                .setToken(IOUtils.toString(new FileInputStream(new File("C:\\Users\\AMR\\Desktop\\discordnations.key"))))
                .setAutoReconnect(true)
                .setAudioEnabled(false)
                .setGame(Game.of("Type nations help"))
                .setStatus(OnlineStatus.ONLINE)
                .setBulkDeleteSplittingEnabled(true)
                .setEnableShutdownHook(true)
                .useSharding(shardNumber, shardCount)
                .buildBlocking();
        if (jda.getGuildById("260841592070340609") != null) DiscordNations.botLogsShard = this;
        jda.addEventListener(new OnMessage());

        factory.addCommand(new Help().with("help").setRatelimit(5).setDescription("Understand what Discord Nations is about").setUsage
                ("help"));
    }

    public int getId() {
        return shardNumber;
    }
}
