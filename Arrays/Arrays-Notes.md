# Arrays

## What is an Array?

An array is a linear data structure that stores elements of the same data type in contiguous memory locations.

---

# Time Complexities

| Operation | Complexity |
|-----------|------------|
| Access | O(1) |
| Search | O(n) |
| Insert | O(n) |
| Delete | O(n) |

---

# Common Patterns

## 1. Simple Traversal

```java
for (int i = 0; i < arr.length; i++) {

}
```

Use when every element needs to be visited.

---

## 2. Reverse Traversal

```java
for (int i = arr.length - 1; i >= 0; i--) {

}
```

Used for carry propagation, reversing, etc.

---

## 3. Two Pointer

```java
int left = 0;
int right = arr.length - 1;

while (left < right) {

}
```

Used for:
- Sorted arrays
- Reversing
- Removing duplicates
- Moving elements

---

## 4. Simulation

Perform exactly what the problem statement describes.

Examples:
- Plus One
- Duplicate Zeros

---

## Common Interview Questions

- What is the time complexity?
- Can this be done in-place?
- Can you reduce the space complexity?
- What are the edge cases?

---

# Common Mistakes

- Array Index Out Of Bounds
- Wrong loop condition
- Forgetting empty arrays
- Forgetting single-element arrays
- Ignoring integer overflow
- Modifying the original array when not allowed

---

# Problems Solved

| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 66 | Plus One | Easy | ✅ |

---

# Pattern Checklist

- [x] Traversal
- [x] Reverse Traversal
- [ ] Simulation (more practice)
- [ ] In-place Modification
- [ ] Rotation
- [ ] Matrix Problems

---

# Revision Tips

Before solving any array problem, ask yourself:

1. Can I solve it with a single traversal?
2. Do I need to traverse from the end?
3. Can I solve it in-place?
4. Do I need two pointers?
5. Is there an edge case such as:
   - Empty array
   - Single element
   - All zeros
   - All same values
   - Maximum/minimum values

---

# Notes

Keep updating this file whenever you learn a new array pattern or discover a useful trick.

---

# Problem 88 - Merge Sorted Array

## Key Observation

Since `nums1` has extra space at the end, if we merge from the beginning, elements may get overwritten.

Instead, merge from the end.

## Dry Run

nums1 = [1,2,3,0,0,0]

nums2 = [2,5,6]

Compare from the back:

3 vs 6 → 6

3 vs 5 → 5

3 vs 2 → 3

2 vs 2 → 2

2 → 2

1 → 1

Result:

[1,2,2,3,5,6]

---

## Pattern

Reverse Traversal

Two Pointers

In-place Modification

---

## Trick

Whenever a problem says:

- modify the original array
- extra space already exists

Think:

**Start filling from the back.**

---

## Common Mistakes

❌ Starting from index 0

❌ Forgetting remaining elements of nums2

❌ Creating another array unnecessarily

---

## Interview Question

Why do we start from the last index?

Because writing from the front overwrites values that haven't been compared yet.

Working from the back preserves all original elements until they are processed.
---

# Problem 414 - Third Maximum Number

## Key Observation

Sorting works but takes O(n log n).

The optimal solution maintains the top three distinct maximum values while traversing once.

---

## Pattern

Single Traversal

Tracking Maximum Values

Simulation

---

## Dry Run

Input:

[2,2,3,1]

first = 3

second = 2

third = 1

Answer = 1

---

Input:

[1,2]

Only two distinct values.

Answer = 2

---

## Trick

Whenever the question asks for:

- First Maximum
- Second Maximum
- Third Maximum
- K Maximum

Think about maintaining variables instead of sorting.

---

## Common Mistakes

❌ Counting duplicate values

❌ Sorting unnecessarily

❌ Forgetting the case where the third maximum doesn't exist

❌ Using int instead of Long

---

## Why Long?

The array may contain Integer.MIN_VALUE.

Using Long with null lets us distinguish:

- "No value assigned yet"
- A legitimate Integer.MIN_VALUE

---

## Interview Question

Can this be solved without sorting?

Yes.

Maintain three distinct maximum values in one traversal.

Time Complexity:

O(n)

Space Complexity:

O(1)
---

# Problem 485 - Max Consecutive Ones

## Key Observation

We only need to count consecutive 1's.

