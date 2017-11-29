/**
 * Created by Dtylan on 2016-07-11.
 */
public class KSmallest {

    public static void main(String[] args) {

        int[] array = {14,5,21,17,6,2,22,15,7,13};

        System.out.println(kSmallest(4, array, 0, array.length-1));
    }

    public static int kSmallest(int k, int[] array, int first, int last) {

        //Partition the items of the array about the pivot
        int pivot = partitionArray(array, first, last);

        if(k < pivot - first + 1) { //K is smaller than the amount of elements in the left partition, so subdivide it
            return kSmallest(k, array, first, pivot-1);
        }

        else if(k == pivot - first + 1) {
            return array[pivot];
        }

        return kSmallest(k-(pivot-first+1), array, pivot+1, last);
    }

    /**
     * Quicksort partition scheme using Lomento's Pivot Selection
     * @param arr Full unsorted array
     * @param lowBound Beginning of the partition (assuming 0 based)
     * @param highBound End of the partition (for example length-1)
     * @return The new pivot's position
     */
    public static int partitionArray(int[] arr, int lowBound, int highBound) {

        //Our key is the end of the partition
        int key = arr[highBound];

        //Start another index at one position behind the loops
        int i = lowBound-1;
        for(int j = lowBound; j < highBound; j++) { //Loop until the end of the partition

            //If we encounter an element less than the key.
            if(arr[j] <= key) {
                //Increment our other pointer, then swap both elements.
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        //At the end, we must swap the key element and the determined pivot.
        i++;
        int temp = arr[i];
        arr[i] = arr[highBound];
        arr[highBound] = temp;

        //Return the index of the pivot to be used in later calculations.
        return i;
    }
}
