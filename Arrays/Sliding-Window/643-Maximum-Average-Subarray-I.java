/*
 * Problem: 643. Maximum Average Subarray I
 * Link: https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * Pattern:
 * Sliding Window (Fixed Size)
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * We need the maximum average of a subarray of size k.
 * Since the window size is fixed, instead of recalculating
 * the sum for every window, update the sum by:
 * - Adding the new element entering the window.
 * - Removing the element leaving the window.
 *
 * Approach:
 * - Calculate the sum of the first k elements.
 * - Store it as the maximum sum.
 * - Slide the window one position at a time.
 * - Update the running sum by adding one element and
 *   removing one element.
 * - Keep track of the maximum sum.
 * - Return maxSum / k.
 *
 * Algorithm:
 * 1. Compute first window sum.
 * 2. Store it as maxSum.
 * 3. Slide the window.
 * 4. Update sum.
 * 5. Update maxSum.
 * 6. Return average.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {

            sum += nums[i];
            sum -= nums[i - k];

            maxSum = Math.max(maxSum, sum);

        }

        return (double) maxSum / k;
    }
}