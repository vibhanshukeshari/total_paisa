package com.vibhunorby.totalpaisa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatLocale {


    Locale locale;
    NumberFormat numberFormat;
    String numberAfterFormat;
    BigDecimal number;

    public String getNumberAfterFormat() {

//            locale = new Locale("hi", "IN");
////
//            numberFormat = NumberFormat.getCurrencyInstance(locale);
//            numberFormat.setMinimumFractionDigits(0);
//            numberFormat.setMaximumFractionDigits(3);
//
//            numberAfterFormat = numberFormat.format(number).replace("₹",  "");






         locale = new Locale("en", "IN");

        // Create a DecimalFormat instance with Indian numbering format
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
//        symbols.setDecimalSeparator('.');
//        symbols.setGroupingSeparator(',');

//        DecimalFormat indianFormat = new DecimalFormat("#,##,###.######", symbols);
        DecimalFormat indianFormat = new DecimalFormat("#,##,##0.######", symbols);



//            if(numberAfterFormat.endsWith(".00")){
//                numberAfterFormat = numberAfterFormat.substring(0, numberAfterFormat.length() - 3);
//            }
//

        return indianFormat.format(number);
    }


//   import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;

//   import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;
//
    public String getNumberAfterFormatUnlimitedDecimal() {
        locale = new Locale("en", "IN");

        // Create a DecimalFormat instance with Indian numbering format
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');




        if (number.toString().contains(".")) {
            // Extract the decimal part
            String decimalPart = number.toString().split("\\.")[1];
            int decimalPlaces = decimalPart.length();
            System.out.println(decimalPlaces);


            // Create a custom pattern that includes the exact number of decimal places
            StringBuilder patternBuilder = new StringBuilder("#,##,##0.");
            for (int i = 0; i < decimalPlaces; i++) {
                patternBuilder.append("0");
            }

            DecimalFormat indianFormat = new DecimalFormat(patternBuilder.toString(), symbols);
            return indianFormat.format(number);
        } else {
//            DecimalFormat indianFormat = new DecimalFormat("#,##,###.######", symbols);
            DecimalFormat indianFormat = new DecimalFormat("#,##,##0.######", symbols);

            return indianFormat.format(number);
        }








    }



    public void setNumber(BigDecimal number) {

        this.number = number;
    }
}
