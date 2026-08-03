# Two Pointers

## What is the Two Pointer Technique?

The Two Pointer technique uses two indices to traverse an array or string efficiently.

Instead of using nested loops (O(n²)), many problems can be solved in O(n).

---

# When Should I Think of Two Pointers?

✅ Sorted Array

✅ Pair Sum

✅ Remove Duplicates

✅ Remove Elements

✅ Reverse Array/String

✅ Merge Two Sorted Arrays

✅ Square of Sorted Array

---
int slow = 0;

for (int fast = 1; fast < nums.length; fast++) {

    if (condition) {

        slow++;
        nums[slow] = nums[fast];

    }

}

# Types of Two Pointers

## 1. Opposite Direction

```java
int left = 0;
int right = arr.length - 1;

while (left < right) {

}
```

Examples

- Two Sum II
- Reverse String
- Valid Palindrome

---

## 2. Same Direction

```java
int slow = 0;

for (int fast = 0; fast < arr.length; fast++) {

}
```

Examples

- Remove Duplicates
- Remove Element
- Move Zeroes

---

## 3. Multiple Pointers

Example

Merge Sorted Array

Three Sum

---

# Time Complexity

Most Two Pointer problems

O(n)

---

# Common Mistakes

❌ Moving the wrong pointer

❌ Infinite loops

❌ Forgetting sorted property

❌ Wrong loop condition

---

# Problems Solved

| # | Problem | Difficulty | Status |
|---|---------|------------|--------|

---

# Revision Checklist

- [ ] Opposite Direction
- [ ] Same Direction
- [ ] Fast & Slow Pointer
- [ ] Merge Technique
- [ ] In-place Modification

---

# Problem 1 - Two Sum

## Actual Pattern

HashMap

Although this problem appears before Two Sum II, the optimal solution uses a HashMap instead of Two Pointers because the array is **not sorted**.

---

## Key Observation

Need

target = nums[i] + nums[j]

↓

Instead of searching every element again,

Store previously visited numbers.

---

## Dry Run

nums

[2,7,11,15]

target

9

Iteration

2

Need 7

Not found

Store

2

↓

7

Need 2

Found

Answer

[0,1]

---

## Trick

Whenever you need

target - currentElement

Think

HashMap

---

## Common Mistakes

❌ Sorting the array

Sorting changes indices.

❌ Using nested loops

Complexity becomes O(n²).

---

## Interview Question

Can Two Pointers solve this?

Yes,

but only **after sorting**.

Sorting changes the original indices, so for this problem HashMap is the preferred solution.

---

## Related Problems

- Two Sum II
- 3Sum
- 4Sum
- Contains Duplicate
---

# Problem 167 - Two Sum II

## Actual Pattern

Opposite Direction Two Pointers

---

## Key Observation

The array is already sorted.

Therefore,

Increasing the left pointer increases the sum.

Decreasing the right pointer decreases the sum.

No HashMap is needed.

---

## Pattern

Opposite Direction

left → ← right

---

## Dry Run

Input

numbers = [2,7,11,15]

target = 9

left = 0

right = 3

2 + 15 = 17

Too large

Move right

↓

2 + 11 = 13

Too large

Move right

↓

2 + 7 = 9

Answer

[1,2]

---

## Pointer Movement

sum < target

Move left

↓

Need a larger value

----------------------------------

sum > target

Move right

↓

Need a smaller value

---

## Trick

Whenever the array is

SORTED

and asks for

Pair Sum

Think

Two Pointers

---

## Common Mistakes

❌ Using HashMap

Works but unnecessary.

❌ Returning zero-based indices.

The problem asks for

1-based indexing.

❌ Moving both pointers.

Move only one pointer each iteration.

---

## Interview Question

Why does Two Pointers work?

Because the array is sorted.

Every pointer movement changes the sum in a predictable direction.

Time Complexity

O(n)

Space Complexity

O(1)

---

# Problem 26 - Remove Duplicates from Sorted Array

