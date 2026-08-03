/*
 * Problem: 209. Minimum Size Subarray Sum
 * Link: https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Pattern:
 * Sliding Window (Variable Size)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * Since all numbers are positive, expanding the window
 * increases the sum and shrinking the window decreases
 * the sum.
 *
 * Once the current sum becomes greater than or equal to
 * the target, shrink the window to find the minimum
 * possible length.
 *
 * Approach:
 * - Expand the window by moving the right pointer.
 * - Add each element to the current sum.
 * - While the sum is greater than or equal to the target:
 *      Update the minimum length.
 *      Remove the left element.
 *      Move the left pointer.
 * - Return 0 if no valid subarray exists.
 *
 * Algorithm:
 * 1. left = 0
 * 2. sum = 0
 * 3. Traverse using right
 * 4. Add nums[right]
 * 5. While sum >= target
 *      Update answer
 *      Remove nums[left]
 *      left++
 * 6. Return answer
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum >= target) {

                minLength = Math.min(minLength, right - left + 1);

                sum -= nums[left];
                left++;

            }

        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}