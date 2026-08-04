
/*
 * Problem: 1480. Running Sum of 1d Array
 * Link: https://leetcode.com/problems/running-sum-of-1d-array/
 *
 * Pattern:
 * Running Sum (Prefix Sum Foundation)
 *
 * Difficulty:
 * Easy
 *
 * Problem Description:
 * Given an array nums, return the running sum of nums.
 *
 * The running sum of an array is obtained by adding the
 * current element to the sum of all previous elements.
 *
 * Running Sum[i] = nums[0] + nums[1] + ... + nums[i]
 *
 * Intuition:
 * Each element only depends on the previous cumulative sum.
 * Instead of calculating the sum from the beginning every time,
 * keep adding the current element to the previous running sum.
 *
 * Approach:
 * - Traverse the array from index 1.
 * - Add the previous running sum to the current element.
 * - Continue until the end of the array.
 * - Return the modified array.
 *
 * Algorithm:
 * 1. Start from index 1.
 * 2. nums[i] += nums[i-1]
 * 3. Repeat until the last element.
 * 4. Return nums.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */


class Solution {
    public int[] runningSum(int[] nums) {
        
        for(int i=1;i<nums.length;i++){
            nums[i]+=nums[i-1];
        }
        return nums;
    }
}