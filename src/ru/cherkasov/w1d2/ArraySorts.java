package ru.cherkasov.w1d2;

import java.util.Arrays;

/**
 * Класс для сортировки массивов
 * @author SCherkasov
 */
public class ArraySorts {

    public static void main(String[] args) {
        Integer[] arr = {1, 55, 9, 12, 45};
//        Integer[] arr = {};
//        BubbleSort(arr);
        InsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Bubble Sort
     * @param array массив
     */
    private static void BubbleSort(Integer[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //swap [j] and [j+1]
                    int mTemp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = mTemp;
                }
            }
        }
    }

    /**
     * Insert Sort
     * @param arr массив
     */
    private static void InsertSort(Integer[] arr){
        int mMin;
        for (int i = 0; i < arr.length-1; i++) {
            mMin = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[mMin]) mMin = j;
            }
            int mTemp = arr[mMin];
            arr[mMin] = arr[i];
            arr[i] = mTemp;
        }
    }

}
