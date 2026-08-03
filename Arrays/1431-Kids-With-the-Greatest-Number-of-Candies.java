/*
 * Problem: 1431. Kids With the Greatest Number of Candies
 * Link: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Find the maximum number of candies among all kids.
 * - Traverse the array again.
 * - If current candies + extraCandies >= maximum,
 *   add true to the answer.
 * - Otherwise add false.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max = 0;

        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        List<Boolean> ans = new ArrayList<>();

        for (int candy : candies) {

            if (candy + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }

        }

        return ans;
    }
}