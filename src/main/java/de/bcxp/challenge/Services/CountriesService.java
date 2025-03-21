package de.bcxp.challenge.Services;

import de.bcxp.challenge.abstractDataProcessor;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountriesService {

   private static final  Logger logger = LoggerFactory.getLogger(CountriesService.class);

    public static String findHighestDensity(abstractDataProcessor myProcessor) {

        //call function from data Processor which will divide one column by the other through an iteration through all rows
        List<Double> densityDivision = myProcessor.getColumnsDivision(myProcessor.getIndexOfHeader("Population"), myProcessor.getIndexOfHeader("Area (km²)"));
        logger.info("Dividing the population by Area");
        //find the Maximum in the results of all the division
        Double maxDensity = densityDivision.stream().max(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("List is empty, cannot find the maximum value."));
        logger.info("Finding highest density in the List ");
        //get the Name of the Country with that maxDensity, data processor skips the header line so the index must be incremented by one again
        return myProcessor.getFromRecord(densityDivision.indexOf(maxDensity)).get(myProcessor.getIndexOfHeader("Name"));
    }


}
