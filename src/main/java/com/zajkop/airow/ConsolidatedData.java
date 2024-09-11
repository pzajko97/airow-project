package com.zajkop.airow;

import lombok.Builder;

import java.util.List;

@Builder
record ConsolidatedData(ActivityOverview activityOverview, List<LapData> lapsData) {

    @Builder
    record ActivityOverview(String userId, String type, String device, int maxHeartRate, long duration) {
    }

    @Builder
    record LapData(long startTime, int distance, long duration,
                   List<DetailedHeartRateSample> detailedHeartRateSamples) {
    }
}
