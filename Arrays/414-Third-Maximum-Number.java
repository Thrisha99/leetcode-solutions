/*
 * Problem: 414. Third Maximum Number
 * Link: https://leetcode.com/problems/third-maximum-number/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Maintain three distinct maximum values.
 * - Ignore duplicate values.
 * - Update first, second, and third maximums while traversing.
 * - If a third distinct maximum doesn't exist, return the largest.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int thirdMax(int[] nums) {

        Long first = null;
        Long second = null;
        Long third = null;

        for (int num : nums) {

            if ((first != null && num == first) ||
                (second != null && num == second) ||
                (third != null && num == third))
                continue;

            if (first == null || num > first) {

                third = second;
                second = first;
                first = (long) num;

            } else if (second == null || num > second) {

                third = second;
                second = (long) num;

            } else if (third == null || num > third) {

                third = (long) num;
            }
        }

        return third == null ? first.intValue() : third.intValue();
    }
}