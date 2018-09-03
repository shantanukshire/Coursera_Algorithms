package com.coursera.quicksort;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {

    private static final int TOTAL_SIZE = 10000;
    private static final String FILE_PATH = "./data/QuickSort.txt";

    public static void main(String args[]) throws IOException{

        int result = -1;
        int[] leftPivArr = new int[TOTAL_SIZE];
        int[] rightPivArr = new int[TOTAL_SIZE];
        int[] medianPivArr = new int[TOTAL_SIZE];

        Path path = Paths.get(FILE_PATH);
        Scanner scn = new Scanner(path);
        scn.useDelimiter(System.getProperty("line.separator"));

        try{
            int i=0;
            while(scn.hasNext()){
            	int value = new Integer(scn.next());
            	leftPivArr[i] = value;
            	rightPivArr[i] = value;
            	medianPivArr[i] = value;
                ++i;
            }

            /* Quick Sort using first element as pivot */
            result = QuickSort.leftPivot(leftPivArr,0,leftPivArr.length-1);
            System.out.println("Comparisons for Left pivot: " + result);
            
            /*Quick Sort using last element as pivot*/
            result = QuickSort.rightPivot(rightPivArr,0,rightPivArr.length-1);
            System.out.println("Comparisons for Right pivot: " + result);
            
            /*Quick Sort using median as pivot*/
            result = QuickSort.medianPivot(medianPivArr,0,medianPivArr.length-1);
            System.out.println("Comparisons for Median pivot: " + result);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            scn.close();
        }

    }

}
