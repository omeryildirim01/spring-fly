package com.yildirimomer.springfly.tools;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public class PriceCalculateUtil {
    public static Double calculatePrice(int bookedCount, int quota, Double basePrice){
        Double updatedPrice = basePrice;

        if (bookedCount > 0  && bookedCount <quota){
            int percentage = (100 * bookedCount) / quota;
            if (percentage >= 10){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 20){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 30){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 40){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 50){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 60){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 70){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 80){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            if (percentage >= 90){
                updatedPrice = updatedPrice + getTenPercentage(basePrice);
            }
            return updatedPrice;
        }

        return  updatedPrice;
    }
    public static Double getTenPercentage(Double p){
        return (p*10)/100;
    }
}
