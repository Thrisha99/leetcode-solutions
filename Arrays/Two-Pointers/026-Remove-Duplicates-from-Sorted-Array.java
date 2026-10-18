/*
 * Problem: 26. Remove Duplicates from Sorted Array
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Pattern:
 * Two Pointers (Fast & Slow Pointer)
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * Since the array is sorted, duplicate elements are adjacent.
 * Keep one pointer (slow) at the last unique element and
 * another pointer (fast) to scan the array.
 *
 * Approach:
 * - Initialize slow pointer at index 0.
 * - Traverse the array using the fast pointer.
 * - If nums[fast] is different from nums[slow],
 *   move slow forward and copy nums[fast].
 * - Return slow + 1 as the number of unique elements.
 *
 * Algorithm:
 * 1. slow = 0
 * 2. Traverse using fast from index 1
 * 3. Compare nums[fast] and nums[slow]
 * 4. If different:
 *      slow++
 *      nums[slow] = nums[fast]
 * 5. Return slow + 1
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {

            if (nums[fast] != nums[slow]) {

                slow++;
                nums[slow] = nums[fast];

            }

        }

        return slow + 1;
    }
}