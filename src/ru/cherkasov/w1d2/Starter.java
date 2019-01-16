package ru.cherkasov.w1d2;

import java.util.Arrays;
import java.util.Random;

/**
 * @author SCherkasov
 */
public class Starter {

    public static void main(String[] args) {
        Random rand = new Random();
        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }

        try {
            ArraySorts.mergeSort(arr, arr.length);
//            ArraySorts.bubbleSort(arr);
//            ArraySorts.insertSort(arr);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(arr));
    }

}