Whenever we encounter a 0, the current streak ends, so reset the counter.

---

## Pattern

- Linear Traversal
- Counting
- Running Maximum

---

## Dry Run

Input:

[1,1,0,1,1,1]

count = 1
max = 1

count = 2
max = 2

0 encountered

count = 0

count = 1

count = 2

count = 3

max = 3

Answer = 3

---

## Trick

Whenever the question asks:

- Longest consecutive...
- Maximum continuous...
- Longest streak...

Think:

✔ Count while condition is true

✔ Reset when condition breaks

✔ Keep track of maximum

---

## Common Mistakes

❌ Forgetting to reset count

❌ Updating max only after the loop

❌ Using nested loops

---

## Interview Question

Can this be solved in one traversal?

Yes.

Maintain:

- Current count
- Maximum count

Update both during traversal.

Time Complexity:

O(n)

Space Complexity:

O(1)
---

# Problem 1295 - Find Numbers with Even Number of Digits

## Key Observation

Instead of converting the number into a String, count digits mathematically.

Repeatedly divide the number by 10 until it becomes 0.

---

## Pattern

- Linear Traversal
- Mathematical Simulation
- Digit Counting

---

## Dry Run

Input:

[12,345,2,6,7896]

Digits:

12 → 2 ✅

345 → 3

2 → 1

6 → 1

7896 → 4 ✅

Answer = 2

---

## Trick

Whenever a problem asks:

- Number of digits
- Reverse a number
- Sum of digits
- Palindrome number

Think:

Use

temp /= 10

instead of converting to a String.

---

## Common Mistakes

❌ Using String conversion unnecessarily

❌ Forgetting to reset the digit counter

❌ Modifying the original number

---

## Interview Question

Can this be solved without converting the integer to a String?

Yes.

Count digits using repeated division by 10.

Time Complexity:

O(d)

Space Complexity:

O(1)
---

# Problem 1431 - Kids With the Greatest Number of Candies

## Key Observation

Every child is compared against the same maximum value.

Instead of finding the maximum repeatedly for every child, compute it once.

---

## Pattern

- Two Traversals
- Global Maximum
- Simulation

---

## Dry Run

Input:

candies = [2,3,5,1,3]

extraCandies = 3

Maximum = 5

2 + 3 = 5 ✅

3 + 3 = 6 ✅

5 + 3 = 8 ✅

1 + 3 = 4 ❌

3 + 3 = 6 ✅

Answer:

[true,true,true,false,true]

---

## Trick

Whenever every element needs to be compared with:

- largest value
- smallest value
- total sum
- average

Think:

First traversal

↓

Compute global information

↓

Second traversal

---

## Common Mistakes

❌ Finding maximum inside the second loop

❌ Modifying the original array

❌ Forgetting that the answer is a List<Boolean>

---

## Interview Question

Why do we use two traversals?

If we calculate the maximum inside the second loop, the complexity becomes O(n²).

Computing the maximum once keeps the solution O(n).

---

# Problem 1672 - Richest Customer Wealth

## Key Observation

Each row represents one customer.

Each column represents one bank account.

We need to calculate the sum of each row and return the maximum sum.

---

## Pattern

- 2D Array Traversal
- Row Sum
- Running Maximum

---

## Dry Run

Input:

[[1,2,3],
 [3,2,1]]

Customer 1

1 + 2 + 3 = 6

Customer 2

3 + 2 + 1 = 6

Answer = 6

---

## Trick

Whenever the problem says:

Each row represents...

Think:

Outer loop → rows

Inner loop → columns

---

## Template

for each row

    sum = 0

    for each column

        sum += value

    update answer

---

## Common Mistakes

❌ Forgetting to reset the row sum

❌ Using accounts.length for the inner loop

❌ Confusing rows and columns

---

## Interview Question

Why do we reset currentWealth after every row?

Because each row belongs to a different customer.

Each customer's wealth must be calculated independently.

Time Complexity:

O(m × n)

Space Complexity:

O(1)

---

# Problem 1920 - Build Array from Permutation

## Key Observation

The value stored in the array is used as another index.

Instead of:

ans[i] = nums[i]

we use:

ans[i] = nums[nums[i]]

---

## Pattern

- Index Mapping
- Simulation
- One Pass Traversal

