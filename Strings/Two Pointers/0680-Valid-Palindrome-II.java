
/*
 * ============================================================
 * LeetCode 680: Valid Palindrome II
 * ============================================================
 *
 * Problem Link:
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Difficulty: Easy
 * Topic: Strings
 * Pattern: Two Pointers
 * Sub-pattern: Greedy + Palindrome Checking
 *
 * Problem:
 * Given a string s, return true if the string can become
 * a palindrome after deleting at most one character.
 *
 * Example 1:
 * Input:  "aba"
 * Output: true
 *
 * Example 2:
 * Input:  "abca"
 * Output: true
 * Explanation: Delete 'c' to obtain "aba".
 *
 * Example 3:
 * Input:  "abc"
 * Output: false
 *
 * ============================================================
 * APPROACH 1: BRUTE FORCE
 * ============================================================
 *
 * Technique:
 * Try deleting each character individually.
 *
 * Idea:
 * 1. First check whether the original string is a palindrome.
 * 2. If not, try skipping each character one at a time.
 * 3. Check whether the remaining characters form a palindrome.
 * 4. If any deletion works, return true.
 *
 * Time Complexity: O(n^2)
 * Auxiliary Space: O(1)
 *
 * ============================================================
 */

class SolutionBruteForce {

    public boolean validPalindrome(String s) {

        if (isPalindrome(s, -1)) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {

            if (isPalindrome(s, i)) {
                return true;
            }
        }

        return false;
    }

    // Skip the character at the given index.
    // Use -1 when no character should be skipped.
    private boolean isPalindrome(String s, int skip) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            if (l == skip) {
                l++;
                continue;
            }

            if (r == skip) {
                r--;
                continue;
            }

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}


/*
 * ============================================================
 * APPROACH 2: OPTIMAL TWO POINTERS
 * ============================================================
 *
 * Technique:
 * Two Pointers + Greedy
 *
 * Idea:
 * 1. Initialize left and right pointers.
 * 2. Compare characters from both ends.
 * 3. If they match, move both pointers inward.
 * 4. If they don't match, try two possibilities:
 *      a. Skip the left character.
 *      b. Skip the right character.
 * 5. Check whether either remaining substring is a palindrome.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 *
 * ============================================================
 */

class SolutionOptimal {

    public boolean validPalindrome(String s) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {

                return isPalindrome(s, l + 1, r) ||
                       isPalindrome(s, l, r - 1);
            }

            l++;
            r--;
        }

        return true;
    }

    // Checks whether the selected substring is a palindrome.
    private boolean isPalindrome(String s, int l, int r) {

        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}


/*
 * ============================================================
 * APPROACH COMPARISON
 * ============================================================
 *
 * Approach 1: Brute Force
 * Time Complexity: O(n^2)
 * Auxiliary Space: O(1)
 *
 * Approach 2: Two Pointers + Greedy
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 *
 * BEST APPROACH:
 * Two Pointers + Greedy
 *
 * Reason:
 * We only need to handle the first mismatch.
 * We check the two possible deletion choices instead
 * of trying every possible character deletion.
 *
 * ============================================================
 */
