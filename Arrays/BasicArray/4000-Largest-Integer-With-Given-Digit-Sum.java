/*
 * Problem: 4000. Largest Integer With Given Digit Sum
 * Link: https://leetcode.com/problems/largest-integer-with-given-digit-sum/
 *
 * Pattern: Greedy
 * Difficulty: Easy
 *
 * Approach:
 * - If the required digit sum is 0, return 0.
 * - If the required digit sum is greater than 9 × n,
 *   it is impossible to construct the number.
 * - Traverse from the most significant digit.
 * - At each position, place the largest possible digit
 *   (maximum 9) while maintaining the remaining sum.
 * - Continue until all digits are assigned.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int largestInteger(int n, int s) {

        if (s == 0) {
            return 0;
        }

        if (s > 9 * n) {
            return -1;
        }

        int ans = 0;

        while (n != 0) {

            int digit = Math.min(9, s);

            ans = ans * 10 + digit;

            s -= digit;
            n--;
        }

        return ans;
    }
}