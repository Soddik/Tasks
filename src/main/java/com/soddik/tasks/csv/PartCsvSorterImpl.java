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
import java.util.logging.Logger;

public class PartCsvSorterImpl implements CsvSorter {
    private final Logger logger = Logger.getLogger(PartCsvSorterImpl.class.getSimpleName());
    private final String separator = ";";
    private final File in;
    private int maxRowCount = 1_000_000;
    private String[] headers;

    public PartCsvSorterImpl(File in, int rowPerPart) {
        this.in = in;
        maxRowCount = rowPerPart;
    }

    public PartCsvSorterImpl(File in) {
        this.in = in;
    }

    @Override
    public void parseFile() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(in.getAbsolutePath())), StandardCharsets.UTF_8))) {
            getHeaders();
            long rowCount = br.lines()
                    .skip(1)
                    .count();

            if (rowCount > 0) {
                File dir = new File("parts");
                if (!dir.exists()) {
                    dir.mkdir();
                }

                long iterations = rowCount % maxRowCount != 0 ? (rowCount / maxRowCount) + 1 : rowCount / maxRowCount;

                int iteration = 0;
                while (iteration < iterations) {
                    sortAndWritePart(iteration, maxRowCount);
                    iteration++;
                }
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private void sortAndWritePart(long iteration, long limit) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(in.getAbsolutePath())), StandardCharsets.UTF_8))) {
            File out = new File(String.format("parts/sortedPart_%s.csv", iteration));
            long skipLines = iteration != 0 ? iteration * maxRowCount + 1 : 1;
            List<String[]> result = new ArrayList<>();
            result.add(headers);
            result.addAll(br.lines()
                    .skip(skipLines)
                    .map(line -> line.split(separator))
                    .limit(limit)
                    .sorted(new CustomCsvComparator())
                    .toList());
            System.out.println("iter: " + iteration + " size: " + result.size());
            DataHandler.writeData(out, result);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private void getHeaders() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(in.getAbsolutePath())), StandardCharsets.UTF_8))) {
            headers = br.readLine().split(separator);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
