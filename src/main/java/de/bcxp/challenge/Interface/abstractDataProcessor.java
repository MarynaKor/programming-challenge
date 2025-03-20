package de.bcxp.challenge.Interface;

import java.util.List;

public interface abstractDataProcessor {

    int getIndexOfHeader(String string);

    List<Double> getColumnsDifference(int columnMinuend, int columnSubtrahend);

    List<Double> getColumnsDivision(int columnDividend, int columnDivisor);

    List<String> getFromRecord(int index);
}
