# LeetCode 53 — Maximum Subarray

## Problem
Given an integer array `nums`, find the non-empty contiguous subarray with the largest sum and return that sum.

## Example

**Input:** `nums = [-2,1,-3,4,-1,2,1,-5,4]`

**Output:** `6`

**Maximum-sum subarray:** `[4,-1,2,1]`

**Calculation:** `4 + (-1) + 2 + 1 = 6`

## Pattern
- Category: Arrays
- Pattern: Kadane's Algorithm
- Technique: Greedy, one-pass traversal, running sum

## Approach
1. Initialize `sum = 0` to track the current subarray sum.
2. Initialize `max = nums[0]` to handle negative values correctly.
3. Add each element to `sum`.
4. Update `max` with the larger of `max` and `sum`.
5. If `sum < 0`, reset it to `0` because a negative running sum cannot improve a future subarray.
6. Return `max`.

## Key Concept
A negative running sum is discarded because starting a new subarray at the next element gives a better sum than carrying the negative sum forward.

A negative element alone does not mean the subarray must be reset.

## Complexity
- Time: O(n)
- Auxiliary space: O(1)

## Edge Cases
- Single element: `[5]` → `5`
- All negative: `[-5,-2,-8]` → `-2`
- All positive: `[1,2,3,4]` → `10`
- Mixed values: `[-2,1,-3,4,-1,2,1,-5,4]` → `6`

## Revision Reminder
Remember: **ADD → UPDATE MAX → RESET IF NEGATIVE**

Initialize `max` with the first array element, not zero, because the required subarray must be non-empty.