---

## Dry Run

Input:

nums = [0,2,1,5,3,4]

i = 0

ans[0] = nums[0] = 0

i = 1

ans[1] = nums[2] = 1

i = 2

ans[2] = nums[1] = 2

i = 3

ans[3] = nums[5] = 4

i = 4

ans[4] = nums[3] = 5

i = 5

ans[5] = nums[4] = 3

Output:

[0,1,2,4,5,3]

---

## Trick

Whenever you see:

nums[ nums[i] ]

Think:

Value → becomes Index

Index → fetch another value

---

## Visualization

Index

0 1 2 3 4 5

↓

nums

0 2 1 5 3 4

↓

Answer

0 1 2 4 5 3

---

## Common Mistakes

❌ Writing

ans[i] = nums[i]

instead of

ans[i] = nums[nums[i]]

❌ Modifying nums directly

❌ Confusing index and value

---

## Interview Question

Why do we need another array?

Because changing nums while reading from it would affect future lookups.

Time Complexity:

O(n)

Space Complexity:

O(n)
---

# Problem 1929 - Concatenation of Array

## Key Observation

The answer array contains two copies of the original array.

Instead of using two loops, we can fill both positions in a single traversal.

---

## Pattern

- Array Construction
- Index Offset
- Simulation

---

## Dry Run

Input:

nums = [1,2,3]

n = 3

Iteration:

i = 0

ans[0] = 1

ans[3] = 1

i = 1

ans[1] = 2

ans[4] = 2

i = 2

ans[2] = 3

ans[5] = 3

Output

[1,2,3,1,2,3]

---

## Trick

Whenever a problem asks to:

- Duplicate an array
- Repeat elements
- Build another array

Think:

Create a new array and use index offsets.

Example:

ans[i + n]

---

## Formula

First copy

ans[i]

Second copy

ans[i + n]

---

## Common Mistakes

❌ Creating an array of size n

❌ Forgetting i + n

❌ Using two unnecessary loops

---

## Interview Question

Why is only one loop enough?

Because each iteration fills two positions in the answer array.

Time Complexity:

O(n)

Space Complexity:

O(n)
---

# Problem 3925 - Concatenate Array With Reverse

## Key Observation

The answer consists of:

- Original array
- Reverse of the same array

No changes are made to the original array.

---

## Pattern

- Array Construction
- Reverse Traversal
- Index Mapping

---

## Dry Run

Input

nums = [1,2,3]

Output array size = 6

First Half

[1,2,3]

Second Half

[3,2,1]

Final

[1,2,3,3,2,1]

---

## Reverse Index Formula

nums[n - 1 - i]

Example

n = 5

i = 0 → index 4

i = 1 → index 3

i = 2 → index 2

...

---

## Trick

Whenever a problem asks:

- Reverse copy
- Reverse order
- Mirror array

Think:

last index = n - 1 - i

---

## Common Mistakes

❌ Using nums[i] instead of nums[n - 1 - i]

❌ Wrong answer array size

❌ Forgetting to append after the original array

---

## Interview Question

Why not reverse the original array?

Because the problem requires preserving the original order in the first half while adding the reversed version to the second half.

Time Complexity:

O(n)

Space Complexity:

O(n)
---

# Problem 4000 - Largest Integer With Given Digit Sum

## Key Observation

To obtain the largest possible number, always place the largest digit
at the leftmost available position.

The maximum value of a single digit is 9.

---

## Pattern

- Greedy
- Mathematical Simulation

---

## Dry Run

Input

n = 3

s = 20

Digit 1

min(9,20)=9

Remaining =11

Digit 2

min(9,11)=9

Remaining =2

Digit 3

min(9,2)=2

Answer

992

---

## Greedy Choice

Always choose

min(9, remainingSum)

because the leftmost digit contributes the most to the final number.

---

## Impossible Case

Maximum possible digit sum

9 × n

If

s > 9 × n

Answer = -1

---

## Common Mistakes

❌ Forgetting the impossible condition

❌ Filling digits from the right

❌ Not reducing the remaining sum

---

## Interview Question

Why is the greedy approach correct?

The leftmost digit has the highest place value.

Choosing the maximum possible digit at every step always produces the
largest integer.

Time Complexity

O(n)

Space Complexity

O(1)