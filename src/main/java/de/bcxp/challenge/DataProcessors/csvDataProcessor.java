package de.bcxp.challenge.DataProcessors;

import de.bcxp.challenge.abstractDataProcessor;

import java.util.*;
import java.util.stream.Collectors;

public class csvDataProcessor implements abstractDataProcessor {
    List<List<String>> records;
    List<String> header;


    public csvDataProcessor(List<List<String>> data) {
        if (data == null || data.size() < 2) {
            throw new IllegalArgumentException("Input data cannot be null or empty.");
        }

        this.header = data.get(0);
        this.records = data.subList(1, data.size());
    }

    @Override
    public int getIndexOfHeader(String string) {
        return this.header.indexOf(string);
    }

    @Override
    public String correctNumberFormat(String raw_string) {
        return raw_string.replace(".", "").replace(",", ".");
    }

    @Override
    public List<Double> getColumnsDifference(int columnMinuend, int columnSubtrahend) {
        return this.records.stream().map(records -> Double.parseDouble(records.get(columnMinuend))
                - Double.parseDouble(records.get(columnSubtrahend))).collect(Collectors.toList());
    }

    @Override
    public List<Double> getColumnsDivision(int columnDividend, int columnDivisor) {
        return this.records.stream().map(records -> {
            String strDividend = this.correctNumberFormat(records.get(columnDividend));
            String strDivisor = this.correctNumberFormat(records.get(columnDivisor));

            double valueDividend = Double.parseDouble(strDividend);
            double valueDivisor = Double.parseDouble(strDivisor);
            if (valueDivisor == 0) {
                throw new RuntimeException("The Divisor is 0 therefore it can't be divided!");
            }
            return (valueDividend / valueDivisor);
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getFromRecord(int index) {
        return this.records.get(index);
    }
}