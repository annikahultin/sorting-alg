import java.util.Arrays;
import java.util.Comparator;

public class Sort {

    static long mergeCounter = 0;
    static long quickCounter = 0;
    static long insertionCounter = 0;
    static Integer[] sequence;

    public static void main(String[] args) {
        String arg = args[0]; //which alg is being used
        sequence = new Integer[args.length -1]; //sequence given by user
        for (int i = 1; i < args.length; i++) { //parses the sequence given by the user
            sequence[i - 1] = Integer.parseInt(args[i]);
        } //for
        Comparator<Integer> comp = (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return 1;
            } //if
        };
        if (arg.equals("-i")) {
            printInsertionSort(sequence);
        } else if (arg.equals("-m")) {
            printMergeSort(sequence, comp);
        } else if (arg.equals("-q")) {
            printQuickSort(sequence, comp);
        } else {
            System.out.println("Invalid command line argument");
        } //if
    } //main

    // Helper methods to be used in the DataGenerator class to help compute the average
    // number of comparisons for input sizes 1,000-100,000

    public static void setQuickCounter(long count) {
        quickCounter = count;
    } //setQuickCounter

    public static void setMergeCounter(long count) {
        mergeCounter = count;
    } //setMergeCounter

    public static void setInsertionCounter(long count) {
        insertionCounter = count;
    } //setInsertionCounter

    public static long insertComparisons() {
        return insertionCounter;
    } //insertComparisons

    public static long mergeComparisons() {
        return mergeCounter;
    } //insertComparisons

    public static long quickComparisons() {
        return quickCounter;
    } //insertComparisons

    // Methods that handle printing out the results of Insertion Sort, Merge Sort,
    // and Quick Sort to the user

    public static void printInsertionSort(Integer[] sequence) {
        insertionCounter = 0;
        System.out.print("Insertion Sort:");
        insertionSort(sequence);
        System.out.println(Arrays.toString(sequence));
        System.out.println("Input size: " + sequence.length);
        System.out.println("Total # of comparisons: " + insertionCounter);
    } //printInsertionSort

    public static void printMergeSort(Integer[] sequence, Comparator<Integer> comp) {
        mergeCounter = 0;
        System.out.print("Merge Sort: ");
        mergeSort(sequence, comp);
        System.out.println(Arrays.toString(sequence));
        System.out.println("Input size: " + sequence.length);
        System.out.println("Total # comparisons: " + mergeCounter);
    } //printMergeSort

    public static void printQuickSort(Integer[] sequence, Comparator<Integer> comp) {
        quickCounter = 0;
        quickSort(sequence, comp, 0, sequence.length - 1);
        System.out.println("Quick sort: " + Arrays.toString(sequence));
        System.out.println("Input size: " + sequence.length);
        System.out.println("Total # comparisons: " + quickCounter);
    } //printQuickSort

    public static void insertionSort(Integer[] sequence) {
        for (int i = 0; i < sequence.length; i++) { //inserts each value to sorted portion of array
            Integer val = sequence[i]; // value to be sorted
            int j = i - 1;
            while (j >= 0 && sequence[j] > val) { //moves all values greater than val over one spot
                insertionCounter ++;
                sequence[j + 1] = sequence[j];
                j --;
            } //while
            sequence[j + 1] = val; //inserts the value to its correct position
        } //for

    } //insertionSort

    public static <Integer> void merge
    (Integer[] s1, Integer[] s2, Integer[] sequence, Comparator<Integer> comp){
        int i = 0, j = 0;
        while (i + j < sequence.length) {
            //determines whether s1 value or s2 values comes next in sorted order
            if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
                sequence[i + j] = s1[i++];
                mergeCounter++;
            } else {
                sequence[i + j] = s2[j++];
                mergeCounter++;
            } //if
        } //while
    } //merge

    public static <Integer> void mergeSort(Integer[] sequence, Comparator<Integer> comp) {
        int size = sequence.length;
        if (size < 2) { // base case
            return;
        } //if
        int mid = size/2;
        //two arrays to hold each have of the sequence
        Integer[] s1 = Arrays.copyOfRange(sequence, 0, mid);
        Integer[] s2 = Arrays.copyOfRange(sequence, mid, size);
        // recursively call mergeSort until base case is reached
        mergeSort(s1, comp);
        mergeSort(s2, comp);
        merge(s1, s2, sequence, comp); //merges the arrays together in sorted order
    } //mergeSort

    public static <Integer> void quickSort(Integer[] sequence, Comparator<Integer> comp,
    int a, int b) {
        if (a >= b) { //base case
            return;
        } //if
        int left = a;
        int right = b - 1;
        Integer pivot = sequence[b]; //pivot is last element in array
        Integer temp;
        while (left <= right) { //determines the correct index of the pivot
            //determines if values left of the pivot need to be swapped to the right of it
            while (left <= right && comp.compare(sequence[left], pivot) < 0) {
                left ++;
                quickCounter ++;
            } //while
            //determines id values right of the pivot need to be swapped to the left of it
            while (left <= right && comp.compare(sequence[right], pivot) > 0) {
                right --;
                quickCounter ++;
            } //while
            if (left <= right) { //swaps values to the left or right of the pivot
                temp = sequence[left];
                sequence[left] = sequence[right];
                sequence[right] = temp;
                left ++;
                right --;
            } //if
        } //while
        //swaps pivot to its correct index
        temp = sequence[left];
        sequence[left] = sequence[b];
        sequence[b] = temp;
        //recursive calls to quicksort until the array is completely sorted
        quickSort(sequence, comp, a, left - 1);
        quickSort(sequence, comp, left + 1, b);
    } //quickSort
} //Sort
