package utils;

import main.DiscordNations;
import main.Shard;
import net.dv8tion.jda.core.entities.Guild;

import static main.ShardManager.getShards;

public class GuildUtils {
    public static Guild getGuild(String id) {
        for (Shard shard : getShards()) {
            Guild temp = shard.jda.getGuildById(id);
            if (temp != null) return temp;
        }
        return null;
    }

    public static Shard getShard(int id) {
        for (Shard shard : getShards()) {
            if (shard.getId() == id) {
                return shard;
            }
        }
        return null;
    }

    public static Shard getShard(Guild guild) {
        if (guild == null) return null;
        long bitwise = Long.parseLong(guild.getId()) >> 22;
        long modulus = bitwise % DiscordNations.shardCount;
        int numbered = (int) modulus;
        return getShard(numbered);
    }

}
