/*
 * Problem: 1672. Richest Customer Wealth
 * Link: https://leetcode.com/problems/richest-customer-wealth/
 *
 * Pattern: Arrays (2D Arrays / Matrix Traversal)
 * Difficulty: Easy
 *
 * Approach:
 * - Traverse each customer's bank accounts (each row).
 * - Calculate the total wealth of that customer.
 * - Compare it with the maximum wealth found so far.
 * - Return the maximum wealth.
 *
 * Time Complexity: O(m × n)
 * where m = number of customers
 *       n = number of bank accounts
 *
 * Space Complexity: O(1)
 */

class Solution {

    public int maximumWealth(int[][] accounts) {

        int maxWealth = 0;

        for (int i = 0; i < accounts.length; i++) {

            int currentWealth = 0;

            for (int j = 0; j < accounts[i].length; j++) {

                currentWealth += accounts[i][j];

            }

            maxWealth = Math.max(maxWealth, currentWealth);
        }

        return maxWealth;
    }
}