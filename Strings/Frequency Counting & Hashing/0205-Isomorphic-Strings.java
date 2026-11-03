
import java.util.HashMap;
import java.util.Arrays;

/*
 * LeetCode 205 - Isomorphic Strings
 *
 * Link:
 * https://leetcode.com/problems/isomorphic-strings/
 *
 * Difficulty: Easy
 * Topic: Strings, Hashing
 * Pattern: Two-way Character Mapping
 *
 * Problem:
 * Determine whether two strings are isomorphic.
 *
 * Two strings are isomorphic if characters in the first
 * string can be replaced to get the second string.
 *
 * Every character must map consistently to exactly one
 * character, and two different characters cannot map
 * to the same character.
 *
 * Approaches:
 * 1. Brute Force - O(n^2) time, O(1) space
 * 2. Better - Two HashMaps - O(n) average time, O(k) space
 * 3. Optimal - Two Mapping Arrays - O(n) time, O(1) space
 *
 * n = length of the strings
 * k = number of distinct characters
 */


// ======================================================
// APPROACH 1: BRUTE FORCE - PREVIOUS CHARACTER CHECKING
// ======================================================

class BruteForce {

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < i; j++) {

                // Same source character must map to same target.
                if (s.charAt(i) == s.charAt(j) &&
                    t.charAt(i) != t.charAt(j)) {

                    return false;
                }

                // Different source characters cannot map
                // to the same target character.
                if (t.charAt(i) == t.charAt(j) &&
                    s.charAt(i) != s.charAt(j)) {

                    return false;
                }
            }
        }

        return true;
    }
}

/*
 * Explanation:
 *
 * For every position i, compare it with all previous
 * positions j.
 *
 * Check two conditions:
 *
 * 1. If s[i] == s[j], then t[i] must equal t[j].
 * 2. If t[i] == t[j], then s[i] must equal s[j].
 *
 * These conditions ensure a consistent one-to-one mapping.
 *
 * Time Complexity: O(n^2)
 * Auxiliary Space: O(1)
 */


// ======================================================
// APPROACH 2: BETTER - TWO HASHMAPS
// ======================================================

class BetterHashMap {

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Check source-to-target mapping.
            if (map1.containsKey(c1) && map1.get(c1) != c2) {
                return false;
            }

            // Check target-to-source mapping.
            if (map2.containsKey(c2) && map2.get(c2) != c1) {
                return false;
            }

            // Store both directions.
            map1.put(c1, c2);
            map2.put(c2, c1);
        }

        return true;
    }
}

/*
 * Explanation:
 *
 * map1 stores:
 * Source character -> Target character
 *
 * map2 stores:
 * Target character -> Source character
 *
 * Both mappings are necessary to guarantee a one-to-one
 * relationship.
 *
 * Time Complexity: O(n) average
 * Auxiliary Space: O(k), where k is the number of
 * distinct characters stored across the mappings.
 */


// ======================================================
// APPROACH 3: OPTIMAL - TWO MAPPING ARRAYS
// ======================================================

class Optimal {

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        // -1 means that a character has not been mapped.
        int[] arr1 = new int[128];
        int[] arr2 = new int[128];

        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Check source-to-target mapping.
            if (arr1[c1] != -1 && arr1[c1] != c2) {
                return false;
            }

            // Check target-to-source mapping.
            if (arr2[c2] != -1 && arr2[c2] != c1) {
                return false;
            }

            // Store both mappings.
            arr1[c1] = c2;
            arr2[c2] = c1;
        }

        return true;
    }
}

/*
 * Explanation:
 *
 * arr1[c1] stores the character mapped from c1 in t.
 * arr2[c2] stores the character mapped from c2 in s.
 *
 * Both arrays have 128 positions because the problem
 * uses ASCII characters.
 *
 * -1 represents an unmapped character.
 *
 * Time Complexity: O(n + 128) = O(n)
 * Auxiliary Space: O(128) = O(1)
 */


// ======================================================
// COMPLEXITY COMPARISON
// ======================================================

/*
 * Approach          Time          Auxiliary Space
 *
 * Brute Force       O(n^2)         O(1)
 * Two HashMaps      O(n) average   O(k)
 * Two Arrays        O(n)           O(1)
 *
 * OPTIMAL:
 * Two fixed-size mapping arrays for ASCII characters.
 */
