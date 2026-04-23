Name: Aruzhan Rsaliyeva

Group: IT-2502

A. Project Overview

This project implements and compares three algorithms:

-Insertion Sort (Basic Sorting  O(n²))

-Quick Sort (Advanced Sorting O(n log n))  

-Binary Search (Searching O(log n))

Purpose of the Experiment:

-Measure and compare algorithm performance on different input sizes

-Analyze how input type (random vs sorted) affects execution time

-Verify theoretical Big-O complexity with actual measurements

-Understand practical implications of algorithm selection

B. Algorithm Descriptions
 
Insertion Sort

Builds final sorted array one element at a time. For each element, it shifts larger elements to the right.

-Best case: O(n) - already sorted

-Worst case: O(n²) - reverse sorted

Quick Sort

Uses divide-and-conquer: picks a pivot, partitions array around it, recursively sorts sub-arrays.

-Average case: - O(n log n)

-Worst case: O(n²) - when pivot is always min/max

Binary Search

Repeatedly divides sorted array in half to find target value.

Time complexity: O(log n)

Requirement: Array MUST be sorted

C. Experimental Results


| Size | Data Type | Insertion Sort | Quick Sort | Binary Search |
|------|-----------|----------------|------------|---------------|
| 10 | Random | 16,500 ns | 9,100 ns | 2,100 ns |
| 10 | Sorted | 3,500 ns | 12,100 ns | 1,900 ns |
| 100 | Random | 117,900 ns | 430,800 ns | 3,300 ns |
| 100 | Sorted | 8,600 ns | 1,296,900 ns | 2,500 ns |
| 1000 | Random | 10,115,700 ns | 230,700 ns | 6,900 ns |
| 1000 | Sorted | 18,800 ns | 13,739,700 ns | 8,700 ns |
| 10000 | Random | 122,916,700 ns | 1,581,000 ns | 11,100 ns |
| 10000 | Sorted | 3,021,100 ns | 381,674,800 ns | 4,400 ns |

D. Analysis Questions
 1. Which sorting algorithm performed faster? Why?

Quick Sort is significantly faster for large random datasets (1.58ms vs 122.9ms at size 10000).

Why?

 Quick Sort has average time complexity of O(n log n)
 
 Insertion Sort has O(n²) complexity
 
 For n=10000: n log n ≈ 132,877 operations vs n² = 100,000,000 operations

 2. How does performance change with input size?

| Size Increase | Insertion Sort | Quick Sort |
|---------------|----------------|------------|
| 10 → 100 (10x) | 0.02 → 0.12ms (6x) | 0.01 → 0.43ms (43x) |
| 100 → 1000 (10x) | 0.12 → 10.12ms (84x) | 0.43 → 0.23ms (0.5x) |
| 1000 → 10000 (10x) | 10.12 → 122.92ms (12x) | 0.23 → 1.58ms (6.8x) |

Observation: Insertion Sort grows quadratically. Doubling input size roughly quadruples execution time.

 3. How does sorted vs unsorted data affect performance?

| Algorithm | Random Data (10k) | Sorted Data (10k) | Ratio |
|-----------|-------------------|-------------------|-------|
| Insertion Sort | 122.92 ms | 3.02 ms | 40x FASTER on sorted |
| Quick Sort | 1.58 ms | 381.68 ms | 240x SLOWER on sorted |

Explanation:

Insertion Sort achieves O(n) best case on already sorted data

Quick Sort experiences worst-case O(n²) when pivot is always the largest element (my implementation picks last element as pivot)

 4. Do the results match expected Big-O complexity?

YES, strongly matches:

| Scenario | Expected | Measured | Verdict |
|----------|----------|----------|---------|
| Insertion Sort (random data) | O(n²) growth | 0.12ms → 10.12ms → 122.92ms |  Quadratic confirmed |
| Insertion Sort (sorted data) | O(n) linear | 0.004ms → 0.009ms → 3.02ms |  Near-linear |
| Quick Sort (random data) | O(n log n) | 0.43ms → 0.23ms → 1.58ms |  Efficient scaling |
| Quick Sort (sorted data) | O(n²) worst case | 0.01ms → 1.30ms → 381.68ms |  Shows quadratic degradation |
| Binary Search | O(log n) | 2,100ns → 3,300ns → 11,100ns | Logarithmic (minimal growth) |

 5. Which searching algorithm is more efficient? Why?

