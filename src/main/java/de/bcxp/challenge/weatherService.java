package de.bcxp.challenge;

import de.bcxp.challenge.FileReader.CSVFileReader;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;


public class weatherService {
    public static void getDayWithSmallestSpread(){

        List<List<String>> weatherRecords =  CSVFileReader.readFileByLine("src/main/resources/de/bcxp/challenge/weather.csv", ',');
        List<String> header = weatherRecords.get(0);
        int linesOfData = weatherRecords.size();
        int columnOfDay = header.indexOf("Day");
        int columnOfMxT = header.indexOf("MxT");
        int columnOfMnT = header.indexOf("MnT");

        List<Double> temperatureDifference = dataProcessor.getColumnsDifference(weatherRecords,header.indexOf("MxT"),header.indexOf("MnT"));
        Double minTemperatureDifference = temperatureDifference.stream().min(Double::compareTo).orElse(null);

        int minIndex = temperatureDifference.indexOf(minTemperatureDifference);
        String minTempDiffDay = weatherRecords.get(minIndex + 1).get(columnOfDay);
        System.out.println("The day with the lowest Temp Difference: " + minTempDiffDay);
        System.out.println(linesOfData);
        System.out.println(columnOfDay + " " + columnOfMxT +" "+ columnOfMnT);

    }
}
