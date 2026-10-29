
/*
 * LeetCode 2000: Reverse Prefix of Word
 * Link: https://leetcode.com/problems/reverse-prefix-of-word/
 *
 * Topic: Strings
 * Pattern: Two Pointers / String Manipulation
 *
 * Approaches:
 * 1. Character Array + Two Pointers (Your Approach)
 * 2. StringBuilder + Two Pointers
 * 3. StringBuilder.reverse() + substring()
 */

class Solution {

    /*
     * APPROACH 1: Character Array + Two Pointers
     *
     * Idea:
     * - Find the first occurrence of ch.
     * - Convert the string into a character array.
     * - Swap characters from both ends of the prefix.
     * - Return the modified array as a String.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     *
     * Recommended: This is your original approach.
     */

    public String reversePrefix(String word, char ch) {
        int k = word.indexOf(ch);

        if (k == -1) {
            return word;
        }

        char[] arr = word.toCharArray();
        int i = 0;

        while (i < k) {
            char temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;

            i++;
            k--;
        }

        return new String(arr);
    }


    /*
     * APPROACH 2: StringBuilder + Two Pointers
     *
     * Idea:
     * - Create a mutable StringBuilder from word.
     * - Swap characters using charAt() and setCharAt().
     * - Reverse only the prefix ending at the first ch.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     *
     * Advantage:
     * No explicit char[] array is created by our code.
     */

    public String reversePrefixUsingStringBuilder(
            String word, char ch) {

        int k = word.indexOf(ch);

        if (k == -1) {
            return word;
        }

        StringBuilder sb = new StringBuilder(word);
        int i = 0;

        while (i < k) {
            char temp = sb.charAt(i);

            sb.setCharAt(i, sb.charAt(k));
            sb.setCharAt(k, temp);

            i++;
            k--;
        }

        return sb.toString();
    }


    /*
     * APPROACH 3: StringBuilder.reverse() + substring()
     *
     * Idea:
     * - Extract the prefix ending at the first ch.
     * - Reverse that prefix using StringBuilder.reverse().
     * - Append the unchanged suffix.
     *
     * Time: O(n)
     * Auxiliary Space: O(n)
     *
     * Advantage:
     * Simple and easy to understand.
     */

    public String reversePrefixUsingReverse(
            String word, char ch) {

        int k = word.indexOf(ch);

        if (k == -1) {
            return word;
        }

        String prefix = word.substring(0, k + 1);
        String reversed = new StringBuilder(prefix)
                .reverse()
                .toString();

        return reversed + word.substring(k + 1);
    }
}
