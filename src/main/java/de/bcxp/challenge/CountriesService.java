package de.bcxp.challenge;

import de.bcxp.challenge.FileReader.CSVFileReader;

import java.util.List;

public class CountriesService {

    /*Use the columns `Population` and `Area` to compute each country's population
    density. Read the file, then print the name of the country with the highest number
    of people per square kilometre.*/

   static List<List<String>> countriesRecords = CSVFileReader.readFileByLine("src/main/resources/de/bcxp/challenge/countries.csv", ';');

    public static String findHighestDensity(){

        //call function from data Processor which will devide one column by the other through an iteration through all rows
        List<Double> densityDivision = dataProcessor.getColumnsDivision(countriesRecords, dataProcessor.getIndexOfHeader(countriesRecords,"Population"), dataProcessor.getIndexOfHeader(countriesRecords,"Area (km²)"));

        //find the Maximum in the results of all the devision
        Double maxDensity = densityDivision.stream().max(Double::compareTo).orElse(null);

        //get the Name of the Country with that maxDensity, data processor skips the header line so the index must be incremented by one again
        return countriesRecords.get(dataProcessor.getIndexOfValueList(densityDivision,maxDensity) + 1).get(dataProcessor.getIndexOfHeader(countriesRecords,"Name"));


    }


}
