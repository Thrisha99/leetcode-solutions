```java
/*
 * ============================================================
 * LeetCode Problem: 443
 * Title: String Compression
 * Difficulty: Medium
 * Topic: Strings
 * Pattern: Two Pointers + In-place Array Manipulation
 * Language: Java
 * ============================================================
 *
 * PROBLEM STATEMENT:
 *
 * Given an array of characters, compress it using the following
 * rules:
 *
 * 1. If a character appears only once consecutively, keep it.
 * 2. If a character appears multiple times consecutively,
 *    write the character followed by its count.
 * 3. Modify the original array in-place.
 * 4. Return the new length of the compressed array.
 * 5. Use O(1) auxiliary space.
 *
 * INPUT:
 * chars = ['a','a','b','b','c','c','c']
 *
 * OUTPUT:
 * 6
 *
 * MODIFIED ARRAY:
 * ['a','2','b','2','c','3']
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE - STRINGBUILDER
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(n)
 *
 * Idea:
 * 1. Traverse the input array.
 * 2. Count consecutive occurrences.
 * 3. Append the character to StringBuilder.
 * 4. Append the count if count > 1.
 * 5. Copy the compressed result into the original array.
 *
 * ------------------------------------------------------------
 * APPROACH 2: BETTER - IN-PLACE TWO POINTERS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(log n) for temporary count String
 *
 * Idea:
 * 1. Use read pointer i to identify character groups.
 * 2. Use write pointer to modify the original array.
 * 3. Count consecutive occurrences.
 * 4. Write the character.
 * 5. Convert count into String and write each digit.
 * 6. Return write.
 *
 * ------------------------------------------------------------
 * APPROACH 3: OPTIMAL - TWO POINTERS + MATH
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 *
 * Idea:
 * 1. Use read pointer i and write pointer.
 * 2. Count consecutive characters.
 * 3. Write the character.
 * 4. Extract each digit of the count mathematically.
 * 5. Write digits directly into the original array.
 * 6. Return write.
 *
 * ============================================================
 */

class Solution {

    // APPROACH 1: BRUTE FORCE - STRINGBUILDER

    public int compressBruteForce(char[] chars) {

        StringBuilder sb = new StringBuilder();

        int i = 0;
        int n = chars.length;

        while (i < n) {

            char current = chars[i];
            int count = 0;

            while (i < n && chars[i] == current) {
                count++;
                i++;
            }

            sb.append(current);

            if (count > 1) {
                sb.append(count);
            }
        }

        // Copy compressed result into original array
        for (int j = 0; j < sb.length(); j++) {
            chars[j] = sb.charAt(j);
        }

        return sb.length();
    }


    // APPROACH 2: BETTER - IN-PLACE TWO POINTERS

    public int compressBetter(char[] chars) {

        int i = 0;
        int write = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }

            chars[write] = current;
            write++;

            if (count > 1) {

                String num = String.valueOf(count);

                for (int j = 0; j < num.length(); j++) {
                    chars[write] = num.charAt(j);
                    write++;
                }
            }
        }

        return write;
    }


    // APPROACH 3: OPTIMAL - TWO POINTERS + MATHEMATICAL DIGIT EXTRACTION

    public int compress(char[] chars) {

        int i = 0;
        int write = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            // Count consecutive occurrences
            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }

            // Write the character
            chars[write] = current;
            write++;

            // Write count digits only when count > 1
            if (count > 1) {

                // Find the highest power of 10
                int divisor = 1;

                while (count / divisor >= 10) {
                    divisor *= 10;
                }

                // Extract and write each digit
                while (divisor > 0) {

                    int digit = count / divisor;

                    chars[write] = (char) ('0' + digit);
                    write++;

                    count %= divisor;
                    divisor /= 10;
                }
            }
        }

        return write;
    }
}


/*
 * ============================================================
 * EXAMPLE TEST CASES
 * ============================================================
 *
 * Input:
 * ['a','a','b','b','c','c','c']
 *
 * Output:
 * 6
 *
 * Compressed portion:
 * ['a','2','b','2','c','3']
 *
 * ------------------------------------------------------------
 *
 * Input:
 * ['a']
 *
 * Output:
 * 1
 *
 * Compressed portion:
 * ['a']
 *
 * ------------------------------------------------------------
 *
 * Input:
 * ['a','a','a','b','b','b','b']
 *
 * Output:
 * 4
 *
 * Compressed portion:
 * ['a','3','b','4']
 *
 * ------------------------------------------------------------
 *
 * Input:
 * ['a','b','c']
 *
 * Output:
 * 3
 *
 * Compressed portion:
 * ['a','b','c']
 *
 * ------------------------------------------------------------
 *
 * Input:
 * ['a','b','b','b','b','b','b','b','b','b','b','b','b']
 *
 * Output:
 * 4
 *
 * Compressed portion:
 * ['a','b','1','2']
 *
 * ============================================================
 */
```