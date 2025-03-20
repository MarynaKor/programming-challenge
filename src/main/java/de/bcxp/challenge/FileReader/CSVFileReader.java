package de.bcxp.challenge.FileReader;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVFileReader {

    public static List<List<String>> readFileByLine(String fileName, char delimiter) {
        List<List<String>> records = new ArrayList<List<String>>();
        try {
            FileReader fileReader = new FileReader(fileName);
            CSVParser parser = new CSVParserBuilder().withSeparator(delimiter).build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();

            String[] values;
            while ((values = csvReader.readNext()) != null) {

                    records.add(Arrays.asList(values));
            }

        } catch(Exception e) {
            e.getStackTrace();
        }
        return records;
    }
}
