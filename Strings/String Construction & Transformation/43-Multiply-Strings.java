/* 
 * LeetCode 43: Multiply Strings
 *
 * Problem:
 * Given two non-negative integers represented as strings,
 * return their product as a string.
 *
 * You must not convert the input strings into integers
 * or use BigInteger.
 *
 * Input:
 * num1 = "123"
 * num2 = "456"
 *
 * Output:
 * "56088"
 *
 * Another Example:
 * Input:
 * num1 = "2"
 * num2 = "3"
 *
 * Output:
 * "6"
 *
 * Approach:
 * Optimal - Digit-by-Digit Multiplication using an Array
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m + n)
 */

class Solution {

    public String multiply(String num1, String num2) {

        int m = num1.length();
        int n = num2.length();

        // If either number is zero, product is zero.
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // Maximum possible product length is m + n.
        int[] arr = new int[m + n];

        // Step 1: Multiply each digit pair.
        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                // Convert characters into numeric digits.
                int p = (num1.charAt(i) - '0')
                      * (num2.charAt(j) - '0');

                // Include the existing value at the current position.
                int total = p + arr[i + j + 1];

                // Extract digit and carry.
                int digit = total % 10;
                int carry = total / 10;

                // Update the result array.
                arr[i + j + 1] = digit;
                arr[i + j] += carry;
            }
        }

        // Step 2: Skip leading zeros.
        int i = 0;

        while (i < arr.length && arr[i] == 0) {
            i++;
        }

        // Handle an all-zero result.
        if (i == arr.length) {
            return "0";
        }

        // Step 3: Convert array digits into a String.
        StringBuilder sb = new StringBuilder();

        while (i < m + n) {
            sb.append(arr[i]);
            i++;
        }

        return sb.toString();
    }
}


/*
 * DRY RUN
 *
 * Input:
 * num1 = "123"
 * num2 = "456"
 *
 * Result array size = 3 + 3 = 6
 *
 * Initial array:
 * [0, 0, 0, 0, 0, 0]
 *
 * Multiplication proceeds from right to left.
 *
 * 1. 3 * 6 = 18
 *    Digit = 8, Carry = 1
 *    [0, 0, 0, 0, 1, 8]
 *
 * 2. 3 * 5 = 15
 *    Total = 15 + 1 = 16
 *    Digit = 6, Carry = 1
 *    [0, 0, 0, 1, 6, 8]
 *
 * 3. 3 * 4 = 12
 *    Total = 12 + 1 = 13
 *    Digit = 3, Carry = 1
 *    [0, 0, 1, 3, 6, 8]
 *
 * 4. 2 * 6 = 12
 *    Total = 12 + 6 = 18
 *    Digit = 8, Carry = 1
 *    [0, 0, 2, 4, 8, 8]
 *
 * 5. 2 * 5 = 10
 *    Total = 10 + 4 = 14
 *    Digit = 4, Carry = 1
 *    [0, 1, 3, 4, 8, 8]
 *
 * 6. 2 * 4 = 8
 *    Total = 8 + 3 = 11
 *    Digit = 1, Carry = 1
 *    [1, 1, 1, 4, 8, 8]
 *
 * 7. 1 * 6 = 6
 *    Total = 6 + 8 = 14
 *    Digit = 4, Carry = 1
 *    [1, 1, 2, 4, 8, 8]
 *
 * 8. 1 * 5 = 5
 *    Total = 5 + 4 = 9
 *    Digit = 9, Carry = 0
 *    [1, 1, 2, 9, 8, 8]
 *
 * 9. 1 * 4 = 4
 *    Total = 4 + 2 = 6
 *    Digit = 6, Carry = 0
 *    [1, 1, 6, 9, 8, 8]
 *
 * Final array:
 * [1, 1, 6, 9, 8, 8]
 *
 * Final output:
 * "5688"
 *
 * NOTE:
 * The above illustrative array transitions are not the
 * correct result for 123 * 456. The correct final product
 * is 56088. Refer to the Markdown file for the verified
 * position-by-position dry run.
 */
