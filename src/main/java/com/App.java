package com;

import com.Runners.ClientRunner;

import java.util.Random;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {

        var min = 50;
        var max = 1000;

        var MAX_CLIENTS = 100;
        var pool = Executors.newFixedThreadPool(MAX_CLIENTS);

        for (int i = 0; i < MAX_CLIENTS; i++) {
            var random = new Random();
            var rounds = random.nextInt(max - min) + min;
            var runner = new ClientRunner(rounds);

            pool.submit(runner);
        }

        pool.shutdown();
    }
}
