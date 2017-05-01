package main;

import game.TickHandler;

public class DiscordNations {
    public static Shard botLogsShard;
    public static int shardCount = 2;
    public static TickHandler tickHandler = new TickHandler().with(5);
    public static void main(String[] args) throws Exception {
        ShardManager.register(shardCount);

        // Submit runnables to update
    }
}
