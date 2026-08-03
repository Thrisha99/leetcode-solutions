/*
 * Problem: 1929. Concatenation of Array
 * Link: https://leetcode.com/problems/concatenation-of-array/
 *
 * Pattern: Arrays (Array Construction)
 * Difficulty: Easy
 *
 * Approach:
 * - Create a new array of size 2 * n.
 * - Traverse the original array once.
 * - Copy the current element to:
 *      1. ans[i]
 *      2. ans[i + n]
 * - Return the new array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int[] getConcatenation(int[] nums) {

        int n = nums.length;

        int[] ans = new int[2 * n];

        for (int i = 0; i < n; i++) {

            ans[i] = nums[i];
            ans[i + n] = nums[i];

        }

        return ans;
    }
}