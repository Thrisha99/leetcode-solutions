/*
 * LeetCode 8 - String to Integer (atoi)
 *
 * Difficulty: Medium
 * Topics: String, Simulation
 *
 * Problem:
 * Convert a string into a 32-bit signed integer.
 *
 * Rules:
 * 1. Ignore leading spaces.
 * 2. Identify the optional sign (+ or -).
 * 3. Read consecutive digits.
 * 4. Stop at the first non-digit after parsing begins.
 * 5. Return 0 if no digits are found.
 * 6. Clamp overflow to Integer.MAX_VALUE or Integer.MIN_VALUE.
 *
 * Examples:
 * Input:  "42"                    Output: 42
 * Input:  "   -42"                Output: -42
 * Input:  "4193 with words"       Output: 4193
 * Input:  "words and 987"         Output: 0
 * Input:  "-91283472332"          Output: -2147483648
 *
 * --------------------------------------------------
 * APPROACH 1: Using Built-in Parsing (Conceptual)
 * --------------------------------------------------
 *
 * Steps:
 * 1. Skip leading spaces.
 * 2. Extract the sign.
 * 3. Extract consecutive digits.
 * 4. Convert using parsing with exception handling.
 *
 * Limitation:
 * Integer.parseInt() throws NumberFormatException
 * for values outside the int range.
 *
 * This approach is useful for understanding the
 * problem, but manual parsing is preferred.
 *
 * --------------------------------------------------
 * APPROACH 2: Manual Parsing with Positive Accumulator
 * --------------------------------------------------
 *
 * Idea:
 * Build the number using:
 * result = result * 10 + digit
 *
 * Before multiplication, check overflow.
 *
 * Important:
 * A positive accumulator cannot represent the
 * magnitude 2147483648 in an int. Therefore, this
 * approach needs special handling for Integer.MIN_VALUE.
 *
 * --------------------------------------------------
 * APPROACH 3: Negative Accumulator (Optimal)
 * --------------------------------------------------
 *
 * Idea:
 * Build the number as a negative value.
 *
 * Why?
 * Integer.MIN_VALUE = -2147483648
 * Integer.MAX_VALUE =  2147483647
 *
 * The negative range has one extra value.
 * Negative accumulation can represent both limits.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 */

class Solution {

    // APPROACH 3: Optimal negative accumulation
    public int myAtoi(String s) {

        int n = s.length();
        int i = 0;

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

        // Step 3: Set the negative boundary
        int limit = (sign == -1)
                ? Integer.MIN_VALUE
                : -Integer.MAX_VALUE;

        int multMin = limit / 10;

        // Step 4: Build the number negatively
        int result = 0;

        while (i < n &&
               s.charAt(i) >= '0' &&
               s.charAt(i) <= '9') {

            int digit = s.charAt(i) - '0';

            // Check overflow before multiplying by 10
            if (result < multMin) {
                return sign == -1
                        ? Integer.MIN_VALUE
                        : Integer.MAX_VALUE;
            }

            result *= 10;

            // Check overflow before subtracting digit
            if (result < limit + digit) {
                return sign == -1
                        ? Integer.MIN_VALUE
                        : Integer.MAX_VALUE;
            }

            result -= digit;
            i++;
        }

        // Step 5: Apply the sign
        return sign * result;
    }
}