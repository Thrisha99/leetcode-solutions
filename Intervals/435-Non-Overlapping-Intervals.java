
import java.util.Arrays;

/*
 * Problem: 435. Non-overlapping Intervals
 * Difficulty: Medium
 * Pattern: Greedy, Sorting, Intervals
 *
 * Approach:
 * 1. Sort intervals by ending value.
 * 2. Keep the interval that ends earliest.
 * 3. If the next interval overlaps, remove it.
 * 4. Otherwise, keep it and update the ending value.
 *
 * Time Complexity: O(n log n)
 * Auxiliary Space: O(log n) for sorting, typically.
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        // Step 1: Handle empty input
        if (intervals.length == 0) {
            return 0;
        }

        // Step 2: Sort by ending time (ascending)
        Arrays.sort(intervals,
            (a, b) -> Integer.compare(a[1], b[1]));

        // Step 3: Initialize variables
        int count = 0;                  // Number of intervals removed
        int end = intervals[0][1];      // End of last kept interval

        // Step 4: Process the remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // Current interval starts before the last kept interval ends
            if (intervals[i][0] < end) {
                count++;                // Remove current interval
            } else {
                // No overlap: keep current interval
                end = intervals[i][1];
            }
        }

        // Step 5: Return the minimum removals
        return count;
    }
}
