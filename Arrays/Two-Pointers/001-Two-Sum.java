/*
 * Problem: 1. Two Sum
 * Link: https://leetcode.com/problems/two-sum/
 *
 * Pattern:
 * HashMap
 *
 * Difficulty:
 * Easy
 *
 * Intuition:
 * While traversing the array, instead of searching every previous element,
 * store each number in a HashMap. For every current element, check whether
 * its complement (target - current number) already exists.
 *
 * Approach:
 * - Traverse the array once.
 * - Compute the complement.
 * - If the complement exists in the HashMap, return both indices.
 * - Otherwise, store the current value and its index.
 *
 * Algorithm:
 * 1. Create a HashMap.
 * 2. Traverse the array.
 * 3. Calculate complement.
 * 4. Check whether complement exists.
 * 5. Return indices if found.
 * 6. Otherwise insert current element.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 */

class Solution {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {

                return new int[]{
                        map.get(complement),
                        i
                };

            }

            map.put(nums[i], i);

        }

        return new int[]{};
    }
}