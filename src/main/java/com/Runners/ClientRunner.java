package com.Runners;

import com.RestClient;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.concurrent.Executors;

public class ClientRunner implements Runnable {
    private final int rounds;

    public ClientRunner(int rounds) {

        this.rounds = rounds;
    }

    @Override
    public void run() {
        var client = new RestClient();
        var pool = Executors.newCachedThreadPool();

        try {
            client.registerUser();
            Thread.sleep(1000);
            client.loginUser();

            if (client.authToken != null) {
                System.out.println("Starting user: " + client.username);

                for (int i = 0; i < this.rounds; i++) {
                    pool.submit(() -> {
                        try {
                            client.playRound();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else {
                throw new LoginException("401, Something went south!");
            }
        } catch (IOException | InterruptedException | LoginException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
