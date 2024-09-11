package com.zajkop.airow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class DataOutliersCleaner {

    private static final double OUTLIERS_DETECTING_MULTIPLIER = 1.5;
    private static final int Q3_PERCENTILE = 75;
    private static final int Q1_PERCENTILE = 25;

    static Stream<Integer> cleanOutliers(List<Integer> values) {
        var sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);

        double q1 = getPercentile(sortedValues, Q1_PERCENTILE);
        double q3 = getPercentile(sortedValues, Q3_PERCENTILE);
        double iqr = q3 - q1;
        double lowerBound = q1 - (OUTLIERS_DETECTING_MULTIPLIER * iqr);
        double upperBound = q3 + (OUTLIERS_DETECTING_MULTIPLIER * iqr);

        return values.stream()
                .filter(value -> value >= lowerBound && value <= upperBound);
    }

    private static double getPercentile(List<Integer> values, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * values.size());
        return values.get(index - 1);
    }
}
