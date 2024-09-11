package com.zajkop.airow;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DataLoaderTest {

    @Test
    void shouldLoadSampleData() {
        var file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("samples.json")).getFile());

        var samples = DataLoader.loadSamples(file);

        assertThat(samples).hasSize(8);
    }

    @Test
    void shouldLoadLapData() {
        var file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("laps.json")).getFile());

        var laps = DataLoader.loadLaps(file);

        assertThat(laps).hasSize(2);
    }

    @Test
    void shouldLoadSummaryData() {
        var file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("summary.json")).getFile());

        var summary = DataLoader.loadSummary(file);

        assertThat(summary.userId()).isEqualTo("1234567890");
        assertThat(summary.activityId()).isEqualTo(9480958402L);
        assertThat(summary.activityName()).isEqualTo("Indoor Cycling");
        assertThat(summary.durationInSeconds()).isEqualTo(3667);
        assertThat(summary.startTimeInSeconds()).isEqualTo(1661158927);
        assertThat(summary.startTimeOffsetInSeconds()).isEqualTo(7200);
        assertThat(summary.activityType()).isEqualTo("INDOOR_CYCLING");
        assertThat(summary.averageHeartRateInBeatsPerMinute()).isEqualTo(150);
        assertThat(summary.activeKilocalories()).isEqualTo(561);
        assertThat(summary.deviceName()).isEqualTo("instinct2");
        assertThat(summary.maxHeartRateInBeatsPerMinute()).isEqualTo(190);
    }
}
