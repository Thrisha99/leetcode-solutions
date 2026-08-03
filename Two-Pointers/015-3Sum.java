/*
 * Problem: 15. 3Sum
 * Link: https://leetcode.com/problems/3sum/
 *
 * Pattern:
 * Sorting + Two Pointers (Opposite Direction)
 *
 * Difficulty:
 * Medium
 *
 * Intuition:
 * Fix one element, then use two pointers to find the
 * remaining two elements whose sum equals the negative
 * of the fixed element.
 *
 * Sorting allows us to:
 * - Use Two Pointers
 * - Skip duplicate values efficiently
 *
 * Approach:
 * - Sort the array.
 * - Iterate through each element as the first number.
 * - Skip duplicate first elements.
 * - Use left and right pointers to search for two numbers.
 * - Skip duplicate left and right values after finding a triplet.
 *
 * Algorithm:
 * 1. Sort the array.
 * 2. Traverse using index i.
 * 3. Skip duplicate i values.
 * 4. left = i + 1
 * 5. right = n - 1
 * 6. Compare total = nums[i] + nums[left] + nums[right]
 * 7. Move pointers accordingly.
 * 8. Store unique triplets.
 *
 * Time Complexity:
 * O(n²)
 *
 * Space Complexity:
 * O(1)
 * (excluding the output list)
 */

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right &&
                           nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right &&
                           nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;

                }

            }

        }

        return ans;
    }
}