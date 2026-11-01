
import java.util.HashMap;

/*
 * LeetCode 383 - Ransom Note
 * Link: https://leetcode.com/problems/ransom-note/
 *
 * Difficulty: Easy
 * Topic: Strings, Hashing
 * Pattern: Frequency Counting
 *
 * Problem:
 * Determine whether ransomNote can be constructed using
 * characters from magazine.
 *
 * Each character in magazine can be used only once.
 *
 * Approaches:
 * 1. Brute Force - O(n * m) time, O(m) space
 * 2. Better - HashMap Frequency Counting
 *    O(n + m) average time, O(k) space
 * 3. Optimal - Fixed Frequency Array
 *    O(n + m) time, O(1) auxiliary space
 *
 * n = ransomNote.length()
 * m = magazine.length()
 * k = number of distinct characters
 */

// ======================================================
// APPROACH 1: BRUTE FORCE
// ======================================================

class BruteForce {

    public boolean canConstruct(String ransomNote, String magazine) {

        boolean[] used = new boolean[magazine.length()];

        for (int i = 0; i < ransomNote.length(); i++) {

            char required = ransomNote.charAt(i);
            boolean found = false;

            for (int j = 0; j < magazine.length(); j++) {

                if (magazine.charAt(j) == required && !used[j]) {

                    used[j] = true;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }
}

/*
 * Explanation:
 * - For every character in ransomNote, search magazine.
 * - Find a matching character that has not been used.
 * - Mark that magazine position as used.
 * - If no unused matching character exists, return false.
 *
 * Time Complexity: O(n * m)
 * Auxiliary Space: O(m) for the used array.
 */


// ======================================================
// APPROACH 2: BETTER - HASHMAP FREQUENCY COUNTING
// ======================================================

class BetterHashMap {

    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count available characters in magazine.
        for (int i = 0; i < magazine.length(); i++) {

            char ch = magazine.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Consume characters required by ransomNote.
        for (int i = 0; i < ransomNote.length(); i++) {

            char ch = ransomNote.charAt(i);

            if (map.getOrDefault(ch, 0) == 0) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}

/*
 * Explanation:
 * - HashMap stores each character and its frequency.
 * - Increase frequency while traversing magazine.
 * - Decrease frequency while traversing ransomNote.
 * - If a required character has frequency zero, return false.
 *
 * Time Complexity: O(n + m) average
 * Auxiliary Space: O(k), where k is the number of distinct
 * characters stored in the HashMap.
 */


// ======================================================
// APPROACH 3: OPTIMAL - FREQUENCY ARRAY
// ======================================================

class Optimal {

    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Step 1: Create frequency array.
        int[] arr = new int[26];

        // Step 2: Count characters available in magazine.
        for (int i = 0; i < magazine.length(); i++) {

            arr[magazine.charAt(i) - 'a']++;
        }

        // Step 3: Consume characters required by ransomNote.
        for (int i = 0; i < ransomNote.length(); i++) {

            int index = ransomNote.charAt(i) - 'a';

            arr[index]--;

            // Character is unavailable.
            if (arr[index] < 0) {
                return false;
            }
        }

        return true;
    }
}

/*
 * Explanation:
 *
 * - Array size is 26 because the problem contains lowercase
 *   English letters only.
 * - arr[0] represents 'a'.
 * - arr[1] represents 'b'.
 * - arr[25] represents 'z'.
 *
 * First traversal:
 * Count available characters in magazine.
 *
 * Second traversal:
 * Decrease frequency for each required character.
 *
 * If any frequency becomes negative:
 * The required character is unavailable.
 * Return false.
 *
 * Otherwise:
 * Return true.
 *
 * Time Complexity: O(n + m)
 * Auxiliary Space: O(1), because array size is fixed at 26.
 */


// ======================================================
// COMPLEXITY COMPARISON
// ======================================================

/*
 * Approach          Time             Auxiliary Space
 *
 * Brute Force       O(n * m)          O(m)
 * HashMap           O(n + m) avg      O(k)
 * Frequency Array   O(n + m)          O(1)
 *
 * OPTIMAL:
 * Fixed frequency array because the input is restricted
 * to lowercase English letters.
 */

/*
 * DRY RUN 1:
 *
 * ransomNote = "aa"
 * magazine   = "aab"
 *
 * After counting magazine:
 * a = 2
 * b = 1
 *
 * Process ransomNote:
 *
 * i = 0, character = 'a'
 * arr[0] = 2 - 1 = 1
 *
 * i = 1, character = 'a'
 * arr[0] = 1 - 1 = 0
 *
 * No negative frequency.
 * Output = true
 *
 *
 * DRY RUN 2:
 *
 * ransomNote = "aa"
 * magazine   = "ab"
 *
 * After counting magazine:
 * a = 1
 * b = 1
 *
 * Process ransomNote:
 *
 * i = 0, character = 'a'
 * arr[0] = 1 - 1 = 0
 *
 * i = 1, character = 'a'
 * arr[0] = 0 - 1 = -1
 *
 * Negative frequency detected.
 * Output = false
 *
 *
 * IMPORTANT OBSERVATIONS:
 *
 * 1. Array size is 26 because there are 26 lowercase letters.
 * 2. We count magazine because it contains available characters.
 * 3. We decrement because ransomNote consumes those characters.
 * 4. A negative frequency means a character is unavailable.
 * 5. Repeated characters are handled automatically.
 *
 * LeetCode Submission:
 * Submit only the Solution class.
 */
