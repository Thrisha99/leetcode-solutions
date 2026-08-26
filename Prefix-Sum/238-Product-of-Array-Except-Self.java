/*
 * Problem: 238. Product of Array Except Self
 * Link: https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern:
 * Prefix/Suffix Product
 *
 * Difficulty:
 * Medium
 * * Approach:
 * 1. Store the product of all elements to the LEFT of each index in ans[].
 * 2. Traverse from RIGHT to LEFT using one variable `right`
 *    to maintain the product of all elements to the RIGHT.
 * 3. Multiply left product × right product to get the answer.
 * 
 * FOR EACH INDEX:

        LEFT     CURRENT     RIGHT
         ↓          ↓          ↓
nums = [ 1    2    [3]    4 ]

answer = LEFT PRODUCT × RIGHT PRODUCT
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1) extra space
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // int[] l=new int[nums.length];
        // int[] r=new int[nums.length];
        // int[] ans=new int[nums.length];
        // l[0]=1;
        // r[nums.length-1]=1;
        // for(int i=1;i<nums.length;i++){
        //     l[i]=l[i-1]*nums[i-1];
        // }
        // for(int i=nums.length-2;i>=0;i--){
        //     r[i]=r[i+1]*nums[i+1];
        // }
        // for(int i=0;i<nums.length;i++){
        //     ans[i]=l[i]*r[i];
        // }
        // return ans;
        int n=nums.length;
        int[] ans=new int[n];
        ans[0]=1;
        for(int i=1;i<n;i++){
            ans[i]=nums[i-1]*ans[i-1];
        }
        int r=1;
        for(int i=n-1;i>=0;i--){
            ans[i]=ans[i]*r;
            r=r*nums[i];
        }
        return ans;
    }
}


// Main Idea:
// answer[i] = LEFT PRODUCT × RIGHT PRODUCT

// Pass 1:
// Store LEFT products in ans[]

// Pass 2:
// Traverse RIGHT → LEFT
// Maintain RIGHT product using one variable

// Important:
// ans[i] = ans[i] × right
// BEFORE
// right = right × nums[i]

// Why?
// Current element must be excluded.

// Time:
// O(n)

// Extra Space:
// O(1)

// Key Mental Model:
// LEFT | CURRENT | RIGHT

// answer = LEFT × RIGHT