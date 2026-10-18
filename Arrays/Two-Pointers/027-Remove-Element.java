/*
 * Problem: 27. Remove Element
 * Link: https://leetcode.com/problems/remove-element/
 *
 * Pattern:
 * Two Pointers (Fast & Slow Pointer)
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * We don't need to keep the elements equal to val.
 * The fast pointer scans every element.
 * The slow pointer keeps the position where the next
 * valid element should be placed.
 *
 * Approach:
 * - Traverse the array using the fast pointer.
 * - If nums[fast] is not equal to val:
 *      Copy it to nums[slow].
 *      Move slow forward.
 * - Ignore elements equal to val.
 * - Return slow as the new length.
 *
 * Algorithm:
 * 1. slow = 0
 * 2. Traverse using fast
 * 3. If nums[fast] != val
 *      nums[slow] = nums[fast]
 *      slow++
 * 4. Return slow
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public int removeElement(int[] nums, int val) {

        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {

            if (nums[fast] != val) {

                nums[slow] = nums[fast];
                slow++;

            }

        }

        return slow;
    }
}