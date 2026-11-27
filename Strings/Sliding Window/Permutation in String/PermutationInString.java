
import java.util.Arrays;

/*
 * LeetCode 567 - Permutation in String
 *
 * Problem Link:
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Difficulty: Medium
 * Topic: Strings
 * Pattern: Fixed-Size Sliding Window + Frequency Counting
 *
 * INPUT:
 * s1 = "ab"
 * s2 = "eidbaooo"
 *
 * OUTPUT:
 * true
 *
 * EXPLANATION:
 * The substring "ba" in s2 is a permutation of s1 ("ab").
 *
 * n = length of s1
 * m = length of s2
 */

class Solution {

    /*
     * APPROACH 1: BRUTE FORCE
     *
     * Steps:
     * 1. Sort s1.
     * 2. Generate every substring of s2 having length n.
     * 3. Convert each substring into a character array.
     * 4. Sort the current window.
     * 5. Compare both sorted arrays.
     *
     * Time Complexity:
     * O((m - n + 1) * n log n)
     *
     * Auxiliary Space Complexity: O(n)
     */

    public boolean checkInclusionBruteForce(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        char[] target = s1.toCharArray();
        Arrays.sort(target);

        for (int i = 0; i <= m - n; i++) {

            String window = s2.substring(i, i + n);

            char[] current = window.toCharArray();
            Arrays.sort(current);

            if (Arrays.equals(target, current)) {
                return true;
            }
        }

        return false;
    }


    /*
     * APPROACH 2: SLIDING WINDOW - INITIAL WINDOW METHOD
     *
     * Steps:
     * 1. Create two frequency arrays of size 26.
     * 2. Count characters of s1.
     * 3. Count the first n characters of s2.
     * 4. Compare both frequency arrays.
     * 5. Remove the outgoing character.
     * 6. Add the incoming character.
     * 7. Compare both arrays after every movement.
     *
     * Time Complexity: O(26 * (m - n + 1)) = O(m)
     *
     * Auxiliary Space Complexity: O(26) = O(1)
     */

    public boolean checkInclusion(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        int[] freq = new int[26];
        int[] windowFreq = new int[26];

        // Count characters in s1.
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
        }

        // Count characters in the first window of s2.
        for (int i = 0; i < n; i++) {
            windowFreq[s2.charAt(i) - 'a']++;
        }

        // Check the first window.
        if (Arrays.equals(freq, windowFreq)) {
            return true;
        }

        // Slide the fixed-size window.
        for (int right = n; right < m; right++) {

            // Character leaving the window.
            int left = right - n;

            windowFreq[s2.charAt(left) - 'a']--;

            // Character entering the window.
            windowFreq[s2.charAt(right) - 'a']++;

            // Compare all character frequencies.
            if (Arrays.equals(freq, windowFreq)) {
                return true;
            }
        }

        return false;
    }


    /*
     * APPROACH 3: SLIDING WINDOW - TWO POINTER METHOD
     *
     * Steps:
     * 1. Count characters of s1.
     * 2. Start left and right pointers at index 0.
     * 3. Add the right character to windowFreq.
     * 4. If window size exceeds n, remove the left character.
     * 5. Move left forward.
     * 6. When window size becomes n, compare both arrays.
     *
     * Time Complexity: O(26 * m) = O(m)
     *
     * Auxiliary Space Complexity: O(26) = O(1)
     */

    public boolean checkInclusionTwoPointers(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        int[] freq = new int[26];
        int[] freq1 = new int[26];

        // Count characters in s1.
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
        }

        int l = 0;
        int r = 0;

        while (r < m) {

            // Add the new character to the window.
            freq1[s2.charAt(r) - 'a']++;

            // Keep the window size equal to n.
            if (r - l + 1 > n) {
                freq1[s2.charAt(l) - 'a']--;
                l++;
            }

            // Compare both frequency arrays.
            if (r - l + 1 == n) {
                if (Arrays.equals(freq, freq1)) {
                    return true;
                }
            }

            r++;
        }

        return false;
    }
}
