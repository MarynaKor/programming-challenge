package de.bcxp.challenge;


import de.bcxp.challenge.Interface.abstractDataProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class csvDataProcessor implements abstractDataProcessor {
    List<List<String>> records;
    List<String> header;


    public csvDataProcessor(@NotNull List<List<String>> data) {
        this.header = data.remove(0);
        this.records = data;
    }

    @Override
    public int getIndexOfHeader(String string){
        return this.header.indexOf(string);
    }

    @Override
    public List<Double> getColumnsDifference(int columnMinuend, int columnSubtrahend){
        return this.records.stream().map(records -> Double.parseDouble(records.get(columnMinuend)) - Double.parseDouble(records.get(columnSubtrahend))).collect(Collectors.toList());
    }

    @Override
    public List<Double> getColumnsDivision(int columnDividend, int columnDivisor){
        return this.records.stream().map(records -> {
            String strDividend = records.get(columnDividend).replace(".", "").replace(",",".");
            String strDivisor = records.get(columnDivisor).replace(".", "").replace(",",".");
            double valueDividend = Double.parseDouble(strDividend);
            double valueDivisor =  Double.parseDouble(strDivisor);
            if(valueDivisor == 0 ){
                throw new RuntimeException("The Divisor is 0 therefore it can't be divided!");
            }
            return (valueDividend / valueDivisor);
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getFromRecord(int index){
        return this.records.get(index);
    }
}
