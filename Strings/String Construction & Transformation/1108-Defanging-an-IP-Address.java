/* 
 * LeetCode Problem: 1108
 * Title: Defanging an IP Address
 * Difficulty: Easy
 * Pattern: String Manipulation
 *
 * Problem:
 * Given a valid IPv4 address, replace every dot ('.')
 * with '[.]' and return the defanged IP address.
 *
 * Example:
 * Input:  "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 *
 * Approach: StringBuilder + Character Traversal
 *
 * Time Complexity: O(n)
 * Auxiliary Space Complexity: O(n)
 */

class Solution {
    public String defangIPaddr(String address) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < address.length(); i++) {

            if (address.charAt(i) == '.') {
                sb.append("[.]");
            } else {
                sb.append(address.charAt(i));
            }
        }

        return sb.toString();
    }
}

/*
 * Alternative One-Line Approach:
 *
 * return address.replace(".", "[.]");
 */
