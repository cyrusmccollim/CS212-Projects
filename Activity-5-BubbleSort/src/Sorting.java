import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] array = new int[]{7,9,4,3,6,8,4,2,2,7,9,0,0,4,2,2,4,8,9,53,8634,8,43,37,13,97,98,125,0,5,21,42,-95,-52};
        System.out.println(Arrays.toString(bubbleSortIntegerArray(array)));
    }

    public static int[] bubbleSortIntegerArray(int[] array){
        boolean swapOccurred;
        do {
            swapOccurred = false;
            for(int i = 1; i < array.length; i++){
                if (array[i] < array [i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    swapOccurred = true;
                }
            }
        } while (swapOccurred);
        return array;
    }
}
