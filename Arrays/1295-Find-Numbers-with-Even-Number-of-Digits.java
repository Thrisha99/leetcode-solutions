/*
 * Problem: 1295. Find Numbers with Even Number of Digits
 * Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Traverse each number in the array.
 * - Count the number of digits using repeated division by 10.
 * - If the digit count is even, increment the answer.
 *
 * Time Complexity: O(n × d)
 * where d is the number of digits (maximum 5 for this problem).
 *
 * Space Complexity: O(1)
 */

class Solution {

    public int findNumbers(int[] nums) {

        int count = 0;

        for (int num : nums) {

            int digits = 0;
            int temp = num;

            while (temp > 0) {
                digits++;
                temp /= 10;
            }

            if (digits % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}