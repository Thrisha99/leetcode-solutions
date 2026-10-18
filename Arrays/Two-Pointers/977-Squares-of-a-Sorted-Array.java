/*
 * Problem: 977. Squares of a Sorted Array
 * Link: https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Pattern:
 * Two Pointers (Opposite Direction)
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * The array is sorted, but after squaring,
 * negative numbers can become larger than positive numbers.
 *
 * Compare the absolute values at both ends,
 * place the larger square at the end of the answer array,
 * and move the corresponding pointer.
 *
 * Approach:
 * - Initialize left at the beginning.
 * - Initialize right at the end.
 * - Create an answer array.
 * - Fill the answer array from the last index.
 * - Compare absolute values of nums[left] and nums[right].
 * - Place the larger square and move the corresponding pointer.
 *
 * Algorithm:
 * 1. left = 0
 * 2. right = n - 1
 * 3. index = n - 1
 * 4. While left <= right
 *      Compare absolute values
 *      Store larger square
 *      Move pointer
 *      Decrement index
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 */

class Solution {

    public int[] sortedSquares(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;

        int[] ans = new int[nums.length];

        while (left <= right) {

            if (Math.abs(nums[left]) > Math.abs(nums[right])) {

                ans[index] = nums[left] * nums[left];
                left++;

            } else {

                ans[index] = nums[right] * nums[right];
                right--;

            }

            index--;

        }

        return ans;
    }
}