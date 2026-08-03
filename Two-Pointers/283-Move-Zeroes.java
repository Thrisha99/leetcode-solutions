/*
 * Problem: 283. Move Zeroes
 * Link: https://leetcode.com/problems/move-zeroes/
 *
 * Pattern:
 * Two Pointers (Fast & Slow Pointer)
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * Move all non-zero elements to the beginning while
 * preserving their relative order.
 * After placing all non-zero elements, fill the
 * remaining positions with zeroes.
 *
 * Approach:
 * - Use the slow pointer to indicate the next position
 *   for a non-zero element.
 * - Traverse the array using the fast pointer.
 * - Whenever a non-zero element is found:
 *      Swap it with nums[slow].
 *      Move slow forward.
 * - Zeroes automatically move towards the end.
 *
 * Algorithm:
 * 1. slow = 0
 * 2. Traverse using fast
 * 3. If nums[fast] != 0
 *      Swap nums[slow] and nums[fast]
 *      slow++
 * 4. Finish traversal
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public void moveZeroes(int[] nums) {

        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {

            if (nums[fast] != 0) {

                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;

                slow++;

            }

        }

    }
}