package com.company;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cryptograph {
    private String mainString;
    private String key;
    public Cryptograph(String input, String key){
        StringBuilder temp = new StringBuilder(input.replaceAll("\\W", "").toUpperCase());
        for (int i = 0, j = 1; j < temp.length();){
            if (temp.charAt(i) == temp.charAt(j)){
                temp.insert(j, 'X');
                i = 0;
                j = 1;
            }
            else{
                i += 2;
                j += 2;
            }

        }
        this.mainString = temp.toString();

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

    public String getMainString(){return this.mainString;}

    private String alphabet = "abcdefghiklmnopqrstuvwxyz".toUpperCase();

    private char[][] matrix = new char[5][5];

    private char[][] getMatrix(){
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

    private String[] toBigram(){
        int size = Math.round(mainString.length()/2.0f);

        String[] arrayChars = new String[size];
        if(mainString.length()/2.0 % 1 == 0.5){
            mainString = mainString + 'X';
        }

        for (int i = 0, j = 0; i < mainString.length(); i+=2, j++){
            arrayChars[j] = String.valueOf(mainString.charAt(i)).toUpperCase() + String.valueOf(mainString.charAt(i + 1)).toUpperCase();
        }

        return arrayChars;
    }

    private int[] getCoordsOfChar(char ch){
        int[] output = new int[2];
        char[][] matrix = this.getMatrix();

        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(matrix[i][j] == ch){
                    output[0] = i;
                    output[1] = j;
                }
            }
        }
        return output;
    }

    private String encryptBigram(String input){
        int[] firstCoord = this.getCoordsOfChar(input.charAt(0));
        int[] secondCoord = this.getCoordsOfChar(input.charAt(1));

        if (firstCoord[0] == secondCoord[0] && firstCoord[1] == secondCoord[1]){
            if(input.charAt(0) == 'X' && input.charAt(1) == 'X'){
                if(firstCoord[1] == 4){
                    return String.valueOf(matrix[firstCoord[0]][0]) + matrix[firstCoord[0]][0];
                }
                else{
                    return (String.valueOf(matrix[firstCoord[0]][firstCoord[1] + 1]) + matrix[firstCoord[0]][firstCoord[1] + 1]) +
                            (String.valueOf(matrix[firstCoord[0]][firstCoord[1] + 1]) + matrix[firstCoord[0]][firstCoord[1] + 1]);
                }
            }
            return (encryptBigram(input.charAt(0) + "X") + encryptBigram(input.charAt(0) + "X"));
        }else if (firstCoord[0] == secondCoord[0]){ //SAME ROW
            if(firstCoord[1] < secondCoord[1]){
                if(secondCoord[1] == 4){
                    return String.valueOf(matrix[firstCoord[0]][firstCoord[1] + 1]) + matrix[secondCoord[0]][0];
                } else {
                    return String.valueOf(matrix[firstCoord[0]][firstCoord[1] + 1]) + matrix[secondCoord[0]][secondCoord[1] + 1];
                }
            } else{
                if(firstCoord[1] == 4){
                    return String.valueOf(matrix[firstCoord[0]][0]) + matrix[secondCoord[0]][secondCoord[1] + 1];
                }
                else {
                    return String.valueOf(matrix[firstCoord[0]][firstCoord[1] + 1]) + matrix[secondCoord[0]][secondCoord[1] + 1];
                }
            }
        } else if(firstCoord[1] == secondCoord[1]){
            if(firstCoord[0] < secondCoord[0]){
                if(secondCoord[0] == 4){
                    return String.valueOf(matrix[firstCoord[0] + 1][firstCoord[1]]) + matrix[0][secondCoord[1]];
                } else {
                    return String.valueOf(matrix[firstCoord[0] + 1][firstCoord[1]]) + matrix[secondCoord[0] + 1][secondCoord[1]];
                }
            } else{
                if(firstCoord[0] == 4){
                    return String.valueOf(matrix[0][firstCoord[1]]) + matrix[secondCoord[0] + 1][secondCoord[1]];
                }
                else {
                    return String.valueOf(matrix[firstCoord[0] + 1][firstCoord[1]]) + matrix[secondCoord[0] + 1][secondCoord[1]];
                }
            }
        } else {
            return String.valueOf(matrix[firstCoord[0]][secondCoord[1]]) + matrix[secondCoord[0]][firstCoord[1]];
        }
    }

    public String encryptWord(){
        String[] chars = this.toBigram();

        StringBuilder output = new StringBuilder();
        for(String item: chars){
            output.append(this.encryptBigram(item));
        }

        return output.toString();
    }
}
