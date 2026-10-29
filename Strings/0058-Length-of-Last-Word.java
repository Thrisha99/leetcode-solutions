
/*
 * LeetCode 58: Length of Last Word
 * Link: https://leetcode.com/problems/length-of-last-word/
 *
 * Topic: Strings
 * Pattern: Reverse Traversal
 *
 * Approach 1: Using split() and an array
 * Approach 2: Reverse traversal (Optimal)
 */

class Solution {

    /*
     * APPROACH 1: Using split()
     *
     * Idea:
     * - Split the string into words.
     * - Find the last word.
     * - Return its length.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(n)
     *
     * Note:
     * This approach needs additional handling for leading spaces
     * and is less space-efficient.
     */

    /*
    public int lengthOfLastWordSplit(String s) {
        String[] s1 = s.trim().split("\\s+");
        int j = 0;

        for (int i = 0; i < s1.length; i++) {
            if (i == s1.length - 1) {
                j = s1[i].length();
            }
        }

        return j;
    }
    */


    /*
     * APPROACH 2: Reverse Traversal (Optimal)
     *
     * Idea:
     * - Start from the end of the string.
     * - Skip trailing spaces.
     * - Count characters until a space or the beginning.
     * - Return the count.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(1)
     */

    public int lengthOfLastWord(String s) {

        int i = s.length() - 1;
        int c = 0;

        // Step 1: Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 2: Count characters in the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            c++;
            i--;
        }

        // Step 3: Return the length
        return c;
    }
}
