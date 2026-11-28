
/*
 * LeetCode 76 - Minimum Window Substring
 * Difficulty: Hard
 * Pattern: Sliding Window + HashMap
 *
 * Problem:
 * Given two strings s and t, return the minimum window
 * substring of s such that every character in t (including
 * duplicates) is included in the window.
 *
 * If no such window exists, return an empty string.
 *
 * Example 1:
 * Input:  s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 2:
 * Input:  s = "a", t = "a"
 * Output: "a"
 *
 * Example 3:
 * Input:  s = "a", t = "aa"
 * Output: ""
 *
 * Approach:
 * Optimized Sliding Window + HashMap
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(k)
 * n = length of s
 * m = length of t
 * k = number of distinct characters stored in the map
 */

import java.util.HashMap;
import java.util.Map;

class Solution {

    public String minWindow(String s, String t) {

        // If s is shorter than t, a valid window is impossible
        if (s.length() < t.length()) {
            return "";
        }

        // Store the required character frequencies from t
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Initialize sliding window variables
        int left = 0;
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int required = t.length();

        // Expand the window using right pointer
        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Check whether this character is still required
            if (map.getOrDefault(ch, 0) > 0) {
                required--;
            }

            // Decrease frequency because character is added
            // to the current window
            map.put(ch, map.getOrDefault(ch, 0) - 1);

            // All required characters are present
            while (required == 0) {

                // Update the smallest valid window
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                // Remove the leftmost character
                char leftChar = s.charAt(left);

                map.put(leftChar, map.get(leftChar) + 1);

                // Check whether removing this character
                // makes the current window invalid
                if (map.get(leftChar) > 0) {
                    required++;
                }

                // Move the left pointer forward
                left++;
            }
        }

        // No valid window was found
        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        // Return the smallest valid substring
        return s.substring(start, start + minLength);
    }
}
