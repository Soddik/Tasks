package com.soddik.tasks.csv;

import java.util.Comparator;

public class CustomCsvComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] o1, String[] o2) {
        return o1[0].compareTo(o2[0]);
    }
}
