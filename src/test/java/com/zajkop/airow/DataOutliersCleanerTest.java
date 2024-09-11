package com.zajkop.airow;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataOutliersCleanerTest {

    @Test
    void shouldCleanOutliers() {
        var values = List.of(9, 9, 10, 10, 10, 11, 12, 36);

        var cleanedValues = DataOutliersCleaner.cleanOutliers(values).toList();

        assertThat(cleanedValues).doesNotContain(36);
    }
}
