package de.bcxp.challenge;

import de.bcxp.challenge.FileReader.CSVFileReader;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;


public class weatherService {

    static List<List<String>> weatherRecords =  CSVFileReader.readFileByLine("src/main/resources/de/bcxp/challenge/weather.csv", ',');

    public static String getDayWithSmallestSpread(){

        ////call function from dataProcessor which will subtract one column from the other through an iteration through all rows
        List<Double> temperatureDifference = dataProcessor.getColumnsDifference(weatherRecords,dataProcessor.getIndexOfHeader(weatherRecords,"MxT"),dataProcessor.getIndexOfHeader(weatherRecords,"MnT"));

        //find the minimum in the results of all the differences
        Double minTemperatureDifference = temperatureDifference.stream().min(Double::compareTo).orElse(null);

        //find the day which has smallest difference in max-min Temperature by the index
        return weatherRecords.get(dataProcessor.getIndexOfValueList(temperatureDifference, minTemperatureDifference) + 1).get(dataProcessor.getIndexOfHeader(weatherRecords,"Day"));



    }
}
