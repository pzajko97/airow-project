package com.zajkop.airow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zajkop.airow.ConsolidatedData.ActivityOverview;
import com.zajkop.airow.ConsolidatedData.LapData;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
class DataProcessor {

    private static final String HEART_RATE_SAMPLE_TYPE = "2";
    private final File summaryFile;
    private final File lapsFile;
    private final File samplesFile;

    public void process() throws IOException {
        var summary = DataLoader.loadSummary(summaryFile);
        var laps = DataLoader.loadLaps(lapsFile);
        var samples = getHeartRateSamples(DataLoader.loadSamples(samplesFile));

        List<LapData> lapsData = new ArrayList<>();
        for (int i = 0; i < laps.size(); i++) {
            var lap = laps.get(i);
            int sampleIndex = i == 0 ? i : i + 1;
            lapsData.add(LapData.builder()
                    .startTime(lap.startTimeInSeconds())
                    .distance(lap.totalDistanceInMeters())
                    .duration(lap.timerDurationInSeconds())
                    .detailedHeartRateSamples(samples.stream().filter(sample -> sample.index() == sampleIndex || sample.index() == sampleIndex + 1).toList())
                    .build());
        }

        ConsolidatedData consolidatedData = ConsolidatedData.builder()
                .activityOverview(ActivityOverview.builder()
                        .userId(summary.userId())
                        .type(summary.activityType())
                        .device(summary.deviceName())
                        .maxHeartRate(summary.maxHeartRateInBeatsPerMinute())
                        .duration(summary.durationInSeconds())
                        .build())
                .lapsData(lapsData)
                .build();

        new ObjectMapper().writer().writeValue(new File("build/generated/output.json"), consolidatedData);
    }

    private List<DetailedHeartRateSample> getHeartRateSamples(List<Sample> hRSamples) {
        hRSamples = hRSamples.stream().filter(sample -> sample.sampleType().equals(HEART_RATE_SAMPLE_TYPE)).toList();
        List<DetailedHeartRateSample> heartRateSamples = new ArrayList<>();
        for (int i = 0; i < hRSamples.size(); i++) {
            var index = i;
            var sampleHRs = Arrays.stream(hRSamples.get(index).data().split(","))
                    .filter(str -> !str.equalsIgnoreCase("null"))
                    .map(Integer::parseInt).toList();
            DataOutliersCleaner.cleanOutliers(sampleHRs)
                    .map(hR -> new DetailedHeartRateSample(index, hR)).forEach(heartRateSamples::add);
        }
        return heartRateSamples;
    }
}
