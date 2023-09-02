package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NoCompareSortArray {
    public static void main(String[] args) {
        int[] arr = {2,4,5,7,3,2,5,6,7,8,9,9,3,2,10};
//        Notesort(arr);
//        RadixSort(arr);
        bucketSort(arr,10);
        System.out.println(Arrays.toString(arr));
    }


    //计数排序
    //创造出一个数组（容量为：最大值-最小值）
    //统计每个数字对应的次数
    //下标零对应数组最小值
    //num = index - min
    //index = num - min
    private static void Notesort(int[] arr) {
        int index = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //找到最大最小值
        for (int i = 0;i<arr.length;i++){
            if(arr[i] <= min){
                min = arr[i];
            }
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //创建临时数组统计次数
        int[] temp = new int[max - min + 1];
        for (int i = 0;i<arr.length;i++){
            temp[arr[i] - min]++;
        }
        //根据次数打印数组
        for (int i = 0;i<temp.length;i++){
            for (int j = 1;j<=temp[i];j++){
                arr[index++] = i + min;
            }
        }
    }
    //基数排序
    //创建10（0-9）个桶(arrayList)
    //依次按照个十百千位装入桶中
    //在依次取出，最终有序
    private static void RadixSort(int[] arr) {
        ArrayList<Integer>[] temp = new ArrayList[10];
        for (int i = 0;i<temp.length;i++){
            temp[i] = new ArrayList<>();
        }
        int max = arr[0];
        for (int i = 0;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int r  =  (max +"").length();
        for (int j = 1;j<=r;j++) {
            for (int i = 0; i < arr.length; i++) {
                int index = index(arr[i], j);
                temp[index].add(arr[i]);
            }
            int k = 0;
            for (int i = 0; i < temp.length; i++) {
                while (temp[i].size() > 0){
                    arr[k++] = temp[i].remove(0);
                }
            }
        }
    }
//取出数字的各个位数上的数字
    private static int index(int i,int j) {
        String  str = i + "";
       if(j > (i + "").length()){ return 0;}
       else {
           return str.charAt(str.length() - j) - '0';
       }
    }
    //桶排序
    public static void bucketSort(int[] array, int bucketSize) {
        // 寻找最大值和最小值
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // 计算桶的数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将元素分配到桶中
        for (int value : array) {
            int bucketIndex = (value - minValue) / bucketSize;
            buckets.get(bucketIndex).add(value);
        }

        // 对每个桶中的元素进行排序，并放回原数组
        int currentIndex = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int value : bucket) {
                array[currentIndex++] = value;
            }
        }
    }
}
