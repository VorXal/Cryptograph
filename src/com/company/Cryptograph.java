package com.company;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cryptograph {
    private String mainString;
    private String key;
    public Cryptograph(String input, String key){
        this.mainString = input;

        char[] chars = key.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }

        this.key = sb.toString().toUpperCase();
    }

    String alphabet = "abcdefghiklmnopqrstuvwxyz".toUpperCase();

    public char[][] matrix = new char[5][5];

    public char[][] getMatrix(){
        for (int k = 0, i = 0, j = 0; k < key.length(); k++){
            if(i > 4){
                j++;
                i = 0;
            }
            matrix[j][i] = Character.toUpperCase(key.charAt(k));
            i++;
        }


        for (char ch: this.key.toCharArray()) {
            if(alphabet.indexOf(ch) != -1){
                alphabet = alphabet.substring(0, alphabet.indexOf(ch)) + alphabet.substring(alphabet.indexOf(ch) + 1);
            }
        }

        for (char ch : this.alphabet.toCharArray()) {
            outerloop:{
                for(int i = 0; i < 5; i++){
                    for (int j = 0; j < 5; j++){
                        if(matrix[i][j] == '\u0000'){
                            matrix[i][j] = ch;
                            break outerloop;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    public String[] toBigram(){
        int size = Math.round(mainString.length()/2.0f);

        String[] arrayChars = new String[size];
        if(mainString.length()/2.0 % 1 == 0.5){
            mainString = mainString + ' ';
        }

        for (int i = 0, j = 0; i < mainString.length(); i+=2, j++){
            arrayChars[j] = String.valueOf(mainString.charAt(i)).toUpperCase() + String.valueOf(mainString.charAt(i + 1)).toUpperCase();
        }

        return arrayChars;
    }
}
