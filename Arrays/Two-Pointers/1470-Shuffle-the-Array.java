/*
 * Problem: 1470. Shuffle the Array
 * Link: https://leetcode.com/problems/shuffle-the-array/
 *
 * Pattern:
 * Array / Simulation
 *
 * Approach:
 * Brute Force using an auxiliary array
 *
 * Difficulty:
 * Easy
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 */

class Solution {
    public int[] shuffle(int[] nums, int n) {

        int[] ans = new int[2 * n];

        int i = 0;      // Start of X values
        int j = n;      // Start of Y values
        int k = 0;      // Position in result array

        while (k < 2 * n) {
            ans[k++] = nums[i++];
            ans[k++] = nums[j++];
        }

        return ans;
    }
}
