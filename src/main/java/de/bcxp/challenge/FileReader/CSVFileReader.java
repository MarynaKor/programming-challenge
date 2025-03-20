package de.bcxp.challenge.FileReader;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CSVFileReader {

    private static final Logger logger = Logger.getLogger(CSVFileReader.class.getName());

    public static List<List<String>> readFileByLine(String fileName, char delimiter) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<List<String>>();

        FileReader fileReader = new FileReader(fileName);
        try (CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(new CSVParserBuilder().withSeparator(delimiter).build())
                .build()) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e ) {
            logger.log(Level.SEVERE, "This Problem occurred: " + e.getMessage(),e);
        }


        return records;
    }
}
