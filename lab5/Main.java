import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map<Integer,String> map = new Map<>();
        int n = 100000;
        long start  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.add(i,"ky");
        }
        long et = System.nanoTime() - start;
        System.out.printf("Додавання %d елементів: " + et/1000000 + " мс\n",n);

        long start1  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.get(i);
        }
        long et1 = System.nanoTime() - start1;
        System.out.printf("Пошук %d елементів: " + et1/1000000 + " мс\n",n);

        long start2  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.remove(i);
        }
        long et2 = System.nanoTime() - start2;
        System.out.printf("Видалення %d елементів: " + et2/1000000 + " мс\n",n);

        long start3  = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.getSorted();
        }
        long et3 = System.nanoTime() - start3;
        System.out.printf("Сортування %d елементів: " + et3/1000000 + " мс\n",n);
    }
}