package com.mjm.apple.filter;

import lombok.Data;

/**
 * @author majun
 * @date 2018/10/24 22:14
 */
@Data
public class Apple {

    private String color;

    private Double weight;

    public Apple() {}

    public Apple(String color) {
        this.color = color;
    }

    public Apple(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Double weight) {
        this.weight = weight;
    }
}
