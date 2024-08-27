package com.golf.app.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class PlayerUtils {

    private PlayerUtils() {
    }

    public static String capitalizeName(String name) {
        return Arrays.stream(name.split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
            .collect(Collectors.joining(" "));
    }

    public static double calculateHandicapIndex(int score, double courseRating, int slopeRating) {
        if (score <= 0) {
            throw new IllegalArgumentException("Score must be greater than zero.");
        }

        double adjustedGrossScore = score;
        double handicapDifferential = ((adjustedGrossScore - courseRating) * 113) / slopeRating;
        return handicapDifferential;
    }
}
