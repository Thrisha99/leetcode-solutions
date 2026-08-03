/*
 * Problem: 1493. Longest Subarray of 1's After Deleting One Element
 * Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 *
 * Pattern:
 * Sliding Window (Variable Size)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * We must delete exactly one element.
 * Treat the deleted element as one zero inside the window.
 * Therefore, maintain a window containing at most one zero.
 *
 * The window length includes the zero, so subtract one
 * to simulate deleting it.
 *
 * Approach:
 * - Expand the window by moving the right pointer.
 * - Count the number of zeros.
 * - If the window contains more than one zero,
 *   shrink it until it becomes valid.
 * - Update the maximum length using:
 *      windowLength - 1
 *   because one element must be deleted.
 *
 * Algorithm:
 * 1. left = 0
 * 2. zeroCount = 0
 * 3. Traverse using right
 * 4. If nums[right] == 0
 *      zeroCount++
 * 5. While zeroCount > 1
 *      If nums[left] == 0
 *          zeroCount--
 *      left++
 * 6. Update answer using (right - left)
 * 7. Return answer
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */

class Solution {
    public int longestSubarray(int[] nums) {
        int l=0;
        int max=0;
        int length=0;
        int z=0;
        int k=1;
        for(int r=0;r<nums.length;r++){
            if(nums[r]==0){
                z++;
            }
            while(z>k){
                if(nums[l]==0){
                    z--;
                }
                l++;
            }
            if(z<=k){
                length=r-l+1;
                max=Math.max(max,length-1);
            }
        }
        if(max==0){
            return 0;
        }
        return max;
    }
}