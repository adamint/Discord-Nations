package main;

public class DiscordNations {
    public static Shard botLogsShard;
    public static int shardCount = 2;

    public static void main(String[] args) throws Exception {
        ShardManager.register(shardCount);
    }
}
