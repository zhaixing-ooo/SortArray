package org.example;


import java.util.Arrays;

public class CompareSortArray {
    public static void main(String[] args) {
        int[] arr = {2,4,5,7,3,2,5,6,7,8,9,9,3,2,10};
//        shellsort(arr);
//        heapSort(arr);
        quickSortOneWay(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
     //单路快排
    //找切割点
    //以切割点为界，左边比它小，右边比它大
    //递归调用使得左右继续重复上述操作
    private static void quickSortOneWay(int[] arr,int l,int r) {
        if(l >= r)return;
        int p = partion(arr,l,r);
        quickSortOneWay(arr,l,p-1);
        quickSortOneWay(arr,p+1,r);
    }
    private static int partion(int[] arr, int l, int r) {
        int  v = arr[l];
        int j = l;
        for (int i = l+1; i <= r; i++){
            if(arr[i] <= v){
                swap(arr,i,j+1);
                j++;
            }
        }
       swap(arr,l,j);
        return j;
    }


    //希尔排序（时间复杂度：n^1.5）
    //基于插入排序（先用大的gap使得数组大致有序，然后在进行排序，提高效率）
    private static void shellsort(int[] arr) {
        int len  = arr.length;
        for(int gap = len/2;gap>=1;gap/=2){
            for(int i = gap;i<arr.length;i++){
                for (int j = i;j-gap>=0;j-=gap){
                    if(arr[j-gap] > arr[j])swap(arr,j,j-gap);
                }
            }
        }
    }
    //堆排序
    //从后往前，依次进行堆化处理，堆化完毕将末位元素与首元素交换，继续堆化，交换，直至到达首元素。
    private static void heapSort(int[] arr) {
        int len = arr.length-1;
        for(int i = len;i >= 0;i--){
            heapfiy(arr,i);
            swap(arr,0,i);
        }
    }
    //堆化处理，从后往前
    private static void heapfiy(int[] arr,int len) {
        for (int i = len;i>=0;i--){
            siftdown(arr,i,len);
        }
    }

    private static void siftdown(int[] arr, int i,int len) {
        //只要有左孩子，就进行排序
        while (leftchild(i) <= len){
            int j = leftchild(i);
            //如果有右孩子，将左右孩子进行比较，将较大值与父亲进行比较
            if(j + 1 <= len && arr[j] < arr[j + 1]){
                j = rightchild(i);
            }
            //若大于父亲，父亲节点交换下沉，继续上述操作，直至不能下沉为止
            if(arr[j] > arr[i]){
                swap(arr,i,j);
                i = j;
            }else {
                break;
            }
        }
    }
    //该节点的左 右孩子下标
    private static int rightchild(int i) {
        return 2 * i + 2;
    }

    private static Integer leftchild(int i) {
        return 2 * i + 1;
    }


    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}