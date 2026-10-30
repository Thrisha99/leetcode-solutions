```java
/*
 * LeetCode 1832: Check if the Sentence Is Pangram
 * Difficulty: Easy
 * Topic: Strings, Arrays, HashSet
 *
 * Problem:
 * A pangram contains every letter of the English alphabet
 * at least once.
 *
 * Given a string containing lowercase English letters,
 * return true if it is a pangram; otherwise, return false.
 *
 * Example 1:
 * Input:  "thequickbrownfoxjumpsoverthelazydog"
 * Output: true
 *
 * Example 2:
 * Input:  "leetcode"
 * Output: false
 *
 * Approaches:
 * 1. Boolean array
 * 2. HashSet using enhanced for loop
 * 3. HashSet using indexed for loop
 * 4. String.contains()
 * 5. Nested loops
 * 6. HashSet initialized with all alphabet letters
 *
 * Complexity:
 * Boolean array: O(n) time, O(1) auxiliary space
 * HashSet:       O(n) expected time, O(1) auxiliary space
 * String.contains(): O(n) time for fixed alphabet size,
 *                     but with repeated scans
 * Nested loops:  O(n) time for fixed alphabet size,
 *                O(1) auxiliary space
 *
 * Note:
 * Submit only one Solution class at a time on LeetCode.
 */

import java.util.HashSet;
import java.util.Set;

class Solution {

    // Approach 1: Boolean Array
    public boolean checkIfPangram(String sentence) {
        boolean[] seen = new boolean[26];
        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            int idx = c - 'a';

            if (!seen[idx]) {
                seen[idx] = true;
                count++;

                if (count == 26) {
                    return true;
                }
            }
        }

        return false;
    }
}

/*
 * Approach 2: HashSet using enhanced for loop
 *
 * class Solution {
 *     public boolean checkIfPangram(String sentence) {
 *         Set<Character> set = new HashSet<>();
 *
 *         for (char c : sentence.toCharArray()) {
 *             set.add(c);
 *         }
 *
 *         return set.size() == 26;
 *     }
 * }
 */

/*
 * Approach 3: HashSet using indexed for loop
 *
 * class Solution {
 *     public boolean checkIfPangram(String sentence) {
 *         Set<Character> set = new HashSet<>();
 *
 *         for (int i = 0; i < sentence.length(); i++) {
 *             set.add(sentence.charAt(i));
 *         }
 *
 *         return set.size() == 26;
 *     }
 * }
 */

/*
 * Approach 4: String.contains()
 *
 * class Solution {
 *     public boolean checkIfPangram(String sentence) {
 *         if (sentence.length() < 26) {
 *             return false;
 *         }
 *
 *         for (char c = 'a'; c <= 'z'; c++) {
 *             if (!sentence.contains(String.valueOf(c))) {
 *                 return false;
 *             }
 *         }
 *
 *         return true;
 *     }
 * }
 */

/*
 * Approach 5: Nested loops
 *
 * class Solution {
 *     public boolean checkIfPangram(String s) {
 *         int n = s.length();
 *
 *         for (char ch = 'a'; ch <= 'z'; ch++) {
 *             boolean found = false;
 *
 *             for (int i = 0; i < n; i++) {
 *                 if (ch == s.charAt(i)) {
 *                     found = true;
 *                     break;
 *                 }
 *             }
 *
 *             if (!found) {
 *                 return false;
 *             }
 *         }
 *
 *         return true;
 *     }
 * }
 */

/*
 * Approach 6: HashSet initialized with alphabet
 *
 * class Solution {
 *     public boolean checkIfPangram(String sentence) {
 *         HashSet<Character> alpha = new HashSet<>();
 *
 *         for (char c = 'a'; c <= 'z'; c++) {
 *             alpha.add(c);
 *         }
 *
 *         for (int i = 0; i < sentence.length(); i++) {
 *             alpha.remove(sentence.charAt(i));
 *
 *             if (alpha.isEmpty()) {
 *                 return true;
 *             }
 *         }
 *
 *         return false;
 *     }
 * }
 */
```