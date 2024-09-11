package com.zajkop.airow;

record Summary(String userId, long activityId, String activityName, long durationInSeconds,
               long startTimeInSeconds, long startTimeOffsetInSeconds, String activityType,
               int averageHeartRateInBeatsPerMinute, int activeKilocalories, String deviceName,
               int maxHeartRateInBeatsPerMinute) {
}
