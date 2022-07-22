package com.soddik.tasks.csv;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class DataHandler {
    private static final Logger logger = Logger.getLogger(DataHandler.class.getSimpleName());

    public static void writeData(String filePath, List<String[]> list) {
        logger.info("Start: " + new Date());
        File file = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(file);
             CSVWriter csvWriter = new CSVWriter(fileWriter, ';',
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            list.stream()
                    .toList()
                    .forEach(csvWriter::writeNext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("End: " + new Date());
    }

    public static List<String[]> generateData() {
        List<String[]> list = new ArrayList<>();
        String pattern = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        for (int index = 0; index < 5_000_000; index++) {
            String[] strings = new String[8];
            strings[0] = String.valueOf(ThreadLocalRandom.current().nextInt(0, 2000));
            strings[1] = String.valueOf(ThreadLocalRandom.current().nextInt(500, 10000));
            String sb = ThreadLocalRandom.current().nextInt(1000, 9999) + "SM" + ThreadLocalRandom.current().nextInt(100000, 999999);
            strings[2] = sb;
            strings[3] = String.valueOf(ThreadLocalRandom.current().nextInt(0, 9));
            strings[4] = sdf.format(new Date());
            strings[5] = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
            strings[6] = sdf.format(new Date());
            strings[7] = String.valueOf(ThreadLocalRandom.current().nextInt(700000, 17000000));
            list.add(strings);
        }
        logger.info(String.format("Generated %s", list.size()));

        return list;
    }
}
