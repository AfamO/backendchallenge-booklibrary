package com.polarisdigitech.backendchallenge.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SortingAlgorithms {

    public List<Integer> bubbleSort(Integer[] arrs){
        for (int i = 0; i < arrs.length; i++){

            for (int j = i+1; j < arrs.length; j++){
                if(arrs[j] < arrs[i])
                    swap(arrs,i,j);
            }
        }
        return Arrays.asList(arrs);
    }

    public List<Integer> insertionSort(Integer[] arrs){
        for (int i = 1; i < arrs.length; i++){
            for (int j = i; j < i; j--)
                swap(arrs,i,j);
        }
        Math.max(1,2);
        return Arrays.asList(arrs);
    }

    public List<Integer> selectionSort(Integer[] arrs){
        for (int i = 0; i < arrs.length; i++){
            int smallestIndex = -1;
            for (int j = i+1; j < arrs.length; j++){
                if (arrs[j] < smallestIndex)
                {
                    smallestIndex = j;
                }
            }
            if(smallestIndex!= -1)
                swap(arrs, i, smallestIndex);
        }
        return Arrays.asList(arrs);
    }

    public void swap(Integer [] arrs, int i, int j){
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    public void callSortingAlgorithms() {
        Integer[] arrs = {4, 2, 9, 6, 23, 12, 34, 0, 1};
        log.info("BubbleSort =="+this.bubbleSort(arrs));
        log.info("InsertionSort =="+this.insertionSort(arrs));
        log.info("SelectionSort=="+this.selectionSort(arrs));

    }

}
