/*
 * Problem: 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 * Link: https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
 *
 * Pattern:
 * Sliding Window (Fixed Size)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * Every subarray has the same size (k).
 * Instead of recalculating the sum of each window,
 * update it by adding the new element and removing
 * the outgoing element.
 *
 * Approach:
 * - Calculate the first window sum.
 * - Check if its average satisfies the threshold.
 * - Slide the window across the array.
 * - Update the sum efficiently.
 * - Count every valid window.
 *
 * Algorithm:
 * 1. Compute the first window sum.
 * 2. If sum >= k × threshold, increment count.
 * 3. Slide the window.
 * 4. Update sum.
 * 5. Check the condition.
 * 6. Return count.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public int numOfSubarrays(int[] arr, int k, int threshold) {

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int count = 0;

        if (sum >= k * threshold) {
            count++;
        }

        for (int i = k; i < arr.length; i++) {

            sum += arr[i];
            sum -= arr[i - k];

            if (sum >= k * threshold) {
                count++;
            }

        }

        return count;
    }
}