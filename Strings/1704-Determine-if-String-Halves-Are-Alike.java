
/*
 * LeetCode 1704: Determine if String Halves Are Alike
 * Link: https://leetcode.com/problems/determine-if-string-halves-are-alike/
 *
 * Topic: Strings
 * Pattern: Character Traversal / Frequency Counting
 *
 * Approaches:
 * 1. Character Arrays
 * 2. Two Counters Without Arrays
 * 3. Single Counter (Optimal)
 */

class Solution {

    /*
     * APPROACH 1: Using Two Character Arrays
     *
     * Idea:
     * - Store the first half in array a.
     * - Store the second half in array b.
     * - Count vowels in each half.
     * - Compare the counts.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(n)
     *
     * Beginner-friendly approach based on the original solution.
     */

    public boolean halvesAreAlikeWithArrays(String s) {
        int mid = s.length() / 2;

        char[] a = new char[mid];
        char[] b = new char[mid];

        int c1 = 0;
        int c2 = 0;

        for (int i = 0; i < mid; i++) {
            a[i] = Character.toLowerCase(s.charAt(i));
            b[i] = Character.toLowerCase(s.charAt(i + mid));

            if (isVowel(a[i])) {
                c1++;
            }

            if (isVowel(b[i])) {
                c2++;
            }
        }

        return c1 == c2;
    }


    /*
     * APPROACH 2: Two Counters Without Arrays
     *
     * Idea:
     * - Access characters directly using charAt().
     * - Count vowels in each half.
     * - Compare the two counts.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(1)
     *
     * Improvement:
     * No character arrays or converted string are created.
     */

    public boolean halvesAreAlikeTwoCounters(String s) {
        int mid = s.length() / 2;

        int c1 = 0;
        int c2 = 0;

        for (int i = 0; i < mid; i++) {
            char a = Character.toLowerCase(s.charAt(i));
            char b = Character.toLowerCase(s.charAt(i + mid));

            if (isVowel(a)) {
                c1++;
            }

            if (isVowel(b)) {
                c2++;
            }
        }

        return c1 == c2;
    }


    /*
     * APPROACH 3: Single Counter (Optimal)
     *
     * Idea:
     * - Increase count for a vowel in the first half.
     * - Decrease count for a vowel in the second half.
     * - If the final count is zero, both halves have
     *   the same number of vowels.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(1)
     *
     * Recommended LeetCode submission.
     */

    public boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;
        int count = 0;

        for (int i = 0; i < mid; i++) {
            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(i + mid));

            if (isVowel(left)) {
                count++;
            }

            if (isVowel(right)) {
                count--;
            }
        }

        return count == 0;
    }


    /*
     * HELPER METHOD
     *
     * Returns true if the character is a vowel.
     * Character.toLowerCase() allows uppercase vowels
     * to be handled without converting the entire string.
     */

    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);

        return ch == 'a'
                || ch == 'e'
                || ch == 'i'
                || ch == 'o'
                || ch == 'u';
    }
}
