/*
 * Problem: 485. Max Consecutive Ones
 * Link: https://leetcode.com/problems/max-consecutive-ones/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Traverse the array once.
 * - Count consecutive 1's.
 * - Reset the count when a 0 is encountered.
 * - Keep track of the maximum count throughout the traversal.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {

        int count = 0;
        int max = 0;

        for (int num : nums) {

            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }

        }

        return max;
    }
}