package org.example.withoutThead;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] result;
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
                    String fileName = "fail.txt";
                    original = RadixSort.readFromFile(fileName);
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
                    RadixSort.radixSort(original);
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nПосортований масив:");
                    RadixSort.printArray(original);
                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 2: {
                    long startTime = System.currentTimeMillis();
                    RadixSort.radixSort(original);
                    RadixSort.writeToFile(original, "fail.txt");
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
                case 3: {
                    long startTime = System.currentTimeMillis();
                    RadixSort.radixSort(original);
                    long stopTime = System.currentTimeMillis();

                    System.out.println("\nЧас виконання " + (stopTime - startTime) + " мілісекунд");
                    break;
                }
            }
        }
    }
}
