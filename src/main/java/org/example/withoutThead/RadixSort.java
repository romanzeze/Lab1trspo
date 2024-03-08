package org.example.withoutThead;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int maxValue = getMaxValue(arr);

        int exp = 1;
        while (maxValue / exp > 0) {
            countSort(arr, exp);
            exp *= 10;
        }
    }

    private static int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static int[] readFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner s = new Scanner(file);
            int size = 0;
            while (s.hasNextInt()) {
                size++;
                s.nextInt();
            }

            int[] arr = new int[size];
            Scanner s1 = new Scanner(file);
            for (int i = 0; i < size; i++)
                arr[i] = s1.nextInt();
            return arr;
        } catch (Exception e) {
            System.out.println("Виникла помилка");
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToFile(int[] arr, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (int j : arr) {
                fw.write(j + " ");
            }
            System.out.println("Запис успішно виконано");
        } catch (IOException e) {
            System.out.println("Виникла помилка");
            e.printStackTrace();
        }
    }

    public static int[] generateRandomInput(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
    public static int[] readInputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Введіть елементи масиву:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }
    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static int[] finalMerge(int[] internal, int[]  internal1) {
        int[] result = new int[internal.length +  internal1.length];
        int i = 0;
        int j = 0;
        int r = 0;
        while (i < internal.length && j <  internal1.length) {
            if (internal[i] <=  internal1[j]) {
                result[r] = internal[i];
                i++;
            } else {
                result[r] =  internal1[j];
                j++;
            }
            r++;
        }
        while (i < internal.length) {
            result[r] = internal[i];
            i++;
            r++;
        }
        while (j <  internal1.length) {
            result[r] =  internal1[j];
            j++;
            r++;
        }
        return result;

    }
}
