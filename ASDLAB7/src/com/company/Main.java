package com.company;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        String patter = Read.readUsingBufferedReader("src/com/company/patter5.txt");;
        String text = Read.readUsingBufferedReader("src/com/company/Text.txt");

        long start = System.nanoTime()/1000;
        System.out.println(Arrays.toString(Search.KMPSearch(text, patter).toArray()));
        long end = System.nanoTime()/1000 - start;
        System.out.printf("Час пошуку: %d мкс " ,end);
    }
}

