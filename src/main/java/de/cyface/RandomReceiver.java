package de.cyface;

import java.util.Random;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

public class RandomReceiver extends Receiver<Integer> {

    private final Random random;
    private final long interval;

    public RandomReceiver(final long interval) {
        super(StorageLevel.MEMORY_AND_DISK_2());
        random = new Random(System.currentTimeMillis());
        this.interval = interval;
    }

    @Override
    public void onStart() {
        System.out.println("Started receiver with interval "+interval+".");
        new Thread(() -> {
            try {
                receive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onStop() {
    }

    private void receive() throws InterruptedException {
        while (!isStopped()) {
            int nextInt = random.nextInt(6);
            System.out.println("Generated "+nextInt+" on Stream with interval "+interval+".");
            store(nextInt);
            Thread.sleep(interval);
        }
    }

}
