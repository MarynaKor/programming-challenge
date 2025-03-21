package de.bcxp.challenge;

import de.bcxp.challenge.DataProcessors.csvDataProcessor;
import de.bcxp.challenge.FileReader.CSVFileReader;
import de.bcxp.challenge.Services.CountriesService;
import de.bcxp.challenge.Services.weatherService;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     *
     */
    private static final  Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String... args) throws FileNotFoundException {

        //filepath for weather
        String filePathWeather = "src/main/resources/de/bcxp/challenge/weather.csv";
        //Reading the weather file with specific (,) delimiter
        List<List<String>> inputDataWeather = CSVFileReader.readFileByLine(filePathWeather, ',');
        logger.info("Reading weather csv file");
        //creating weather Processor to give the processed data into the weather service
        csvDataProcessor myWeatherProcessor = new csvDataProcessor(inputDataWeather);
        //weather service gives the day with smallest temperature spread
        String dayWithSmallestTempSpread = weatherService.getDayWithSmallestSpread(myWeatherProcessor);     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);


        //filepath for countries
        String filePath = "src/main/resources/de/bcxp/challenge/countries.csv";
        //Reading the country csv file with specific (;) delimiter
        List<List<String>> inputData = CSVFileReader.readFileByLine(filePath, ';');
        logger.info("Reading countries csv file");
        //creating country data processor to give the processed data into the weather service
        csvDataProcessor myCountryProcessor = new csvDataProcessor(inputData);
        //country service gets the country with highest density
        final String countryWithHighestPopulationDensity = CountriesService.findHighestDensity(myCountryProcessor); // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
