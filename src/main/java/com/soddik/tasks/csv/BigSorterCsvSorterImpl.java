package com.soddik.tasks.csv;

import com.github.davidmoten.bigsorter.Serializer;
import com.github.davidmoten.bigsorter.Sorter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

public class BigSorterCsvSorterImpl implements CsvSorter {
    private final File in;
    private final File out;

    public BigSorterCsvSorterImpl(File in, String out) {
        this.in = in;
        this.out = new File(out);
    }


    public void parseFile() {
        String separator = ";";
        Serializer<CSVRecord> serializer = Serializer.csv(
                CSVFormat.Builder
                        .create()
                        .setDelimiter(separator)
                        .setHeader()
                        .build(), StandardCharsets.UTF_8);

        Comparator<CSVRecord> comparator = (x, y) -> {
            int a = Integer.parseInt(x.get("FID"));
            int b = Integer.parseInt(y.get("FID"));
            return Integer.compare(b, a);
        };

        Sorter.serializer(serializer)
                .comparator(comparator)
                .input(in)
                .output(out)
                .sort();
    }
}
