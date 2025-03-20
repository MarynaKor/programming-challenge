package de.bcxp.challenge;

import de.bcxp.challenge.Interface.abstractDataProcessor;

import java.util.List;

public class CountriesService {

    /*Use the columns `Population` and `Area` to compute each country's population
    density. Read the file, then print the name of the country with the highest number
    of people per square kilometre.*/
    

    public static String findHighestDensity(abstractDataProcessor myProcessor) {

        //call function from data Processor which will divide one column by the other through an iteration through all rows
        List<Double> densityDivision = myProcessor.getColumnsDivision(myProcessor.getIndexOfHeader("Population"), myProcessor.getIndexOfHeader("Area (km²)"));

        //find the Maximum in the results of all the division
        Double maxDensity = densityDivision.stream().max(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("List is empty, cannot find the maximum value."));
        //get the Name of the Country with that maxDensity, data processor skips the header line so the index must be incremented by one again
        return myProcessor.getFromRecord(densityDivision.indexOf(maxDensity)).get(myProcessor.getIndexOfHeader("Name"));
    }


}