## Actual Pattern

Fast & Slow Pointer

---

## Key Observation

The array is sorted.

Therefore,

All duplicates appear together.

We only need to keep one copy of each value.

---

## Pattern

Same Direction Two Pointers

slow → stores unique elements

fast → scans every element

---

## Dry Run

Input

[1,1,2,2,3]

Initial

slow = 0

fast = 1

nums[1] == nums[0]

Duplicate

Skip

↓

fast = 2

nums[2] != nums[0]

Move slow

slow = 1

nums[1] = 2

↓

fast = 3

Duplicate

↓

fast = 4

Different

slow = 2

nums[2] = 3

Result

[1,2,3]

Return

3

---

## Pointer Movement

Duplicate

↓

Move only fast

----------------------

Unique

↓

Move both

Copy value

---

## Trick

Whenever a question asks

Remove

Compress

Keep unique values

Think

Fast & Slow Pointer

---

## Common Mistakes

❌ Starting fast from index 0

❌ Returning nums.length

❌ Forgetting the array is sorted

❌ Returning slow instead of slow + 1

---

## Interview Question

Why do we need two pointers?

The fast pointer scans every element.

The slow pointer keeps the position where the next unique value should be placed.

Time Complexity

O(n)

Space Complexity

O(1)

---

# Problem 27 - Remove Element

## Actual Pattern

Fast & Slow Pointer

---

## Key Observation

We only copy the elements that should remain.

The slow pointer always points to the next position where a valid element should be placed.

---

## Pattern

Same Direction Two Pointers

slow → next valid position

fast → scans every element

---

## Dry Run

Input

nums = [3,2,2,3]

val = 3

Initial

slow = 0

fast = 0

nums[0] = 3

Skip

↓

fast = 1

nums[1] = 2

nums[0] = 2

slow = 1

↓

fast = 2

nums[2] = 2

nums[1] = 2

slow = 2

↓

fast = 3

nums[3] = 3

Skip

Final

[2,2]

Return

2

---

## Pointer Movement

nums[fast] == val

↓

Move only fast

----------------------

nums[fast] != val

↓

Copy element

Move both pointers

---

## Trick

Whenever the question asks:

- Remove elements
- Filter values
- Keep only valid elements

Think:

Fast & Slow Pointer

---

## Common Mistakes

❌ Incrementing slow even when the value should be removed

❌ Returning nums.length

❌ Comparing the wrong values

---

## Interview Question

Why doesn't this require a sorted array?

Because we only check whether the current element equals val.

No comparison with neighboring elements is needed.

Time Complexity

O(n)

Space Complexity

O(1)
---

# Problem 283 - Move Zeroes

## Actual Pattern

Fast & Slow Pointer

---

## Key Observation

Instead of moving every zero,

move every non-zero element forward.

The zeroes naturally shift to the end.

---

## Pattern

Same Direction Two Pointers

slow → next position for a non-zero

fast → scans every element

---

## Dry Run

Input

[0,1,0,3,12]

Initial

slow = 0

fast = 0

Zero

Skip

↓

fast = 1

Swap

1 ↔ 0

Array

[1,0,0,3,12]

slow = 1

↓

fast = 2

Zero

Skip

↓

fast = 3

Swap

3 ↔ 0

Array

[1,3,0,0,12]

slow = 2

↓

fast = 4

Swap

12 ↔ 0

Array

[1,3,12,0,0]

Answer

[1,3,12,0,0]

---

## Pointer Movement

Zero

↓

Move only fast

----------------------

Non-zero

↓

Swap

Move both pointers

---

## Trick

Whenever the question asks:

Move

Shift

Rearrange

Maintain order

Think

Fast & Slow Pointer

---

## Common Mistakes

❌ Creating another array

❌ Losing the order of non-zero elements

❌ Swapping every element unnecessarily

---

## Interview Question

Why is swapping enough?

The slow pointer always points to the first available position where the next non-zero element belongs.

Time Complexity

O(n)

Space Complexity

