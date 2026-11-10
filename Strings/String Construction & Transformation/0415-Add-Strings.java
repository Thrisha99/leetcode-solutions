
/*
 * LeetCode 415 - Add Strings
 *
 * Link: https://leetcode.com/problems/add-strings/
 * Difficulty: Medium
 * Topic: Strings
 * Pattern: Two Pointers + Simulation + Carry
 *
 * Problem:
 * Given two non-negative integers represented as strings,
 * return their sum as a string.
 *
 * Important:
 * We cannot convert the complete strings into integer types.
 *
 * Approaches:
 * 1. Brute Force - String concatenation
 * 2. Optimal - StringBuilder + Two Pointers
 *
 * Optimal Time Complexity: O(max(m, n))
 * Output Space: O(max(m, n))
 * Auxiliary Space excluding output: O(1)
 *
 * m = num1.length()
 * n = num2.length()
 */


// ============================================================
// APPROACH 1: BRUTE FORCE
// ============================================================
//
// Idea:
// Perform digit-by-digit addition from right to left.
// Construct the answer by prepending each digit to a String.
//
// Drawback:
// String concatenation creates new String objects.
// Repeated prepending can take O(L^2) time.
//
// L = max(m, n)


class BruteForceAddStrings {

    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        String result = "";

        while (i >= 0 || j >= 0) {

            // Default digit is zero when one number
            // has no remaining digits.

            int d1 = 0;
            int d2 = 0;

            if (i >= 0) {
                d1 = num1.charAt(i) - '0';
            }

            if (j >= 0) {
                d2 = num2.charAt(j) - '0';
            }

            // Add both digits and the previous carry.

            int sum = d1 + d2 + carry;

            // Extract the current digit.

            int digit = sum % 10;

            // Calculate the carry for the next position.

            carry = sum / 10;

            // Prepend the digit to the result.

            result = digit + result;

            i--;
            j--;
        }

        // Handle the final carry.

        if (carry != 0) {
            result = carry + result;
        }

        return result;
    }
}


// ============================================================
// APPROACH 2: OPTIMAL - STRINGBUILDER + TWO POINTERS
// ============================================================
//
// This is the user's corrected implementation.
//
// Idea:
// 1. Start from the last digit of both strings.
// 2. Add corresponding digits and carry.
// 3. Extract digit using % 10.
// 4. Calculate carry using / 10.
// 5. Append digits to StringBuilder.
// 6. Reverse the StringBuilder.
// 7. Return the result.
//
// Time Complexity: O(max(m, n))
// Output Space: O(max(m, n))
// Auxiliary Space excluding output: O(1)


class Solution {

    public String addStrings(String num1, String num2) {

        // STEP 1: Initialize pointers at the last positions.

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        // Stores carry from the previous addition.

        int carry = 0;

        // Stores result digits in reverse order.

        StringBuilder sb = new StringBuilder();

        // STEP 2: Continue while either number has digits.

        while (i >= 0 || j >= 0) {

            // STEP 3: Initialize both digits to zero.
            //
            // This handles numbers having different lengths.

            int d1 = 0;
            int d2 = 0;

            // Extract digit from num1 if available.

            if (i >= 0) {
                d1 = num1.charAt(i) - '0';
            }

            // Extract digit from num2 if available.

            if (j >= 0) {
                d2 = num2.charAt(j) - '0';
            }

            // STEP 4: Add both digits and previous carry.

            int sum = d1 + d2 + carry;

            // STEP 5: Extract the current result digit.
            //
            // Example:
            // sum = 15
            // digit = 15 % 10 = 5

            int digit = sum % 10;

            // STEP 6: Calculate carry.
            //
            // Example:
            // sum = 15
            // carry = 15 / 10 = 1

            carry = sum / 10;

            // STEP 7: Append digit to StringBuilder.
            //
            // Digits are appended from right to left.
            //
            // Example:
            // First digit  -> "8"
            // Second digit -> "86"
            // Third digit  -> "861"

            sb.append(digit);

            // STEP 8: Move both pointers left.

            i--;
            j--;
        }

        // STEP 9: Handle the remaining carry.
        //
        // Example:
        // 9 + 9 = 18
        // digit = 8
        // carry = 1
        //
        // Append the remaining 1.

        if (carry != 0) {
            sb.append(carry);
        }

        // STEP 10: Reverse the digits and return.
        //
        // Before reverse: "861"
        // After reverse:  "168"

        return sb.reverse().toString();
    }
}


// ============================================================
// ALTERNATIVE OPTIMAL IMPLEMENTATION
// ============================================================
//
// Instead of handling carry after the loop,
// include carry directly in the while condition.
//
// Time Complexity: O(max(m, n))
// Output Space: O(max(m, n))


class CarryInLoopAddStrings {

    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        StringBuilder sb = new StringBuilder();

        // Continue while digits OR carry remain.

        while (i >= 0 || j >= 0 || carry > 0) {

            // Start sum with the existing carry.

            int sum = carry;

            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }

            int digit = sum % 10;

            carry = sum / 10;

            sb.append(digit);
        }

        return sb.reverse().toString();
    }
}
