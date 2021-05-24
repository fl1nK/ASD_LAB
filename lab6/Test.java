package com.company;

import java.lang.reflect.Array;

public class Test {

    static void TestArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
    }

    public static void main(String [] arg){

        LinkedList te = new LinkedList();

        long start,end, point = 1000;
        for (int i = 1000; i <= 50000000; i += point) {
            if(i>=10000 && i<=100000){
               point =10000;
            }
            if(i>=100000 && i<=1000000){
                point =100000;
            }
            if(i>=1000000 && i<=10000000){
                point =1000000;
            }
            if(i>=10000000){
                point =10000000;
            }

        int[] array = new int[i];
        TestArray(array);

        start = System.nanoTime();
        for (int j : array) {
            te.push(j);
        }

        end = System.nanoTime();
        System.out.printf("Insert time for AVL Tree, %d elements- " + (end - start) / array.length + " ns\n", i);

        start = System.nanoTime();
        for (int j : array) {
            te.searchIndex(j);
        }
        end = System.nanoTime();
        System.out.printf("Search time for AVL Tree, %d elements- " + (end - start) /array.length + " ns\n", i);


        start = System.nanoTime();
        for (int j : array) {
            te.removeBeginning();
        }
        end = System.nanoTime();
        System.out.printf("Delete time for AVL Tree, %d elements- " + (end - start) / array.length + " ns\n\n", i);

        }
    }
}
