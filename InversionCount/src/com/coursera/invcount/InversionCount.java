package com.coursera.invcount;

public class InversionCount {
	
    public static long merge_and_countSplitInv(int[] arr, int l, int m, int r){
        long inv = 0;

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];


        for(int i=0; i<n1; ++i){
            leftArr[i] = arr[l+i];
        }

        for(int j=0; j<n2; ++j){
            rightArr[j] = arr[m+1+j];
        }

        int i=0, j=0, k=l;
        while (i<n1 && j<n2){
            if(leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i];
                ++i;
            }else{
                arr[k] = rightArr[j];
                inv += n1-i;
                ++j;
            }
            ++k;
        }

        while(i<n1){
            arr[k] = leftArr[i];
            ++k;
            ++i;
        }

        while(j<n2){
            arr[k] = rightArr[j];
            ++k;
            ++j;
        }

        return inv;
    }


    public static long invcount(int[] arr, int l, int r){
        if(l<r){
            int m = (l+r)/2;

            long leftInv = invcount(arr, l, m);
            long rightInv = invcount(arr, m + 1, r);
            long splitInv = merge_and_countSplitInv(arr,l,m,r);

            return leftInv + rightInv + splitInv;
        }else
            return 0;
    }

}
