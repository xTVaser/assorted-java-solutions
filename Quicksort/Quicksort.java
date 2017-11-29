import java.util.Arrays;
import java.util.Random;

/**
 * Created by Dtylan on 2016-07-26.
 */
public class Quicksort {

    public static void main(String[] args) {

        int[] randomArray1 = generateArray("random", 10000000);
        int[] randomArray2 = Arrays.copyOf(randomArray1, 10000000);
        int[] randomArray3 = Arrays.copyOf(randomArray1, 10000000);
        int[] randomArray4 = Arrays.copyOf(randomArray1, 10000000);
        int[] randomArray5 = Arrays.copyOf(randomArray1, 10000000);

        int[] ascendingArray1 = generateArray("ascending", 5000);
        int[] ascendingArray2 = Arrays.copyOf(ascendingArray1 , 5000);
        int[] ascendingArray3 = Arrays.copyOf(ascendingArray1 , 5000);
        int[] ascendingArray4 = Arrays.copyOf(ascendingArray1 , 5000);
        int[] ascendingArray5 = Arrays.copyOf(ascendingArray1 , 5000);

        int[] descendingArray1 = generateArray("descending", 5000);
        int[] descendingArray2 = Arrays.copyOf(descendingArray1 , 5000);
        int[] descendingArray3 = Arrays.copyOf(descendingArray1 , 5000);
        int[] descendingArray4 = Arrays.copyOf(descendingArray1 , 5000);
        int[] descendingArray5 = Arrays.copyOf(descendingArray1 , 5000);


        System.out.println("Quicksort - Select First Index of Partition");
        long startTime = System.nanoTime();
        quickSortFirst(randomArray1, 0, randomArray1.length-1);
        long finalTime = System.nanoTime() - startTime;
        System.out.println("Test 1 - Random Array: \t\t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(ascendingArray1, 0, ascendingArray1.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 2 - Ascending Array: \t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(descendingArray1, 0, descendingArray1.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 3 - Descending Array: \t"+(double)finalTime/1000000+" milliseconds");
        System.out.println("--------------------------------------------\n");

        System.out.println("Quicksort - Select Median Index of Partition");
        startTime = System.nanoTime();
        quickSortFirst(randomArray2, 0, randomArray2.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 1 - Random Array: \t\t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(ascendingArray2, 0, ascendingArray2.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 2 - Ascending Array: \t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(descendingArray2, 0, descendingArray2.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 3 - Descending Array: \t"+(double)finalTime/1000000+" milliseconds");
        System.out.println("--------------------------------------------\n");

        System.out.println("Quicksort - Select Random Index of Partition");
        startTime = System.nanoTime();
        quickSortFirst(randomArray3, 0, randomArray3.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 1 - Random Array: \t\t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(ascendingArray3, 0, ascendingArray3.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 2 - Ascending Array: \t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(descendingArray3, 0, descendingArray3.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 3 - Descending Array: \t"+(double)finalTime/1000000+" milliseconds");
        System.out.println("--------------------------------------------\n");

        System.out.println("Quicksort - Lomuto Partitioning Scheme");
        startTime = System.nanoTime();
        quickSortFirst(randomArray4, 0, randomArray4.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 1 - Random Array: \t\t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(ascendingArray4, 0, ascendingArray4.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 2 - Ascending Array: \t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(descendingArray4, 0, descendingArray4.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 3 - Descending Array: \t"+(double)finalTime/1000000+" milliseconds");
        System.out.println("--------------------------------------------\n");

        System.out.println("Quicksort - Hoare Partitioning Scheme");
        startTime = System.nanoTime();
        quickSortFirst(randomArray5, 0, randomArray5.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 1 - Random Array: \t\t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(ascendingArray5, 0, ascendingArray5.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 2 - Ascending Array: \t"+(double)finalTime/1000000+" milliseconds");
        startTime = System.nanoTime();
        quickSortFirst(descendingArray5, 0, descendingArray5.length-1);
        finalTime = System.nanoTime() - startTime;
        System.out.println("Test 3 - Descending Array: \t"+(double)finalTime/1000000+" milliseconds");
        System.out.println("--------------------------------------------\n");

        System.out.println("Array Info:");
        System.out.println("Random Arrays: \t\t10,000,000 Items");
        System.out.println("Ascending Arrays: \t5000 Items");
        System.out.println("Descending Arrays: \t5000 Items");
    }

    public static void quickSortFirst(int[] array, int low, int high) {

        if(low < high) {

            int pivot = partitionFirst(array, low, high);
            quickSortFirst(array, low, pivot-1);
            quickSortFirst(array, pivot+1, high);
        }
    }

    public static int partitionFirst(int[] array, int low, int high) {

        int pivot = array[low];

        swap(array, low, high);

        int j = low;
        for(int i = low; i <= (high - 1); i++) {

            if(array[i] < pivot)
                swap(array, i, j++);
        }

        swap(array, j, high);
        return j;
    }

    public static void quickSortMiddle(int[] array, int low, int high) {

        if(low < high) {

            int pivot = partitionMiddle(array, low, high);
            quickSortMiddle(array, low, pivot-1);
            quickSortMiddle(array, pivot+1, high);
        }
    }

    public static int partitionMiddle(int[] array, int low, int high) {

        int pivot = array[(low+high)/2];

        swap(array, (low+high)/2, high);

        int j = low;
        for(int i = low; i <= (high - 1); i++) {

            if(array[i] < pivot)
                swap(array, i, j++);
        }

        swap(array, j, high);
        return j;
    }

    public static void quickSortRandom(int[] array, int low, int high) {

        if(low < high) {

            int pivot = partitionRandom(array, low, high);
            quickSortRandom(array, low, pivot-1);
            quickSortRandom(array, pivot+1, high);
        }
    }

    public static int partitionRandom(int[] array, int low, int high) {

        int randomIndex = (int)(Math.random() * ((high + 1) - low) + low);
        int pivot = array[randomIndex];

        swap(array, randomIndex, high);

        int j = low;
        for(int i = low; i <= (high - 1); i++) {

            if(array[i] < pivot)
                swap(array, i, j++);
        }

        swap(array, j, high);
        return j;
    }

    public static void quickSortLomuto(int[] array, int low, int high) {

        if(low < high) {

            int pivot = partitionLomuto(array, low, high);
            quickSortLomuto(array, low, pivot-1);
            quickSortLomuto(array, pivot+1, high);
        }
    }

    public static int partitionLomuto(int[] array, int low, int high) {

        int pivot = array[high];

        //Start another index at one position behind the loops
        int i = low-1;
        for(int j = low; j < high; j++) { //Loop until the end of the partition

            //If we encounter an element less than the key.
            if(array[j] <= pivot) {
                //Increment our other pointer, then swap both elements.
                swap(array, ++i, j);
            }
        }

        //At the end, we must swap the key element and the determined pivot.
        swap(array, ++i, high);

        //Return the index of the pivot to be used in later calculations.
        return i;
    }

    public static void quickSortHoare(int[] array, int low, int high) {

        if(low < high) {

            int pivot = partitionHoare(array, low, high);
            quickSortHoare(array, low, pivot);
            quickSortHoare(array, pivot+1, high);
        }
    }

    public static int partitionHoare(int[] array, int low, int high) {

        int pivot = array[low];

        int i = low - 1;
        int j = high + 1;

        while (true) {

            do {
                i++;
            } while(array[i] < pivot);

            do {
                j--;
            } while(array[j] > pivot);

            if(i >= j) {
                return j;
            }

            swap(array, i, j);
        }
    }


    public static void swap(int[] array, int indexI, int indexJ) {

        int temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }

    /**
     * Quick method to verify that the array is sorted, useful for testing.
     * @param sArray Potentially sorted integer array
     * @return True or false if sorted.
     */
    public static boolean verifySorted(int[] sArray) {

        //Loop through the array
        for(int i = 0; i < sArray.length-1; i++) {

            //if the next element is less than the current element, the array isn't in ascending order
            if(sArray[i] > sArray[i+1])
                return false;
        }
        return true;
    }

    /**
     * Generates and returns an int array filled with a specified amount of elements in a particle ordering scheme
     * @param args "sorted" for a sorted array in ascending order 1,2,3,4..."decreasing" for descending order, and anything else for random order.
     * @param size Number of elements in the array.
     * @return The generated array as an int[]
     */
    public static int[] generateArray(String args, int size) {

        //So we can generate negative numbers, size 5000 would give you -2500->2499
        int lowBound = size/2*-1;
        int highBound = size/2;

        //Ascending Order
        if (args.equalsIgnoreCase("sorted") || args.equalsIgnoreCase("ascending")) {

            int[] array = new int[size];
            for(int i = 0; i < array.length; i++)
                array[i] = lowBound+i;
            return array;
        }

        //Descending Order
        else if(args.equalsIgnoreCase("decreasing") || args.equalsIgnoreCase("descending")){

            int[] array = new int[size];
            for(int i = 0; i < array.length; i++)
                array[i] = highBound-i;
            return array;
        }

        //Else, do random order.
        int[] array = new int[size];
        Random rand = new Random();
        for(int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(size)-highBound;
        return array;
    }
}
