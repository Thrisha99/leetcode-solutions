// LeetCode 121 - Best Time to Buy and Sell Stock
//  *
//  * Pattern: Greedy / One Pass
//  * Topic: Arrays
//  * Difficulty: Easy
//  *
//  * Time Complexity: O(n)
//  * Space Complexity: O(1)
// You are given an array prices where prices[i] is the price of a given stock on the ith day.

// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 

// Example 1:

// Input: prices = [7,1,5,3,6,4]
// Output: 5
// Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
// Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell

//***Best approach***::
//1. is to keep track of the minimum price seen so far and 
// 2.calculate the profit for each day by subtracting the minimum price from the current price.
//  3.Update the maximum profit whenever a higher profit is found. 

// | Day | Price | Minimum so far | Profit if sell today |
// |   7 |     7 |              7 |                    0 |
// |   1 |     1 |              1 |                    0 |
// |   5 |     5 |              1 |                    4 |
// |   3 |     3 |              1 |                    2 |
// |   6 |     6 |              1 |                **5** |//output: 5
// |   4 |     4 |              1 |                    3 |


class Solution {
    public int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            // Find the cheapest buying price so far
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            // Calculate profit if we sell today
            int profit = prices[i] - minPrice;

            // Keep the maximum profit
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }
}