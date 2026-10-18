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
 //Main LOgic:
 current > largest
    largest → second
    second  → third
    current → largest

current > second
    second → third
    current → second

current > third
    current → third

duplicate
    ignore

after loop:
    third exists → return third
    no third      → return largest
    //


    
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int thirdMax(int[] nums) {
        /*The actual purpose of long**-->
We are not using long because we need bigger numbers. We use long so that Long.MIN_VALUE can act as a special 
"not initialized" marker that cannot be confused with any int input.

   /* int input
   ↓
need "not found yet" marker
   ↓
use Long.MIN_VALUE
   ↓
because it is outside the possible int range  */

        long l = Long.MIN_VALUE;
        long s = Long.MIN_VALUE;
        long t = Long.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            // Ignore duplicates
            if (nums[i] == l || nums[i] == s || nums[i] == t) {
                continue;
            }

            // New largest
            if (nums[i] > l) {
                t = s;
                s = l;
                l = nums[i];
            }

            // New second largest
            else if (nums[i] > s) {
                t = s;
                s = nums[i];
            }

            // New third largest
            else if (nums[i] > t) {
                t = nums[i];
            }
        }

        // If third maximum doesn't exist, return maximum
        if (t == Long.MIN_VALUE) {
            return (int) l;
        }

        return (int) t;
    }
}
