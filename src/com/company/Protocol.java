package com.company;

import java.math.BigInteger;

public class Protocol {
    private static String alphabet = "abcdefghiklmnopqrstuvwxyz".toUpperCase();

    public static String generatePartialKey(String firstPublicKey, String secondPublicKey, int firstPrivateKey, boolean second){
        if(!second){
            BigInteger output = new BigInteger(secondPublicKey);
            output = output.pow(firstPrivateKey);
            return String.valueOf(output.mod(new BigInteger(firstPublicKey)));
        } else {
            BigInteger output = new BigInteger(firstPublicKey);
            output = output.pow(firstPrivateKey);
            return String.valueOf(output.mod(new BigInteger(secondPublicKey)));
        }
    }

    public static double generateFinalNumericKey(String PartialKey, String firstPrivateKey, String PublicKey, boolean second){
        if(!second){
            BigInteger output = new BigInteger(PartialKey);
            output = output.pow(Integer.parseInt(firstPrivateKey));
            System.out.println(output.mod(new BigInteger(PublicKey)).doubleValue());
            return output.mod(new BigInteger(PublicKey)).doubleValue();
        } else {
            BigInteger output = new BigInteger(PartialKey);
            output = output.pow(Integer.parseInt(firstPrivateKey));
            System.out.println(output.mod(new BigInteger(PublicKey)).doubleValue());
            return output.mod(new BigInteger(PublicKey)).doubleValue();
        }
    }

    public static String generateFinalKey(double numericKey){
        String mainString = String.valueOf((int)numericKey);

        int size = Math.round(mainString.length()/2.0f);

        String[] arrayChars = new String[size];

        for (int i = 0, j = 0; i < mainString.length(); i+=2, j++){
            if(i + 2 > mainString.length()){
                arrayChars[j] = String.valueOf(mainString.charAt(i)).toUpperCase();
            } else {
                arrayChars[j] = String.valueOf(mainString.charAt(i)).toUpperCase() + String.valueOf(mainString.charAt(i + 1)).toUpperCase();
            }
        }

        int[] arrayInts = new int[size];
        int i = 0;
        StringBuilder output = new StringBuilder();
        for (String str : arrayChars){
            arrayInts[i] = Integer.parseInt(str) % 25;
            output.append(String.valueOf(alphabet.charAt(arrayInts[i] - 1)));
            i++;
        }
        return output.toString();
    }
}
