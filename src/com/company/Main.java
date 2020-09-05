package com.company;

public class Main {

    public static void main(String[] args) {
        Cryptograph cr = new Cryptograph("abcde");
        String[] chars = cr.toBigram();
        for (String i : chars) {
            System.out.println(i);
        }
    }
}
