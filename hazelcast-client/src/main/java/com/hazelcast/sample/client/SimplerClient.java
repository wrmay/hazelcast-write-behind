package com.hazelcast.sample.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimplerClient {



    public static void main(String []args) {
        String clientName = args[0];
        DateFormat fmt = new SimpleDateFormat("kk:mm:ss.SSS");

        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        IMap<String, String> testMap = hz.getMap("test");

        String now = fmt.format(new Date());
        testMap.put("test", String.format("%s  %s", clientName, now));

//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            String now = fmt.format(new Date());
//            testMap.put("test", String.format("%s  %s", clientName, now));
//        }, 0, 1, TimeUnit.SECONDS);

        hz.shutdown();
    }

}
