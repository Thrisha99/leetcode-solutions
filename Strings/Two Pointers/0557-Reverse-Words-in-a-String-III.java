
/*
 * ============================================================
 * LeetCode 557: Reverse Words in a String III
 * ============================================================
 *
 * Problem Link:
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 *
 * Difficulty: Easy
 * Topic: Strings
 * Pattern: Two Pointers - In-place Reversal
 *
 * Problem:
 * Given a string s, reverse the characters of each word
 * while preserving the original order of words and spaces.
 *
 * Example:
 * Input:  "Let's code"
 * Output: "s'teL edoc"
 *
 * Input:  "God Ding"
 * Output: "doG gniD"
 *
 * Input Constraints:
 * - 1 <= s.length <= 5 * 10^4
 * - s contains printable ASCII characters.
 * - Words are separated by single spaces.
 *
 * ============================================================
 * APPROACH 1: BRUTE FORCE
 * ============================================================
 *
 * Technique:
 * String splitting + StringBuilder
 *
 * Idea:
 * 1. Split the input into individual words.
 * 2. Reverse each word using StringBuilder.
 * 3. Join the reversed words using spaces.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(n)
 */

class SolutionBruteForce {

    public String reverseWords(String s) {

        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            StringBuilder word = new StringBuilder(words[i]);
            result.append(word.reverse());

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}


/*
 * ============================================================
 * APPROACH 2: TWO POINTERS + CHARACTER ARRAY
 * ============================================================
 *
 * Technique:
 * Character array + Two pointers
 *
 * Idea:
 * 1. Convert String into char[].
 * 2. Traverse the array.
 * 3. Whenever a space is encountered, reverse the previous word.
 * 4. Update the starting index of the next word.
 * 5. Reverse the final word separately.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(n)
 *
 * This is the approach developed during problem solving.
 */

class SolutionTwoPointers {

    public void reverse(char[] ch, int i, int j) {

        while (i < j) {

            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;

            i++;
            j--;
        }
    }

    public String reverseWords(String s) {

        char[] ch = s.toCharArray();

        int start = 0;

        for (int i = 0; i < ch.length; i++) {

            if (ch[i] == ' ') {

                reverse(ch, start, i - 1);

                start = i + 1;
            }
        }

        // Reverse the last word
        reverse(ch, start, ch.length - 1);

        return new String(ch);
    }
}


/*
 * ============================================================
 * APPROACH 3: OPTIMAL JAVA SOLUTION
 * ============================================================
 *
 * Technique:
 * Two Pointers + Character Array
 *
 * Idea:
 * Reverse each word independently by swapping characters
 * from both ends toward the center.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(n)
 *
 * Note:
 * This is the recommended solution for Java.
 * It achieves optimal O(n) time complexity.
 */

class SolutionOptimal {

    public String reverseWords(String s) {

        char[] ch = s.toCharArray();

        int start = 0;

        for (int i = 0; i <= ch.length; i++) {

            if (i == ch.length || ch[i] == ' ') {

                int left = start;
                int right = i - 1;

                while (left < right) {

                    char temp = ch[left];
                    ch[left] = ch[right];
                    ch[right] = temp;

                    left++;
                    right--;
                }

                start = i + 1;
            }
        }

        return new String(ch);
    }
}


/*
 * ============================================================
 * APPROACH COMPARISON
 * ============================================================
 *
 * Approach 1: Split + StringBuilder
 * Time: O(n)
 * Auxiliary Space: O(n)
 *
 * Approach 2: Two Pointers + Helper Method
 * Time: O(n)
 * Auxiliary Space: O(n)
 *
 * Approach 3: Two Pointers + Inline Reversal
 * Time: O(n)
 * Auxiliary Space: O(n)
 *
 * BEST CHOICE:
 * Approach 2 or Approach 3.
 *
 * Both have optimal O(n) time complexity and O(n)
 * auxiliary space in Java because a mutable char[]
 * is required to modify the immutable input String.
 *
 * ============================================================
 */
