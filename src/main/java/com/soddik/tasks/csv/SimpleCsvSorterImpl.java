package com.soddik.tasks.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SimpleCsvSorterImpl implements CsvSorter {
    private final Logger logger = Logger.getLogger(SimpleCsvSorterImpl.class.getSimpleName());
    private final String separator = ";";
    private final File in;
    private final File out;

    public SimpleCsvSorterImpl(File in, String out) {
        this.in = in;
        this.out = new File(out);
    }

    @Override
    public void parseFile() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(in.getAbsolutePath())), StandardCharsets.UTF_8))) {
            List<String[]> result = br.lines()
                    .skip(1)
                    .map(line -> line.split(separator))
                    .sorted(new CustomCsvComparator())
                    .collect(Collectors.toList());

            DataHandler.writeData(out, result);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
