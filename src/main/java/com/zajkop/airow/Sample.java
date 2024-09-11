package com.zajkop.airow;

import com.fasterxml.jackson.annotation.JsonProperty;

record Sample(@JsonProperty("recording-rate") int recordingRate, @JsonProperty("sample-type") String sampleType,
              String data) {
}
