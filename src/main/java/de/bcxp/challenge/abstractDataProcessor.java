package de.bcxp.challenge;

import java.util.List;

public interface abstractDataProcessor {

    // gets Index of a string value in the header
    int getIndexOfHeader(String string);

    //function subtracts one column from the other through an iteration through all rows
    List<Double> getColumnsDifference(int columnMinuend, int columnSubtrahend);

    //reformats the numbers from the List of List from german format to the english format
    String correctNumberFormat(String raw_string);

    //function takes two columns and devides one by the other through an iteration through all rows
    List<Double> getColumnsDivision(int columnDividend, int columnDivisor);

    //
    List<String> getFromRecord(int index);
}
