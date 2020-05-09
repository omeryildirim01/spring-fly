package com.yildirimomer.springfly.tools;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public class CardUtil {

    public static String mask = "######******####";

    // to mask a card number  maskCardNumber( "##************##", "1234123412341234")) > 12************34

    public static String maskCardNumber(String mask, String cardNumber) {
        cardNumber = cardNumber.replaceAll("-", "");
        cardNumber = cardNumber.replaceAll(",", "");
        cardNumber = cardNumber.trim();

        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }
        return maskedNumber.toString();
    }
}
