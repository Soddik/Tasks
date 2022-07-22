package com.soddik.tasks.csv;

import java.util.Comparator;

public class CustomCsvComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] o1, String[] o2) {
        return Integer.valueOf(o2[0]).compareTo(Integer.valueOf(o1[0]));
    }
}
