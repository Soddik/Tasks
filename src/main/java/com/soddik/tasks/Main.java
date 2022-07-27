package com.soddik.tasks;

import com.soddik.tasks.csv.*;

import java.io.File;
import java.util.Date;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getSimpleName());
    private static final String PATH = "myFile.csv";

    public static void main(String[] args) {
        File in = new File(PATH);
        DataHandler.writeData(in, DataHandler.generateData()); //generate large(14_500_000 lines) csv file

        logger.info("====Simple====");
        logger.info("Start: " + new Date());
        CsvSorter simple = new SimpleCsvSorterImpl(in, "simpleSortedMyFile.csv");
        simple.parseFile();
        logger.info("End: " + new Date());

        logger.info("====Part====");
        logger.info("Start: " + new Date());
        CsvSorter partSorter = new PartCsvSorterImpl(in);
        partSorter.parseFile();
        logger.info("End: " + new Date());

        logger.info("====BIG====");
        logger.info("Start: " + new Date());
        CsvSorter bigSorter = new BigSorterCsvSorterImpl(in, "bigSortedMyFile.csv");
        bigSorter.parseFile();
        logger.info("End: " + new Date());
    }
}
