package com.soddik.tasks.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SimpleCsvReaderImpl implements AbstractCsvReader {
    private final Logger logger = Logger.getLogger(SimpleCsvReaderImpl.class.getSimpleName());
    protected final String separator = ";";
    private final File file;

    public SimpleCsvReaderImpl(File file) {
        this.file = file;
    }

    @Override
    public List<String[]> parseFile() {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8))) {
            result = br.lines()
                    .skip(1)
                    .map(line -> {
                        String[] fields = line.split(separator);
                        if (fields.length == 8) {
                            return fields;
                        }
                        return null;
                    }).filter(Objects::nonNull)
                    .sorted(new CustomCsvComparator())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return result;
    }
}