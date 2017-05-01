package main;

public class ShardManager {
    private static Shard[] shards;

    static void register(int numberOfShards) throws Exception {
        shards = new Shard[numberOfShards];
        for (int i = 0; i < numberOfShards; i++) {
            Shard shard = new Shard(i, numberOfShards);
            shards[i] = shard;
        }
    }

    public static Shard[] getShards() {
        return shards;
    }
}