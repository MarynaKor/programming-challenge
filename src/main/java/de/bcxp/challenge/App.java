package de.bcxp.challenge;

import de.bcxp.challenge.FileReader.CSVFileReader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws FileNotFoundException {

        // Your preparation code …
        String filePathWeather = "src/main/resources/de/bcxp/challenge/weather.csv";
        List<List<String>> inputDataWeather = CSVFileReader.readFileByLine(filePathWeather, ',');
        csvDataProcessor myWeatherProcessor = new csvDataProcessor(inputDataWeather);
        String dayWithSmallestTempSpread = weatherService.getDayWithSmallestSpread(myWeatherProcessor);     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        String filePath = "src/main/resources/de/bcxp/challenge/countries.csv";
        List<List<String>> inputData = CSVFileReader.readFileByLine(filePath, ';');

        csvDataProcessor myCountryProcessor = new csvDataProcessor(inputData);
        final String countryWithHighestPopulationDensity = CountriesService.findHighestDensity(myCountryProcessor); // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
