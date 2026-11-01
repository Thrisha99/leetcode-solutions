
import java.util.HashMap;

/*
 * LeetCode 387 - First Unique Character in a String
 *
 * Link:
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * Difficulty: Easy
 * Topic: Strings, Hashing
 * Pattern: Frequency Counting
 *
 * Problem:
 * Find the index of the first non-repeating character
 * in a string. Return -1 if no unique character exists.
 *
 * Approaches:
 * 1. Brute Force - O(n^2) time, O(1) auxiliary space
 * 2. Better - HashMap - O(n) average time, O(k) space
 * 3. Optimal - Frequency Array - O(n) time, O(1) space
 *
 * n = length of string
 * k = number of distinct characters
 */

// ======================================================
// APPROACH 1: BRUTE FORCE
// ======================================================

class BruteForce {

    public int firstUniqChar(String s) {

        for (int i = 0; i < s.length(); i++) {

            int count = 0;

            for (int j = 0; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    count++;
                }
            }

            if (count == 1) {
                return i;
            }
        }

        return -1;
    }
}

/*
 * Explanation:
 * 1. Traverse each character in the string.
 * 2. Count how many times that character occurs.
 * 3. If its count is exactly 1, return its index.
 * 4. If no unique character is found, return -1.
 *
 * Time Complexity: O(n^2)
 * Auxiliary Space: O(1)
 */


// ======================================================
// APPROACH 2: BETTER - HASHMAP FREQUENCY COUNTING
// ======================================================

class BetterHashMap {

    public int firstUniqChar(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count the frequency of each character.
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Traverse the original string.
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Find the first character with frequency 1.
            if (map.get(ch) == 1) {
                return i;
            }
        }

        return -1;
    }
}

/*
 * Explanation:
 * 1. HashMap stores each character and its frequency.
 * 2. First traversal calculates frequencies.
 * 3. Second traversal checks characters in original order.
 * 4. Return the index of the first character with frequency 1.
 *
 * Time Complexity: O(n) average
 * Auxiliary Space: O(k)
 */


// ======================================================
// APPROACH 3: OPTIMAL - FREQUENCY ARRAY
// ======================================================

class Optimal {

    public int firstUniqChar(String s) {

        // Step 1: Create frequency array.
        int[] arr = new int[26];

        // Step 2: Count character frequencies.
        for (int i = 0; i < s.length(); i++) {

            arr[s.charAt(i) - 'a']++;
        }

        // Step 3: Traverse original string.
        for (int i = 0; i < s.length(); i++) {

            int index = s.charAt(i) - 'a';

            // Step 4: Find the first unique character.
            if (arr[index] == 1) {
                return i;
            }
        }

        // No unique character exists.
        return -1;
    }
}

/*
 * Explanation:
 *
 * - Array size is 26 because the problem contains
 *   lowercase English letters.
 *
 * - arr[0] represents 'a'.
 * - arr[1] represents 'b'.
 * - arr[25] represents 'z'.
 *
 * First traversal:
 * Count the frequency of every character.
 *
 * Second traversal:
 * Check characters in their original order.
 *
 * If frequency == 1:
 * Return the current string index.
 *
 * If no unique character exists:
 * Return -1.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 */


// ======================================================
// COMPLEXITY COMPARISON
// ======================================================

/*
 * Approach          Time          Auxiliary Space
 *
 * Brute Force       O(n^2)         O(1)
 * HashMap           O(n) average   O(k)
 * Frequency Array   O(n)           O(1)
 *
 * OPTIMAL:
 * Fixed frequency array because the problem guarantees
 * lowercase English letters.
 */
