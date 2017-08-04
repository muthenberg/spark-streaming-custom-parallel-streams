package de.cyface;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public final class Application {
    
    static RandomReceiver secondReceiver = new RandomReceiver(1000L);
    static RandomReceiver twoHundredHertzReceiver = new RandomReceiver(2000L);

    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setAppName("Test").setMaster("local[2]");
        JavaStreamingContext sc = new JavaStreamingContext(conf, new Duration(1000));
        JavaDStream<Integer> stream1 = startStream(sc, 1000L);
//        JavaDStream<Integer> stream2 = startStream(sc, 2000L);
        
        stream1.print();
        
        sc.start();
        sc.awaitTermination();
    }
    
    static JavaDStream<Integer> startStream(final JavaStreamingContext sc, final long interval) {
        RandomReceiver receiver = new RandomReceiver(interval);
        JavaReceiverInputDStream<Integer> stream = sc.receiverStream(receiver);
        return stream;
    }

}
