package com.zajkop.airow;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class DataProcessorTest {

    @Test
    void shouldProduceExpectedOutputData() throws IOException {
        var summary = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("summary.json")).getFile());
        var samples = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("samples.json")).getFile());
        var laps = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("laps.json")).getFile());
        var expectedOutput = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("output.json")).getFile());
        var dataProcessor = new DataProcessor(summary, laps, samples);


        dataProcessor.process();

        var expected = new ObjectMapper().readValue(expectedOutput, ConsolidatedData.class);
        var actual = new ObjectMapper().readValue(new File("build/generated/output.json"), ConsolidatedData.class);
        assertThat(actual).isEqualTo(expected);
    }
}
