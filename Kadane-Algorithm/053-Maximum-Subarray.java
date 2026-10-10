//  * LeetCode 53 - Maximum Subarray
//  *
//  * Pattern: Kadane's Algorithm
//  * Category: Arrays -> Subarrays
//  *
//  * Approach:
//  * 1. Initialize sum = 0 to track the current subarray sum.
//  * 2. Initialize max with the first array element.
//  * 3. Add each element to the current sum.
//  * 4. Update max whenever the current sum is greater.
//  * 5. If sum becomes negative, reset it to 0.
//  * 6. Return max.
//  *
//  * Time Complexity: O(n)
//  * Space Complexity: O(1)
//  */

class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;

        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);

            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }
}