import java.util.Arrays;

public class Experiment {
    private Sorter sorter;
    private Searcher searcher;

    public Experiment() {
        sorter = new Sorter();
        searcher = new Searcher();
    }

    // ========== DEMONSTRATION WITH SMALL ARRAY ==========
    public void showDemo() {
        System.out.println("=" .repeat(70));
        System.out.println("ALGORITHM DEMONSTRATION (Small Array - 10 elements)");
        System.out.println("=" .repeat(70));

        int[] original = {64, 34, 25, 12, 22, 11, 90, 5, 77, 30};

        System.out.print("\nOriginal array: ");
        sorter.printArray(original);

        // Insertion Sort
        int[] basicCopy = sorter.copyArray(original);
        sorter.basicSort(basicCopy);
        System.out.print("\nInsertion Sort (O(n²)): ");
        sorter.printArray(basicCopy);

        // Quick Sort
        int[] advancedCopy = sorter.copyArray(original);
        sorter.advancedSort(advancedCopy);
        System.out.print("Quick Sort (O(n log n)): ");
        sorter.printArray(advancedCopy);

        // Binary Search
        System.out.print("\nBinary Search for '22': ");
        int index = searcher.search(advancedCopy, 22);
        System.out.println(index != -1 ? "Found at index " + index : "Not found");

        System.out.println("\n" + "-".repeat(70));
    }

    // ========== MEASUREMENT METHODS ==========

    public long measureSortTime(int[] arr, String type) {
        int[] arrCopy = sorter.copyArray(arr);

        long startTime = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(arrCopy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(arrCopy);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long measureSearchTime(int[] arr, int target) {
        int[] sortedArr = sorter.copyArray(arr);
        sorter.advancedSort(sortedArr);

        long startTime = System.nanoTime();
        int result = searcher.search(sortedArr, target);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    // ========== MAIN EXPERIMENTS ==========

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000, 10000};

        System.out.println("\n" + "=" .repeat(70));
        System.out.println("PERFORMANCE ANALYSIS");
        System.out.println("=" .repeat(70));

        System.out.println("\nAlgorithms under test:");
        System.out.println("  • Basic Sort   : Insertion Sort (O(n²))");
        System.out.println("  • Advanced Sort: Quick Sort (O(n log n))");
        System.out.println("  • Search       : Binary Search (O(log n))");

        for (int size : sizes) {
            System.out.println("\n" + "-".repeat(70));
            System.out.println("ARRAY SIZE: " + size);
            System.out.println("-".repeat(70));

            // Random data
            System.out.println("\n[RANDOM DATA]");
            testWithRandomData(size);

            // Sorted data
            System.out.println("\n[SORTED DATA]");
            testWithSortedData(size);
        }
    }

    private void testWithRandomData(int size) {
        int[] randomArray = sorter.generateRandomArray(size);

        long basicTime = measureSortTime(randomArray, "basic");
        System.out.printf("  Insertion Sort: %10d ns (%.3f ms)%n",
                basicTime, basicTime / 1_000_000.0);

        long advancedTime = measureSortTime(randomArray, "advanced");
        System.out.printf("  Quick Sort:     %10d ns (%.3f ms)%n",
                advancedTime, advancedTime / 1_000_000.0);

        int target = randomArray[randomArray.length / 2];
        long searchTime = measureSearchTime(randomArray, target);
        System.out.printf("  Binary Search:  %10d ns (%.3f ms)%n",
                searchTime, searchTime / 1_000_000.0);
    }

    private void testWithSortedData(int size) {
        int[] sortedArray = sorter.generateSortedArray(size);

        long basicTime = measureSortTime(sortedArray, "basic");
        System.out.printf("  Insertion Sort: %10d ns (%.3f ms) [BEST CASE]%n",
                basicTime, basicTime / 1_000_000.0);

        long advancedTime = measureSortTime(sortedArray, "advanced");
        System.out.printf("  Quick Sort:     %10d ns (%.3f ms)%n",
                advancedTime, advancedTime / 1_000_000.0);

        int target = sortedArray[sortedArray.length / 2];
        long searchTime = measureSearchTime(sortedArray, target);
        System.out.printf("  Binary Search:  %10d ns (%.3f ms)%n",
                searchTime, searchTime / 1_000_000.0);
    }
}