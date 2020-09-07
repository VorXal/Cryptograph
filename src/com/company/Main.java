package com.company;

public class Main {

    public static void main(String[] args) {
        Cryptograph cr = new Cryptograph("Hello","x");
        String[] chars = cr.toBigram();
        char[][] key = cr.getMatrix();
        for (String i : chars) {
            System.out.println(i);
        }

        for (char[] value : key) {
            System.out.println();
            for (char c : value) {
                System.out.print(c + "\t");
            }
        }

        System.out.println();
        int[] aCoords = cr.getCoordsOfChar('Y');
        System.out.println(aCoords[0]);
        System.out.println(aCoords[1]);

        System.out.println(cr.encryptBigram("FF"));

    }
}
