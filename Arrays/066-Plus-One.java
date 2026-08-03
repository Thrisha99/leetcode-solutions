/*
 * Problem: 66. Plus One
 * Link: https://leetcode.com/problems/plus-one/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Traverse the array from right to left.
 * - If the current digit is less than 9, increment it and return.
 * - Otherwise, set it to 0 and continue carrying.
 * - If all digits become 0, create a new array with a leading 1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] ans = new int[digits.length + 1];
        ans[0] = 1;

        return ans;
    }
}