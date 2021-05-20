package com.company;

public class Main {

    public static void main(String[] args) {

        BinaryHeap bh = new BinaryHeap(100000);
        int n = 100000;
        long start  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            bh.insertItem(i);
        }
        long et = System.nanoTime() - start;
        System.out.printf("Додавання %d елементів: " + et/1000000 + " мс\n",n);

        long start1  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            bh.find(i);
        }
        long et1 = System.nanoTime() - start1;
        System.out.printf("Пошук %d елементів: " + et1/1000000 + " мс\n",n);

        long getSortedTime = System.nanoTime();
        int[] sorted = bh.getSorted();
        getSortedTime = System.nanoTime() - getSortedTime;
        System.out.printf("Сортування %d елементів: " + getSortedTime/1000000 + " мс\n",n);

        long start2  = System.nanoTime();
        for (int i = n; i > 0; i--) {
            bh.deleteItem(i);
        }
        long et2 = System.nanoTime() - start2;
        System.out.printf("Видалення %d елементів: " + et2/1000000 + " мс\n",n);
    }
}
