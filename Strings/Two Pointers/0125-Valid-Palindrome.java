
/*
 * LeetCode 125: Valid Palindrome
 *
 * Problem:
 * Given a string s, determine whether it is a palindrome,
 * considering only alphanumeric characters and ignoring cases.
 *
 * A palindrome reads the same forward and backward.
 *
 * Examples:
 * Input:  "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Input:  "race a car"
 * Output: false
 *
 * Input:  " "
 * Output: true
 *
 * Pattern: Two Pointers
 */

// ==========================================
// APPROACH 1: BRUTE FORCE
// ==========================================

class SolutionBruteForce {

    public boolean isPalindrome(String s) {

        // Step 1: Convert to lowercase
        s = s.toLowerCase();

        // Step 2: Keep only letters and digits
        StringBuilder cleaned = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                cleaned.append(ch);
            }
        }

        // Step 3: Reverse the cleaned string
        String reversed = cleaned.reverse().toString();

        // Step 4: Compare original cleaned string and reversed string
        return cleaned.toString().equals(reversed);
    }
}


// ==========================================
// APPROACH 2: TWO POINTERS
// Your approach
// ==========================================

class SolutionTwoPointers {

    public boolean isPalindrome(String s) {

        s = s.toLowerCase();

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            // Skip non-alphanumeric characters from left
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }

            // Skip non-alphanumeric characters from right
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }

            // If characters don't match, not a palindrome
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            // Move both pointers inward
            i++;
            j--;
        }

        return true;
    }
}


// ==========================================
// APPROACH 3: OPTIMIZED TWO POINTERS
// O(1) Auxiliary Space
// ==========================================

class SolutionOptimal {

    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            // Skip non-alphanumeric characters from left
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }

            // Skip non-alphanumeric characters from right
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }

            // Compare characters without creating a lowercase string
            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(j));

            if (left != right) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
