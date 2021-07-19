import java.util.Random;
import java.util.Comparator;
public class DataGenerator {

    public static void main (String[] args) {
        Random rand = new Random();
        String arg = args[0]; //indicates which algorithm to run
        Integer[] sequence;
        Comparator<Integer> comp = (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return 1;
            } //if
        };

        long[] comparisons = new long[10]; //keeps track of the # of comparisons for each run
        if (arg.equals("i")) { //finds average # comparisons for insertion sort for each input size
            for (int i = 1000; i < 100001; i += 1000) { // loops through for sizes 1,000-100,000
                sequence = new Integer[i];
                for (int j = 0; j < 10; j++) { // 10 runs of the alg for each input size
                    Sort.setInsertionCounter(0);
                    rand.setSeed((j + 1)*100);
                    for (int k = 0; k < sequence.length; k++) { // generates a new sequence
                        sequence[k] = rand.nextInt(i+1);
                    } //for
                    Sort.insertionSort(sequence);
                    comparisons[j] = Sort.insertComparisons(); //keeps track of comparisons
                } //for
                Double sum = 0.0;
                for (int k = 0; k < comparisons.length; k++) { //computes average of 10 comparisons
                    sum += comparisons[k];
                } //for
                System.out.printf("%.1f\n", sum / 10); //prints average comparisons for each input
            } //for
        } else if (arg.equals("m")) { //finds average # of comparisons for merge sort for each input
            for (int i = 1000; i < 100001; i += 1000) { // loops through for sizes 1,000-100,000
                sequence = new Integer[i];
                for (int j = 0; j < 10; j++) { //10 runs of the alg for each input size
                    Sort.setMergeCounter(0);
                    rand.setSeed((j + 1)*100);
                    for (int k = 0; k < sequence.length; k++) { //generates a new sequence
                        sequence[k] = rand.nextInt(i+1);
                    } //for
                    Sort.mergeSort(sequence, comp);
                    comparisons[j] = Sort.mergeComparisons(); //keeps track of comparisons
                } //for
                Double sum = 0.0;
                for (int k = 0; k < comparisons.length; k++) { //computes average of 10 comparisons
                    sum += comparisons[k];
                } //for
                System.out.printf("%.1f\n", sum / 10); //prints average comparisons for each input
            } //for
        } else if (arg.equals("q")) { //finds average # of comparisons for quicksort for each input
            for (int i = 1000; i < 100001; i += 1000) { //loops through sizes 1,000-100,000
                sequence = new Integer[i];
                for (int j = 0; j < 10; j++) { //10 runs of alg for each input size
                    Sort.setQuickCounter(0);
                    rand.setSeed((j + 1)*100);
                    for (int k = 0; k < sequence.length; k++) { //generates a new sequence
                        sequence[k] = rand.nextInt(i+1);
                    } //for
                    Sort.quickSort(sequence, comp, 0, sequence.length - 1);
                    comparisons[j] = Sort.quickComparisons(); //keeps track of comparisons
                } //for
                Double sum = 0.0;
                for (int k = 0; k < comparisons.length; k++) { //computes average of 10 comparisons
                    sum += comparisons[k];
                } //for
                System.out.printf("%.1f\n", sum / 10); // prints average comparisons for each input
            } //for
        } //if
    } //main



} //Data Generator
