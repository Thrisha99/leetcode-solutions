/*
 * Problem: 3925. Concatenate Array With Reverse
 * Link: https://leetcode.com/problems/concatenate-array-with-reverse/
 *
 * Pattern: Arrays (Array Construction + Reverse Traversal)
 * Difficulty: Easy
 *
 * Approach:
 * - Create a new array of size 2 * n.
 * - Copy the original array into the first half.
 * - Traverse the original array from the end and copy it
 *   into the second half.
 * - Return the new array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int[] concatenateAndReverse(int[] nums) {

        int n = nums.length;
        int[] ans = new int[2 * n];

        // Copy original array
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
        }

        // Copy reverse of original array
        for (int i = 0; i < n; i++) {
            ans[n + i] = nums[n - 1 - i];
        }

        return ans;
    }
}