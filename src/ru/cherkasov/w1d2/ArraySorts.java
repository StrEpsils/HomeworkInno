package ru.cherkasov.w1d2;

import java.util.logging.Logger;

/**
 * Сортировка массивов методами:
 * 1) Пузырька
 * 2) Вставками
 * 3) Слиянием
 *
 * @author SCherkasov
 */
public abstract class ArraySorts {
//    static Logger log = Logger.getLogger(ArraySorts.class.getName());

    /**
     * Сортировка пузырьком
     *
     * @param arr исходный массив
     */
    protected static void bubbleSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        try {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int mTemp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = mTemp;
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Сортировка вставками
     *
     * @param arr исходный массив
     */
    protected static void insertSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mMin;
        try {
            for (int i = 0; i < arr.length - 1; i++) {
                mMin = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[mMin]) mMin = j;
                }
                int mTemp = arr[mMin];
                arr[mMin] = arr[i];
                arr[i] = mTemp;
            }
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Сортировка слиянием рекурсивным методом
     *
     * @param arr    исходный массив
     * @param length длина массива
     */
    protected static void mergeSort(Integer[] arr, int length) {
        if (arr.length < 2) {
            return;
        }

        int mMid = length / 2;
        Integer[] mLeft = new Integer[mMid];
        Integer[] mRight = new Integer[length - mMid];

        for (int i = 0; i < mMid; i++) {
            mLeft[i] = arr[i];
        }
        for (int i = mMid; i < length; i++) {
            mRight[i - mMid] = arr[i];
        }

        mergeSort(mLeft, mMid);
        mergeSort(mRight, length - mMid);
        merge(arr, mLeft, mRight, mMid, length - mMid);
    }

    /**
     * Метод слияния
     *
     * @param arr      исходный массив
     * @param leftArr  левая часть массива
     * @param rightArr правая часть массива
     * @param left     кол-во элементов левой части
     * @param right    кол-во элементов правой части
     */
    private static void merge(Integer[] arr, Integer[] leftArr, Integer[] rightArr, int left, int right) {
        int i = 0, j = 0, k = 0;
        try {
            while (i < left && j < right) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[k++] = leftArr[i++];
                } else {
                    arr[k++] = rightArr[j++];
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
        while (i < left) {
            arr[k++] = leftArr[i++];
        }
        while (j < right) {
            arr[k++] = rightArr[j++];
        }
    }

}
