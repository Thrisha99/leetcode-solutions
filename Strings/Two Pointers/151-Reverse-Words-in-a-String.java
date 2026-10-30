
/*
 * LeetCode 151: Reverse Words in a String
 * Link: https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Topic: Strings
 * Patterns:
 * 1. Two Pointers
 * 2. String Manipulation
 * 3. Read / Write Pointers
 *
 * Approaches:
 * 1. Character Array + Two Pointers (Your Approach)
 * 2. split() + String Concatenation
 * 3. split() + StringBuilder
 * 4. Character Array + Read/Write Pointers (Recommended)
 */

class Solution {

    /*
     * HELPER METHOD
     *
     * Reverses characters between indices i and j.
     *
     * Time: O(n) for the selected range
     * Auxiliary Space: O(1)
     */

    public void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }


    /*
     * APPROACH 1: Character Array + Two Pointers
     *
     * Your original approach.
     *
     * Steps:
     * 1. Convert string into a character array.
     * 2. Reverse the entire array.
     * 3. Reverse each individual word.
     * 4. Remove extra spaces.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     */

    public String reverseWordsTwoPointers(String s) {
        char[] arr = s.toCharArray();

        int i = 0;
        int j = arr.length - 1;

        // Reverse the entire array
        reverse(arr, i, j);

        // Reverse individual words
        int r = 0;

        for (int k = 0; k < arr.length; k++) {
            if (arr[k] == ' ') {
                reverse(arr, r, k - 1);
                r = k + 1;
            }
        }

        // Reverse the last word
        reverse(arr, r, j);

        // Remove extra spaces
        return new String(arr).trim().replaceAll("\\s+", " ");
    }


    /*
     * APPROACH 2: split() + String Concatenation
     *
     * Steps:
     * 1. Split the string into words.
     * 2. Traverse the words backward.
     * 3. Concatenate each word into the result.
     *
     * Time: O(n^2) worst case
     * Auxiliary Space: O(n)
     *
     * Note:
     * Repeated String concatenation creates intermediate strings.
     */

    public String reverseWordsSplitConcat(String s) {
        String[] words = s.trim().split("\\s+");

        String result = "";

        for (int i = words.length - 1; i >= 0; i--) {
            result = result + words[i];

            if (i != 0) {
                result = result + " ";
            }
        }

        return result;
    }


    /*
     * APPROACH 3: split() + StringBuilder
     *
     * Steps:
     * 1. Split the string into words.
     * 2. Traverse the array backward.
     * 3. Append each word to StringBuilder.
     * 4. Return the final string.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     *
     * Improvement:
     * Avoids repeated immutable String concatenation.
     */

    public String reverseWordsStringBuilder(String s) {
        String[] words = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);

            if (i > 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }


    /*
     * APPROACH 4: Character Array + Read/Write Pointers
     *
     * Recommended optimized approach.
     *
     * Steps:
     * 1. Reverse the entire character array.
     * 2. Skip extra spaces using read pointer.
     * 3. Copy valid characters using write pointer.
     * 4. Reverse each copied word.
     * 5. Add exactly one space between words.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     *
     * Advantage:
     * Handles extra spaces during traversal itself.
     * No split(), trim(), or replaceAll() required.
     */

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();

        // Step 1: Reverse the entire array
        reverse(arr, 0, arr.length - 1);

        int read = 0;
        int write = 0;

        // Step 2: Process words
        while (read < arr.length) {

            // Skip extra spaces
            while (read < arr.length && arr[read] == ' ') {
                read++;
            }

            if (read == arr.length) {
                break;
            }

            // Add one space between words
            if (write > 0) {
                arr[write++] = ' ';
            }

            int start = write;

            // Copy current word
            while (read < arr.length && arr[read] != ' ') {
                arr[write] = arr[read];
                write++;
                read++;
            }

            // Reverse the current word
            reverse(arr, start, write - 1);
        }

        // Return only the valid portion
        return new String(arr, 0, write);
    }
}
