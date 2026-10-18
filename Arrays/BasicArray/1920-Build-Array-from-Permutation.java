/*
 * Problem: 1920. Build Array from Permutation
 * Link: https://leetcode.com/problems/build-array-from-permutation/
 *
 * Pattern: Arrays (Index Mapping)
 * Difficulty: Easy
 *
 * Approach:
 * - Traverse the array once.
 * - For every index i, place nums[nums[i]] into the answer array.
 * - Return the newly built array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int[] buildArray(int[] nums) {

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            ans[i] = nums[nums[i]];

        }

        return ans;
    }
}