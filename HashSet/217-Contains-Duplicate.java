/*
 * LeetCode 217 - Contains Duplicate
 *
 * Problem:
 * Given an integer array nums, return true if any value appears
 * at least twice in the array, and return false if every element
 * is distinct.
 *
 * Approach 1: Brute Force
 * - Compare every pair of elements using nested loops.
 * - Time Complexity: O(n^2)
 * - Space Complexity: O(1)
 *
 * Approach 2: HashSet (Optimal)
 * - Store elements that have already been seen.
 * - Before adding an element, check whether it already exists.
 * - Time Complexity: O(n) average
 * - Space Complexity: O(n)
 */

import java.util.HashSet;

class Solution {

    // Approach 1: Brute Force
    public boolean containsDuplicateBruteForce(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }


    // Approach 2: HashSet - Optimal
    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            // If number is already present, duplicate found
            if (set.contains(num)) {
                return true;
            }

            // Store the number for future checking
            set.add(num);
        }

        return false;
    }
}