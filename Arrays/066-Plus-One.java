/*
-------------------------------------------------------
Problem: 66. Plus One
Link: https://leetcode.com/problems/plus-one/

Pattern:
Arrays

Difficulty:
Easy

Approach:
1. Start traversing from the last digit.
2. If the digit is less than 9:
      - Increment it.
      - Return the same array.
3. If the digit is 9:
      - Change it to 0.
      - Carry continues to the previous digit.
4. If every digit was 9:
      - Create a new array of size n + 1.
      - Put 1 at index 0.

Time Complexity:
O(n)

Space Complexity:
O(1)
Worst Case:
O(n) only when a new array is created.

Tags:
Arrays
Simulation

-------------------------------------------------------
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