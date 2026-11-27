# LeetCode 424 – Longest Repeating Character Replacement

/*
 * Problem Number: 424
 * Problem Name: Longest Repeating Character Replacement
 * Difficulty: Medium
 * Topic: Strings
 * Pattern: Variable Size Sliding Window
 * Language: Java
 *
 * INPUT:
 * s = "AABABBA"
 * k = 1
 *
 * OUTPUT:
 * 4
 *
 * EXPLANATION:
 * The substring "AABA" can be converted into "AAAA"
 * by replacing one character.
 *
 * BASIC INFORMATION:
 * - We are given an uppercase English string.
 * - We can replace at most k characters.
 * - We need the longest substring containing the same character.
 * - A substring must be continuous.
 *
 * APPROACH:
 * Optimized Variable Size Sliding Window
 *
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1)
 *
 * KEY FORMULA:
 * Required Replacements = Window Length - Maximum Character Frequency
 *
 * VALID WINDOW:
 * windowLength - maxFrequency <= k
 */

class Solution {

    public int characterReplacement(String s, int k) {

        // Starting index of the current window
        int left = 0;

        // Stores the frequency of uppercase characters A-Z
        int[] frequency = new int[26];

        // Highest frequency observed during window expansion
        int maxFrequency = 0;

        // Current window length
        int windowLength = 0;

        // Longest window length found
        int maxLength = 0;

        // Expand the window using the right pointer
        for (int right = 0; right < s.length(); right++) {

            // Add the current character to the frequency array
            frequency[s.charAt(right) - 'A']++;

            // Update the highest frequency observed
            maxFrequency = Math.max(
                maxFrequency,
                frequency[s.charAt(right) - 'A']
            );

            // Calculate current window length
            windowLength = right - left + 1;

            // Shrink the window if replacements exceed k
            while (windowLength - maxFrequency > k) {

                // Remove the leftmost character from frequency
                frequency[s.charAt(left) - 'A']--;

                // Move the left pointer forward
                left++;

                // Recalculate the window length
                windowLength = right - left + 1;
            }

            // Update the maximum window length
            maxLength = Math.max(maxLength, windowLength);
        }

        // Return the longest valid window length
        return maxLength;
    }
}

/*
 * DRY RUN:
 *
 * Input: s = "AABABBA", k = 1
 *
 * right = 0 -> Window "A"     -> Length 1
 * right = 1 -> Window "AA"    -> Length 2
 * right = 2 -> Window "AAB"   -> Length 3
 * right = 3 -> Window "AABA"  -> Length 4
 * right = 4 -> Shrink "AABAB" -> "ABAB"
 * right = 5 -> Shrink "ABABB" -> "BABB"
 * right = 6 -> Shrink "BABBA" -> "ABBA"
 *
 * Final Answer = 4
 *
 * IMPORTANT:
 * Do not decrease maxFrequency during shrinking.
 * In this optimized approach, maxFrequency stores the
 * highest frequency observed during expansion.
 */