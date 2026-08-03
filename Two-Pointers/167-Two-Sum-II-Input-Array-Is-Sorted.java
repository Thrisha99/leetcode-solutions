/*
 * Problem: 167. Two Sum II - Input Array Is Sorted
 * Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Pattern:
 * Two Pointers (Opposite Direction)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * Since the array is sorted, we don't need a HashMap.
 * We can place one pointer at the beginning and another
 * at the end. Based on the current sum, move one pointer
 * to get closer to the target.
 *
 * Approach:
 * - Initialize left at index 0.
 * - Initialize right at the last index.
 * - Calculate the sum.
 * - If sum equals target, return the indices.
 * - If sum is smaller than target, move left forward.
 * - If sum is greater than target, move right backward.
 *
 * Algorithm:
 * 1. left = 0
 * 2. right = n - 1
 * 3. While left < right
 *      Calculate sum
 *      Compare with target
 *      Move one pointer
 * 4. Return answer.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }

        }

        return new int[]{};
    }
}