/*
 * Problem: 1004. Max Consecutive Ones III
 * Link: https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Pattern:
 * Sliding Window (Variable Size)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * We are allowed to flip at most k zeros.
 * Instead of actually flipping them, maintain a window
 * that contains at most k zeros.
 *
 * If the number of zeros exceeds k, the window becomes
 * invalid. Shrink it until it becomes valid again.
 *
 * The longest valid window is the answer.
 *
 * Approach:
 * - Expand the window by moving the right pointer.
 * - If nums[right] is 0, increase zeroCount.
 * - While zeroCount > k:
 *      If nums[left] is 0, decrease zeroCount.
 *      Move the left pointer.
 * - After the window becomes valid, update the maximum length.
 *
 * Algorithm:
 * 1. left = 0
 * 2. zeroCount = 0
 * 3. Traverse using right
 * 4. If nums[right] == 0
 *      zeroCount++
 * 5. While zeroCount > k
 *      If nums[left] == 0
 *          zeroCount--
 *      left++
 * 6. Update maximum window length
 * 7. Return answer
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 */




class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0;
        int z = 0;
        int max = Integer.MIN_VALUE;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                z++;
            }
            while(z>k){
                if(nums[l]==0){
                    z--;
                }
                l++;
            }
            if(z<=k){
                max=Math.max(max,r-l+1);
            }
            

        }
        return max;
    }
}