package org.example.withThead;

import org.example.withoutThead.RadixSort;

import java.util.Scanner;

public class WithTheard {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int[] original = new int[0];
        while (true) {
            System.out.println("Виберіть метод: 1 - згенерувати випадковий масив; 2 - ввести масив з клавіатури; 3 - прочитати масив з файлу; 4 - вихід із програми");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.print("Введіть розмір масиву: \n");
                    int size = scan.nextInt();
                    original = RadixSort.generateRandomInput(size);
                    break;
                }
                case 2: {
                    original = RadixSort.readInputFromConsole();
                    break;
                }
                case 3: {
                    original = RadixSort.readFromFile("fail.txt");
                    break;
                }
                case 4: {
                    return;
                }
            }
            System.out.println("1 - Показати на екран; 2 - Записати в файл; 3 - Записати час");
            int choice2 = scan.nextInt();
            switch (choice2) {
                case 1: {
                    System.out.println("Даний масив:");
                    RadixSort.printArray(original);

                    long startTime = System.currentTimeMillis();
                    int[] result = parallelRadixSort(original);
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nПосортований масив:");
                    RadixSort.printArray(result);
                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 2: {
                    long startTime = System.currentTimeMillis();
                    int[] result = parallelRadixSort(original);
                    RadixSort.writeToFile(result, "fail.txt");
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 3: {
                    long startTime = System.currentTimeMillis();
                    int[] result = parallelRadixSort(original);
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
            }
        }
    }

    public static int[] parallelRadixSort(int[] arr) throws InterruptedException {
        int mid = arr.length / 2;
        int[] subArr1 = new int[mid];
        int[] subArr2 = new int[arr.length - mid];

        System.arraycopy(arr, 0, subArr1, 0, mid);
        System.arraycopy(arr, mid, subArr2, 0, arr.length - mid);

        RadixSortThread t1 = new RadixSortThread(subArr1);
        RadixSortThread t2 = new RadixSortThread(subArr2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return RadixSort.finalMerge(t1.getInternal(), t2.getInternal());
    }
}

