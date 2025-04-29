package SortingAlgos;

import java.util.ArrayList;
import java.util.List;

class SelectionSort {

    public static void main(String[] args) {
        List<Integer> arr = List.of(5, 8, 3, 9, 4, 1, 7);
        System.out.println("Before: " + arr);
        List<Integer> res = selectionSort(new ArrayList<>(arr));

        System.out.println("After: " + res);
    }

    static List<Integer> selectionSort(List<Integer> list) {
        int len = list.size();
        for (int i = 0; i < len - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (list.get(j) < list.get(minIdx))
                    minIdx = j;
            }

            // swap ith < - > minIdx
            int temp = list.get(i);
            list.set(i, list.get(minIdx));
            list.set(minIdx, temp);
        }
        return list;
    }
}