# Prefix Sum

## What is Prefix Sum?

Prefix Sum is a preprocessing technique used to answer multiple range sum
queries efficiently.

Instead of calculating the sum every time, we store the cumulative sum
of the array.

Each element in the prefix array stores the sum of all previous elements
including itself.

---

# When Should You Think of Prefix Sum?

Look for words like:

- Range Sum
- Sum between indices
- Query
- Multiple Queries
- Cumulative Sum
- Prefix
- Continuous Sum
- Running Total

If multiple range sum calculations are required,

think Prefix Sum.

---

# What is a Prefix Sum Array?

Given

nums

[2,4,6,8]

Prefix Sum

[2,6,12,20]

Explanation

prefix[0] = 2

prefix[1] = 2 + 4 = 6

prefix[2] = 2 + 4 + 6 = 12

prefix[3] = 2 + 4 + 6 + 8 = 20

---

# Formula

prefix[0] = nums[0]

prefix[i] = prefix[i-1] + nums[i]

---

# Range Sum Formula

Need sum from

left

to

right

If

left == 0

Answer

prefix[right]

Otherwise

Answer

prefix[right] - prefix[left-1]

---

# Running Sum vs Prefix Sum

## Running Sum

Purpose

Store cumulative sum.

Usually returned as the final answer.

Example

1480. Running Sum of 1D Array

---

## Prefix Sum

Purpose

Answer range sum queries efficiently.

Example

303. Range Sum Query - Immutable

---

# Advantages

✅ O(1) range sum queries after preprocessing

✅ Eliminates repeated summation

✅ Very useful when many queries are asked

---

# Time Complexity

Building Prefix Array

O(n)

Each Query

O(1)

---

# Space Complexity

O(n)

---

# Common Mistakes

❌ Forgetting

left == 0

case

❌ Using

prefix[left]

instead of

prefix[left-1]

❌ Building prefix incorrectly

❌ Recalculating the sum every query

---

# Pattern Recognition

Need multiple range sum queries?

↓

YES

↓

Prefix Sum

-------------------------

Need cumulative total?

↓

Running Sum

-------------------------

Need subarray sum?

↓

Prefix Sum

---

# Problem 1480 - Running Sum of 1D Array

## Actual Pattern

Running Sum

---

## Key Observation

Every element stores

Current Value

+

Previous Running Sum

---

## Dry Run

Input

[1,2,3,4]

Running Sum

1

↓

1+2 = 3

↓

3+3 = 6

↓

6+4 = 10

Output

[1,3,6,10]

---

## Trick

Whenever the problem asks

Running Total

Cumulative Sum

Running Balance

Think

Running Sum

---

## Common Mistakes

❌ Starting from index 0

❌ Forgetting to use the previous running sum

---

## Interview Question

Can Running Sum answer range sum queries?

No.

It only stores cumulative totals.

For efficient range queries,

Prefix Sum is preferred.

Time Complexity

O(n)

Space Complexity

O(1)

(if modifying the original array)

or

O(n)

(if creating a new array)

---

# Problem 303 - Range Sum Query - Immutable

## Actual Pattern

Prefix Sum

---

## Key Observation

Many queries are performed.

Instead of recalculating every range,

precompute the Prefix Sum array once.

---

## Dry Run

nums

[1,2,3,4]

Prefix

[1,3,6,10]

Query

left = 1

right = 3

Answer

10 - 1

=

9

Subarray

[2,3,4]

---

## Formula

left == 0

↓

prefix[right]

Otherwise

↓

prefix[right] - prefix[left-1]

---

## Trick

Need many

sum(left,right)

queries?

↓

Build Prefix Sum once.

Answer every query in O(1).

---

## Common Mistakes

❌ Forgetting

left == 0

❌ Calculating the sum for every query

O(n)

instead of

O(1)

❌ Incorrect prefix array construction

---

## Interview Question

Why is Prefix Sum useful?

Building the prefix array takes O(n),

but every range query becomes O(1).

This is much faster than recalculating each range.

Time Complexity

Preprocessing

O(n)

Each Query

O(1)

Space Complexity

O(n)

---

# Problem Comparison

## 1480

Goal

Build cumulative totals.

Return the running sum.

---

## 303

Goal

Answer multiple range sum queries.

Return sum(left,right).

---

# Revision Checklist

## Running Sum

- [x] 1480. Running Sum of 1D Array

---

## Prefix Sum

- [x] 303. Range Sum Query - Immutable

---

# Prefix Sum Cheat Sheet

Running Sum

↓

Current + Previous Sum

----------------------------

Prefix Sum

↓

Store cumulative sums

----------------------------

Range Sum

↓

prefix[right]

-

prefix[left-1]

----------------------------

left == 0

↓

prefix[right]

---

# Summary

Whenever you see

- Range Sum
- Multiple Queries
- Continuous Sum
- Prefix
- Cumulative Sum

👉 Think **Prefix Sum** first.
---

# Problem 1480 - Running Sum of 1D Array

## Actual Pattern

Running Sum

(Foundation of Prefix Sum)

---

## Problem Description

Given an integer array nums,

return the running sum of the array.

Running Sum

=

Current Element

+

Sum of all previous elements.

---

## Key Observation

Every running sum depends only on

the previous running sum.

Instead of calculating from the beginning every time,

reuse the previous answer.

---

## Pattern

Running Sum

(Current)

↓

Previous Running Sum

+

Current Value

---

## Dry Run

Input

[1,2,3,4]

Running Sum

1

↓

1 + 2 = 3

↓

3 + 3 = 6

↓

6 + 4 = 10

Output

[1,3,6,10]

---

## Formula

runningSum[i]

=

runningSum[i-1]

+

nums[i]

---

## Trick

Whenever the problem says

Running Sum

Running Total

Cumulative Total

Running Balance

Think

Running Sum

---

## Common Mistakes

❌ Starting from index 0

❌ Forgetting to use the previous cumulative sum

❌ Creating unnecessary nested loops

---

## Interview Question

Why is this called the foundation of Prefix Sum?

Because every Prefix Sum array is simply a Running Sum array.

Learning this problem makes Prefix Sum much easier to understand.

Time Complexity

O(n)

Space Complexity

O(1)

# LeetCode 238 - Product of Array Except Self

## Problem Number

238

## Problem Name

Product of Array Except Self

## Difficulty

Medium

## Pattern

**Prefix Product + Suffix Product**

## LeetCode

https://leetcode.com/problems/product-of-array-except-self/

---

# 1. Problem Understanding

Given an integer array `nums`, return an array `answer` where:

```text
answer[i] = product of every element except nums[i]