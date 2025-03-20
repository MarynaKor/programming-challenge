package de.bcxp.challenge;

import java.util.List;
import java.util.stream.Collectors;

public class dataProcessor {



    public static List<String> defineHeaderCSV(List<List<String>> data) {
        return data.get(0);
    }
    public static int getIndexOfValueList(List<Double> data, double value){
        return data.indexOf(value);
    }
    public static int getIndexOfHeader(List<List<String>> data, String string){
        return defineHeaderCSV(data).indexOf(string);
    }


    public static List<Double> createNewList(List<List<String>> subList, int columnForSubList){
        return subList.stream().skip(1).map(records -> Double.parseDouble(records.get(columnForSubList))).collect(Collectors.toList());
    }

    public static List<Double> getColumnsDifference(List<List<String>> subList, int columnMinuend, int columnSubtrahend){
        return subList.stream().skip(1).map(records -> Double.parseDouble(records.get(columnMinuend)) - Double.parseDouble(records.get(columnSubtrahend))).collect(Collectors.toList());
    }

    public static List<Double> getColumnsDivision(List<List<String>> subList, int columnDividend, int columnDivisor){
        return subList.stream().skip(1).map(records -> {
            String strDividend = records.get(columnDividend).replace(".", "").replace(",",".");
            String strDivisor = records.get(columnDivisor).replace(".", "").replace(",",".");
            double valueDividend = Double.parseDouble(strDividend);
            double valueDivisor =  Double.parseDouble(strDivisor);
            if(valueDivisor == 0 ){
                throw new RuntimeException("The Divisor is 0 therefore it can't be devided!");
            }
            return (valueDividend / valueDivisor);
        }).collect(Collectors.toList());
    }
}
