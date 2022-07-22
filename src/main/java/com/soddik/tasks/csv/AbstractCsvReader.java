package com.soddik.tasks.csv;

import java.util.List;

public interface AbstractCsvReader {
    List<String[]> parseFile();
}