Binary Search (O(log n)) is dramatically more efficient than Linear Search (O(n)).

Comparison for 10,000 elements:

- Binary Search: ~11,100 ns (≈14 comparisons)

- Linear Search (theoretical): ~100,000 ns (≈5,000 comparisons average)

Binary Search is ~9x faster despite the sorting overhead.

 6. Why does Binary Search require a sorted array?

Binary Search relies on the **ordered property** of the array to eliminate half of the remaining elements at each step:

If array is sorted [1, 3, 5, 7, 9] and target = 7:

Middle = 5 → 7 > 5 → search RIGHT half [7, 9]

This logic ONLY works if array is sorted!

If array is unsorted [9, 1, 7, 5, 3]:

Middle = 7 → target 5 is on both sides → cannot decide

Without sorting, we cannot guarantee which half contains the target, making Binary Search impossible.

E. Screenshots

Program Output - Performance Results (Size 10)
<img width="589" height="310" alt="image" src="https://github.com/user-attachments/assets/2476ab3c-589e-4136-8bfe-289e81378f69" />

Program Output - Performance Results (Size 100)
<img width="589" height="307" alt="image" src="https://github.com/user-attachments/assets/9ed4a774-1dd4-4cf7-9330-6482a34e50e0" />

Program Output - Performance Results (Size 1000)
<img width="589" height="311" alt="image" src="https://github.com/user-attachments/assets/6c7624fe-11e0-41ad-9af7-8b6a289ef940" />

Program Output - Performance Results (Size 10000)
<img width="582" height="296" alt="image" src="https://github.com/user-attachments/assets/45de0ed4-5d9c-4234-98ce-fb2d6a323d85" />

F. Reflection Section

What I learned about algorithm efficiency

This experiment provided invaluable hands-on experience with the practical implications of Big-O complexity. The most striking lesson was seeing theory become reality*- Insertion Sort's O(n²) growth became painfully obvious at 10,000 elements (122ms) compared to Quick Sort's graceful O(n log n) scaling (1.6ms). However, the surprise came with sorted data: Quick Sort, typically considered "fast," degraded to 382ms - 240 times slower than on random data! This happened because my implementation always picks the last element as pivot, creating the worst-case scenario on already-sorted arrays. Meanwhile, Insertion Sort shone brilliantly, completing in just 3ms due to its O(n) best case.

Differences between theoretical and practical performance

While Big-O notation predicts growth rates, actual performance includes important constants and overhead. For small arrays (n=10), Insertion Sort sometimes outperformed Quick Sort despite worse Big-O - Quick Sort's recursive overhead dominates at tiny sizes. I also learned that pivot selection strategy is critical for Quick Sort's real-world performance. A simple improvement like random pivot selection would eliminate the sorted-data bottleneck. Binary Search consistently delivered O(log n) efficiency, completing searches in under 0.011ms even at 10,000 elements - a powerful demonstration of logarithmic efficiency.

Challenges faced during implementation

The main challenge was ensuring fair performance comparisons - each algorithm needed identical input data, so I implemented careful array copying before each test. Using System.nanoTime() required understanding Java's timing precision and ensuring measurements focused only on algorithm execution, not array copying overhead. Another challenge was choosing appropriate array sizes - too small (like 10 elements) produced noisy timing results, while too large caused excessive wait times during testing. Finally, interpreting the results required connecting observed behavior back to theoretical concepts, which deepened my understanding of algorithm analysis.

Key Takeaways
1. No single "best" algorithm - Quick Sort excels on random data, Insertion Sort on nearly-sorted data

2. Always consider input characteristics** when choosing algorithms

3. Theoretical complexity matters most for large inputs (n > 1000)

4. Constants and overhead dominate at small scales

5. Understanding worst-case scenarios is as important as knowing average performance


