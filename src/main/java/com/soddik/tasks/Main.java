package com.soddik.tasks;

import com.soddik.tasks.csv.AbstractCsvReader;
import com.soddik.tasks.csv.SimpleCsvReaderImpl;
import com.soddik.tasks.csv.DataHandler;

import java.io.File;
import java.util.Date;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getSimpleName());
    private static final String PATH = "myFile.csv";

    public static void main(String[] args) {
        //DataHandler.writeData(PATH, DataHandler.generateData()); //generate large(14_500_000 lines) csv file
        File file = new File(PATH);
        logger.info("====Simple====");
        logger.info("Start: " + new Date());
        AbstractCsvReader simple = new SimpleCsvReaderImpl(file);
        DataHandler.writeData(simple.parseFile());
        logger.info("End: " + new Date());
    }
}