O(1)
---

# Fast & Slow Pointer Comparison

## Remove Duplicates

Condition

nums[fast] != nums[slow]

Copy unique values.

---

## Remove Element

Condition

nums[fast] != val

Copy valid values.

---

## Move Zeroes

Condition

nums[fast] != 0

Swap instead of just copying.

Goal

Maintain the relative order of all non-zero elements.

---

# Problem 977 - Squares of a Sorted Array

## Actual Pattern

Opposite Direction Two Pointers

---

## Key Observation

The largest square always comes from one of the two ends.

Not from the middle.

Reason:

The largest absolute value is always located at either end.

---

## Pattern

Opposite Direction

left → ← right

Fill answer array from the end.

---

## Dry Run

Input

[-4,-1,0,3,10]

Compare

|-4| = 4

|10| = 10

100

↓

Compare

|-4| = 4

|3| = 3

16

↓

Compare

|-1| = 1

|3| = 3

9

↓

Compare

|-1| = 1

|0| = 0

1

↓

0

Answer

[0,1,9,16,100]

---

## Trick

Whenever the question involves:

Negative numbers

+

Sorted array

+

Square

Think

Compare absolute values.

---

## Pointer Movement

|left| > |right|

↓

Move left

----------------------

Otherwise

↓

Move right

---

## Common Mistakes

❌ Squaring first and then sorting

O(n log n)

❌ Comparing actual values instead of absolute values

❌ Filling the answer array from the front

---

## Interview Question

Why fill from the end?

Because the largest square is produced first.

The answer array must remain sorted.

Time Complexity

O(n)

Space Complexity

O(n)

---

# Problem 15 - 3Sum

## Actual Pattern

Sorting + Opposite Direction Two Pointers

---

## Key Observation

Brute Force

Three nested loops

↓

O(n³)

Too slow.

Sorting allows us to fix one element and search
for the remaining two elements using Two Pointers.

↓

O(n²)

---

## Pattern

Sort

↓

Fix first element

↓

Use Two Pointers

---

## Dry Run

Input

[-1,0,1,2,-1,-4]

After Sorting

[-4,-1,-1,0,1,2]

i = -4

Need sum = 4

No answer

-------------------

i = -1

left = -1

right = 2

sum = 0

Triplet

[-1,-1,2]

Move pointers

-------------------

Next

left = 0

right = 1

sum = 0

Triplet

[-1,0,1]

Answer

[[-1,-1,2],[-1,0,1]]

---

## Why Sort?

Sorting allows

left++

to increase the sum.

right--

to decrease the sum.

Without sorting,

Two Pointers cannot be used.

---

## Why Skip Duplicates?

Input

[-1,-1,-1,2]

Without skipping,

the same triplet would be added multiple times.

---

## Pointer Movement

sum < 0

↓

Need larger value

Move left

----------------

sum > 0

↓

Need smaller value

Move right

----------------

sum == 0

↓

Store answer

Skip duplicates

Move both

---

## Common Mistakes

❌ Forgetting to sort

❌ Not skipping duplicate i values

❌ Not skipping duplicate left/right values

❌ Using three nested loops

---

## Interview Question

Why is the complexity O(n²)?

Sorting

O(n log n)

Outer loop

O(n)

Inner Two Pointer traversal

O(n)

Overall

O(n²)

because O(n²) dominates O(n log n).


---

# Two Pointer Cheat Sheet

## Opposite Direction

Used when:

- Sorted array
- Pair sum
- Compare two ends

Problems

- Two Sum II
- Squares of Sorted Array
- 3Sum

---

## Fast & Slow Pointer

Used when:

- Remove elements
- Remove duplicates
- Move elements
- Compress arrays

Problems

- Remove Duplicates
- Remove Element
- Move Zeroes

---

## Remember

Sorted array

↓

Opposite Direction

------------------

Need filtering

↓

Fast & Slow Pointer

------------------

Need triplets

↓

Sort first

↓

Then Two Pointers