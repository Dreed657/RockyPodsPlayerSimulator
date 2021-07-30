package com.DTOs;

import com.enums.HandEnum;
import com.enums.ResultEnum;

public class RoundDto {
    private ResultEnum result;
    private HandEnum gesture;

    public HandEnum getGesture() {
        return gesture;
    }

    public void setGesture(HandEnum gesture) {
        this.gesture = gesture;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }
}
