package com.Utils;

import com.DTOs.RoundDto;
import com.enums.HandEnum;
import com.enums.ResultEnum;

import java.util.Random;

public class GameUtil {
    public static RoundDto generateRound() {
        var random = new Random();
        var handPick = random.nextInt(HandEnum.values().length);
        var resultPick = random.nextInt(HandEnum.values().length);

        var hand = HandEnum.values()[handPick];
        var result = ResultEnum.values()[resultPick];

        var round = new RoundDto();

        round.setGesture(hand);
        round.setResult(result);

        return round;
    }
}
