package com.company;

public class Cryptograph {
    private String mainString;
    public Cryptograph(String input){
        this.mainString = input;
    }

    public String[] toBigram(){
        int size = Math.round(mainString.length()/2.0f);

        String[] arrayChars = new String[size];
        if(mainString.length()/2.0 % 1 == 0.5){
            mainString = mainString + ' ';
        }

        for (int i = 0, j = 0; i < mainString.length(); i+=2, j++){
            arrayChars[j] = String.valueOf(mainString.charAt(i)) + mainString.charAt(i + 1);
        }

        return arrayChars;
    }
}
