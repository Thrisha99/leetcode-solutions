
/*
 * ============================================================
 * LeetCode 242: Valid Anagram
 * ============================================================
 *
 * Problem Link:
 * https://leetcode.com/problems/valid-anagram/
 *
 * Difficulty: Easy
 * Topic: Strings
 * Pattern: Hashing
 * Sub-pattern: Frequency Counting
 * Language: Java
 *
 * Problem:
 * Given two strings s and t, return true if t is an
 * anagram of s, and false otherwise.
 *
 * An anagram contains exactly the same characters
 * with the same frequencies, in any order.
 *
 * Example 1:
 * Input:  s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input:  s = "rat", t = "car"
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 10^4
 * - s and t consist of lowercase English letters.
 *
 * ============================================================
 * APPROACH 1: BRUTE FORCE
 * ============================================================
 *
 * Technique:
 * Sorting
 *
 * Idea:
 * 1. Check whether both strings have equal lengths.
 * 2. Convert both strings into character arrays.
 * 3. Sort both arrays.
 * 4. Compare the sorted arrays.
 *
 * Time Complexity: O(n log n)
 * Auxiliary Space: O(n)
 *
 * ============================================================
 */

class SolutionBruteForce {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);

        return java.util.Arrays.equals(arr1, arr2);
    }
}


/*
 * ============================================================
 * APPROACH 2: BETTER APPROACH
 * ============================================================
 *
 * Technique:
 * HashMap + Frequency Counting
 *
 * Idea:
 * 1. Check whether both strings have equal lengths.
 * 2. Store the frequency of each character in a HashMap.
 * 3. Traverse the second string.
 * 4. If a character has frequency zero, return false.
 * 5. Otherwise, decrease its frequency.
 * 6. If all characters are processed, return true.
 *
 * Time Complexity: O(n) average
 * Auxiliary Space: O(k), where k is the number of
 * distinct characters.
 *
 * ============================================================
 */

class SolutionHashMap {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        java.util.HashMap<Character, Integer> map =
            new java.util.HashMap<>();

        // Count characters in the first string
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Decrease frequencies using the second string
        for (int i = 0; i < t.length(); i++) {

            char ch = t.charAt(i);

            if (map.getOrDefault(ch, 0) == 0) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}


/*
 * ============================================================
 * APPROACH 3: OPTIMAL APPROACH
 * ============================================================
 *
 * Technique:
 * Frequency Array + Hashing
 *
 * Idea:
 * 1. Check whether both strings have equal lengths.
 * 2. Create an integer array of size 26.
 * 3. Increase the frequency for every character in s.
 * 4. Decrease the frequency for every character in t.
 * 5. If any frequency becomes negative, return false.
 * 6. If all characters are processed, return true.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 *
 * Assumption:
 * Strings contain only lowercase English letters.
 *
 * ============================================================
 */

class SolutionOptimal {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];

        // Increase frequency for characters in s
        for (int i = 0; i < s.length(); i++) {

            arr[s.charAt(i) - 'a']++;
        }

        // Decrease frequency for characters in t
        for (int i = 0; i < t.length(); i++) {

            int index = t.charAt(i) - 'a';

            arr[index]--;

            if (arr[index] < 0) {
                return false;
            }
        }

        return true;
    }
}


/*
 * ============================================================
 * APPROACH COMPARISON
 * ============================================================
 *
 * Approach 1: Sorting
 * Time: O(n log n)
 * Auxiliary Space: O(n)
 *
 * Approach 2: HashMap
 * Time: O(n) average
 * Auxiliary Space: O(k)
 *
 * Approach 3: Frequency Array
 * Time: O(n)
 * Auxiliary Space: O(1)
 *
 * BEST APPROACH:
 * Frequency Array
 *
 * Reason:
 * The problem guarantees lowercase English letters.
 * Therefore, a fixed-size array of 26 elements is sufficient.
 *
 * ============================================================
 */
