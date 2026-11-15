/*
 * LeetCode 8 - String to Integer (atoi)
 *
 * Difficulty: Medium
 * Topics: String, Simulation
 * Pattern: String Traversal + Overflow Handling
 *
 * Problem:
 * Convert a string into a 32-bit signed integer.
 *
 * Rules:
 * 1. Ignore leading spaces.
 * 2. Identify the optional sign (+ or -).
 * 3. Read consecutive digits.
 * 4. Stop at the first non-digit.
 * 5. Return 0 if no digits are found.
 * 6. Clamp overflow to Integer.MAX_VALUE or Integer.MIN_VALUE.
 *
 * Examples:
 * Input:  "42"                    Output: 42
 * Input:  "   -42"                Output: -42
 * Input:  "4193 with words"       Output: 4193
 * Input:  "words and 987"         Output: 0
 * Input:  "-91283472332"          Output: -2147483648
 * Input:  "2147483648"            Output: 2147483647
 *
 * Integer Range:
 * Integer.MAX_VALUE =  2147483647
 * Integer.MIN_VALUE = -2147483648
 *
 * --------------------------------------------------
 * APPROACH 1: Built-in Parsing (Conceptual)
 * --------------------------------------------------
 *
 * Extract the numeric prefix and convert using
 * Integer.parseInt().
 *
 * Limitation:
 * Integer.parseInt() throws NumberFormatException
 * when the value exceeds the int range.
 *
 * --------------------------------------------------
 * APPROACH 2: Manual Parsing with int Accumulator
 * --------------------------------------------------
 *
 * Formula:
 * result = result * 10 + digit
 *
 * Before updating result, check overflow.
 *
 * Limitation:
 * int cannot represent positive 2147483648.
 * Therefore, a positive accumulator using int needs
 * special handling for Integer.MIN_VALUE.
 *
 * --------------------------------------------------
 * APPROACH 3: Manual Parsing with long Accumulator
 * --------------------------------------------------
 *
 * IDEA:
 * Use long to temporarily store the absolute value.
 *
 * Why long?
 * int maximum  = 2147483647
 * int minimum  = -2147483648
 * long can safely store 2147483648.
 *
 * Before updating result, check whether the next
 * digit would exceed the int boundary.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 */

class Solution {

    // APPROACH 3: Optimal solution using long accumulator
    public int myAtoi(String s) {

        int i = 0;
        int n = s.length();

        // Step 1: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Identify sign
        int sign = 1;

        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            i++;
        }

        // Step 3: Initialize long accumulator
        long result = 0;

        // Step 4: Process consecutive digits
        while (i < n &&
               s.charAt(i) >= '0' &&
               s.charAt(i) <= '9') {

            int digit = s.charAt(i) - '0';

            // Step 5: Check overflow BEFORE updating result
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 &&
                 digit > (sign == 1 ? 7 : 8))) {

                return sign == 1
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }

            // Step 6: Build number
            result = result * 10 + digit;

            i++;
        }

        // Step 7: Apply sign and convert to int
        return (int) (result * sign);
    }
}