/*
 * LeetCode 189 - Rotate Array
 *
 * Pattern: Array Rotation
 * Technique: Reversal Algorithm
 *
 * Problem:
 * Rotate the array to the right by k steps.
 *
 * Approach:
 * 1. Reverse the last k elements.
 * 2. Reverse the first n-k elements.
 * 3. Reverse the entire array.
 *
 * Example:
 * Input:  [1, 2, 3, 4, 5], k = 2
 * Output: [4, 5, 1, 2, 3]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
     void reverse(int nums[],int start,int end){
        while(start<=end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start++;
            end--;
            
        }
    }
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
      reverse(nums,n-k,n-1);
      reverse(nums,0,n-k-1);
      reverse(nums,0,n-1);

    }
}