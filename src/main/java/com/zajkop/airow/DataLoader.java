package com.zajkop.airow;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

class DataLoader {

    static List<Sample> loadSamples(File file) {
        return loadData(file, new TypeReference<>() {});
    }

    static List<Lap> loadLaps(File file) {
        return loadData(file, new TypeReference<>() {});
    }

    static Summary loadSummary(File file) {
        return loadData(file, new TypeReference<>() {});
    }

    private static <T> T loadData(File file, TypeReference<T> typeReference) {
        try {
            return new ObjectMapper().readValue(file, typeReference);
        } catch (IOException e) {
            throw new DataLoadException("Unable to load data for type: %s, based on file: %s".formatted(typeReference.getType().getTypeName(), file.getName()), e);
        }
    }
}
