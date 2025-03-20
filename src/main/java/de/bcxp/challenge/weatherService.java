package de.bcxp.challenge;

import de.bcxp.challenge.FileReader.CSVFileReader;
import de.bcxp.challenge.Interface.abstractDataProcessor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;


public class weatherService {


    public static String getDayWithSmallestSpread(abstractDataProcessor myProcessor){

        ////call function from myProcessor which will subtract one column from the other through an iteration through all rows
        List<Double> temperatureDifference = myProcessor.getColumnsDifference(myProcessor.getIndexOfHeader("MxT"),myProcessor.getIndexOfHeader("MnT"));

        //find the minimum in the results of all the differences
        Double minTemperatureDifference = temperatureDifference.stream().min(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("List is empty, cannot find the maximum value."));
        //find the day which has smallest difference in max-min Temperature by the index
        return myProcessor.getFromRecord(temperatureDifference.indexOf(minTemperatureDifference)).get(myProcessor.getIndexOfHeader("Day"));



    }
}
