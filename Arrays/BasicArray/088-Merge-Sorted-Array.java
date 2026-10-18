/*
 * Problem: 88. Merge Sorted Array
 * Link: https://leetcode.com/problems/merge-sorted-array/
 *
 * Pattern: Arrays
 * Difficulty: Easy
 *
 * Approach:
 * - Use three pointers starting from the end.
 * - Compare the largest elements of both arrays.
 * - Place the larger element at the end of nums1.
 * - Continue until all elements of nums2 are merged.
 *
 * Time Complexity: O(m + n)
 * Space Complexity: O(1)
 */

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {

            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }

        }
//using this extra loop for the left over elemnts that need to be copied in nums1 from nums2 after comparing them as elemnts 
        //of nums1 comparison and copying will probably done in case --> Edge case
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }

    }
}
