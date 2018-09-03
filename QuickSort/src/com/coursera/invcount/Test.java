package com.coursera.invcount;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {

    private static final int TOTAL_SIZE = 100000;
    private static final String FILE_PATH = "./data/IntegerArray.txt";


    public static void main(String args[]) throws IOException{

        long result = -1;
        int[] arr = new int[TOTAL_SIZE];

        Path path = Paths.get(FILE_PATH);
        Scanner scn = new Scanner(path);
        scn.useDelimiter(System.getProperty("line.separator"));

        try{
            int i=0;
            while(scn.hasNext()){
                arr[i] = new Integer(scn.next());
                ++i;
            }

            result = InversionCount.invcount(arr,0,arr.length-1);
            System.out.println("Total Inversions: " + result);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            scn.close();
        }

    }

}
