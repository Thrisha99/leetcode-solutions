
import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode 3: Longest Substring Without Repeating Characters
 *
 * Problem:
 * Given a string s, find the length of the longest substring
 * without repeating characters.
 *
 * Input:
 * String s = "abcabcbb"
 *
 * Output:
 * 3
 *
 * Explanation:
 * The longest substrings without repeating characters are
 * "abc", "bca", and "cab".
 *
 * Approach:
 * Variable Size Sliding Window + HashSet
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, character set size))
 */

class Solution {
    
    public int lengthOfLongestSubstring(String s) {

        // Stores unique characters in the current window
        Set<Character> set = new HashSet<>();

        // Left pointer of the sliding window
        int l = 0;

        // Stores the maximum valid window length
        int maxWind = 0;

        // Right pointer expands the window
        for (int r = 0; r < s.length(); r++) {

            // Shrink the window while the current character is duplicate
            while (set.contains(s.charAt(r))) {

                // Remove the leftmost character
                set.remove(s.charAt(l));

                // Move the left pointer forward
                l++;
            }

            // Add the current character to the window
            set.add(s.charAt(r));

            // Calculate the current window length
            int windowLength = r - l + 1;

            // Update the maximum window length
            maxWind = Math.max(maxWind, windowLength);
        }

        return maxWind;
    }
}

/*
 * Test Cases:
 *
 * Input: "abcabcbb"  Output: 3
 * Input: "bbbbb"     Output: 1
 * Input: "pwwkew"    Output: 3
 * Input: "abba"      Output: 2
 * Input: ""          Output: 0
 * Input: "abcdef"    Output: 6
 */
