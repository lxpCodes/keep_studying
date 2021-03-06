package sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort3
 * @Description 快排简单实现
 *  从数列中挑出一个元素，称为 “基准”（pivot）；
 *  重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *  递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * @Author liangxp
 * @Date 2020/9/27 19:39
 **/
public class QuickSort3 {

    public static void main(String[] args) {
        int[] arr = new int[]{38, 49, 65, 97, 49, 64, 27, 49, 78};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end)
            return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            quickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            quickSort(array, smallIndex + 1, end);
        return array;
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int partition2(int[] nums, int l, int r) {
        int pivot = nums[r];//随机取最右元素为pivot(中心轴)

        //此处将i移到l-1位置，去假定当前<=pivot的区域不存在
        //然后根据循环对比的结果，将<=pivot的区域逐渐通过i++扩大
        int i = l - 1;// <= pivot的区间下标
        for (int j = l; j <= r - 1; ++j) {// j为当前数所在位置的指针

            /**
             * 当j指针移动到的位置的元素 <= pivot时，当前数与<=pivot区的下一个数字交换
             * 同时<=pivot的区域向右扩一个位置，即i++
             */
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }

            //当nums[j] > pivot时，<=区的大小不变，当前指针位置右移一个，去对比下一个数字和pivot的大小，判断是否需要和
            // <=区的下一个数字进行交换，即循环体中的 j++
        }
        //上述结束后，i的左侧为<=pivot的数字，右侧为>pivot的数字，所以将pivot和i+1的位置交换，
        //至此，pivot左侧所有元素小于pivot,右侧所有元素大于pivot
        swap(nums, i + 1, r);
        return i + 1;//返回中心轴所在的下标，其实也没卵用
    }

}
