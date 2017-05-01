package game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickHandler {
    private int timesPerSecond;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    public TickHandler with(int amount) {
        this.timesPerSecond = amount;
        return this;
    }

    public TickHandler submit(Runnable r) {
        executorService.scheduleWithFixedDelay(r, 1000, 1000 / timesPerSecond, TimeUnit.MILLISECONDS);
        return this;
    }

    public TickHandler submit(Runnable r, int tps) {
        executorService.scheduleWithFixedDelay(r, 1000, 1000 / tps, TimeUnit.MILLISECONDS);
        return this;
    }

    public TickHandler() {
    }
}
