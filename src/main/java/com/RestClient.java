package com;

import com.DTOs.RegisterResponseDto;
import com.DTOs.RoundDto;
import com.DTOs.UserDto;
import com.Utils.GameUtil;
import com.Utils.StringUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.Arrays;

public class RestClient {
    private HttpClient http;

    private final String domain = "http://localhost:";
    private final String port = "5000";
    private final String url = domain + port;

    public String authToken = null;
    public String username;
    private String password;

    public RestClient() {
        this.http = HttpClient.newHttpClient();
    }

    //POST Register User
    public void registerUser() throws IOException, InterruptedException {
        var gson = new Gson();

        this.username = StringUtil.generateRandom(5);
        this.password = StringUtil.generateRandom(10);

        var user = new UserDto(this.username, this.password);

        var data = gson.toJson(user);

//        System.out.println(data);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    //Login User -> token
    public void loginUser() throws IOException, InterruptedException {
        var gson = new Gson();
        var user = new UserDto(this.username, this.password);
        var data = gson.toJson(user);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

        this.authToken = gson.fromJson(response.body(), RegisterResponseDto.class).getToken();
    }

    //Get PlayRound
    public void playRound() throws IOException, InterruptedException {
        var gson = new Gson();
        var round = GameUtil.generateRound();
        var data = gson.toJson(round);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url + "/game/SaveRound"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.authToken)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.printf("%s - %s\n", this.username, response.statusCode());
    }
}
