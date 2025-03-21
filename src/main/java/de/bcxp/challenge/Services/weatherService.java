package de.bcxp.challenge.Services;

import de.bcxp.challenge.abstractDataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class weatherService {

    private static final Logger logger = LoggerFactory.getLogger(weatherService.class);

    public static String getDayWithSmallestSpread(abstractDataProcessor myProcessor){

        ////call function from myProcessor which will subtract one column from the other through an iteration through all rows
        List<Double> temperatureDifference = myProcessor.getColumnsDifference(myProcessor.getIndexOfHeader("MxT"),myProcessor.getIndexOfHeader("MnT"));
        logger.info("Subtracting the min temperature from max temerature");
        //find the minimum in the results of all the differences
        Double minTemperatureDifference = temperatureDifference.stream().min(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("List is empty, cannot find the maximum value."));
        logger.info("Finding minimal temperature difference");
        //find the day which has smallest difference in max-min Temperature by the index
        return myProcessor.getFromRecord(temperatureDifference.indexOf(minTemperatureDifference)).get(myProcessor.getIndexOfHeader("Day"));



    }
}
