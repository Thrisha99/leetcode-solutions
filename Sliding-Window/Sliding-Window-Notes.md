# Sliding Window

## What is Sliding Window?

Sliding Window is an optimization technique used when we need to process
contiguous subarrays or substrings.

Instead of recalculating every window from scratch, we reuse the previous
window by removing one element and adding another.

This reduces many O(n²) solutions to O(n).

---

# When Should You Think of Sliding Window?

Look for words like:

- Subarray
- Substring
- Contiguous
- Consecutive
- Window
- Maximum
- Minimum
- Average
- Sum

If the problem involves a contiguous range,
Sliding Window is often the correct approach.

---

# Types of Sliding Window

## 1. Fixed Size Window

Window size never changes.

Examples

- Maximum Average Subarray I
- Number of Subarrays of Size K

Template

```java
int sum = 0;

for (int i = 0; i < k; i++) {
    sum += nums[i];
}

for (int i = k; i < nums.length; i++) {
    sum += nums[i];
    sum -= nums[i - k];
}
```

---

## 2. Variable Size Window

Window expands and shrinks.

Examples

- Minimum Size Subarray Sum
- Longest Substring Without Repeating Characters

Template

```java
int left = 0;

for (int right = 0; right < nums.length; right++) {

    while (condition) {

        left++;

    }

}
---

# Variable Sliding Window Patterns

## Minimum Window

Expand

↓

Condition becomes true

↓

Shrink

↓

Find minimum

Example

- 209. Minimum Size Subarray Sum

---

## Maximum Window

Expand

↓

Constraint is satisfied

↓

Shrink only when the constraint breaks

↓

Find maximum

Examples

- 1004. Max Consecutive Ones III
- 424. Longest Repeating Character Replacement
- 904. Fruit Into Baskets
```

---

# Fixed vs Variable

Fixed Window

- Size is given.
- Move both pointers together.

Variable Window

- Size changes dynamically.
- Expand using right.
- Shrink using left.

---

# Time Complexity

Most Sliding Window problems

O(n)

---

# Space Complexity

Usually

O(1)

unless extra data structures are required.

---

# Common Mistakes

❌ Forgetting to remove the left element

❌ Shrinking before processing

❌ Using nested loops

❌ Wrong window size

❌ Off-by-one errors

---

# Pattern Recognition

Need a contiguous subarray?

↓

YES

↓

Window size fixed?

↓

YES → Fixed Sliding Window

↓

NO

↓

Variable Sliding Window

---

# Problems Solved

| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 643 | Maximum Average Subarray I | Easy | ✅ |
| 1343 | Number of Subarrays of Size K and Average ≥ Threshold | Medium | ✅ |
| 209 | Minimum Size Subarray Sum | Medium | ✅ |

---

# Revision Checklist

## Fixed Window

- [x] Maximum Average Subarray I
- [x] Number of Subarrays of Size K

---

## Variable Window

- [x] Minimum Size Subarray Sum
- [ ] Longest Repeating Character Replacement
- [ ] Longest Substring Without Repeating Characters

---

# Problem 643 - Maximum Average Subarray I

## Actual Pattern

Fixed Size Sliding Window

---

## Key Observation

The window size is fixed.

Instead of calculating every window separately,

reuse the previous window.

New Window Sum

=

Previous Window Sum

+ New Element

- Removed Element

---

## Pattern

Fixed Window

Window Size = k

---

## Dry Run

Input

nums = [1,12,-5,-6,50,3]

k = 4

First Window

1 + 12 + (-5) + (-6)

= 2

Next Window

2

+ 50

- 1

= 51

Next Window

51

+ 3

- 12

= 42

Maximum Sum

51

Average

51 / 4 = 12.75

---

## Window Update Formula

sum += nums[i]

sum -= nums[i-k]

---

## Trick

Whenever the window size is given,

don't recalculate the sum.

Update it.

---

## Common Mistakes

❌ Recalculating every window

O(n × k)

❌ Forgetting to remove the outgoing element

❌ Integer division while returning the answer

---

## Interview Question

Why is Sliding Window better?

Brute Force

O(n × k)

Sliding Window

O(n)

because every element is added and removed at most once.

// First Window
for (int i = 0; i < k; i++) {
    sum += nums[i];
}

// Slide Window
for (int i = k; i < nums.length; i++) {

    sum += nums[i];
    sum -= nums[i - k];

}
Pattern recognization:

Does the problem mention a contiguous subarray?

        │
       YES
        │
Is the window size fixed (k)?

        │
       YES
        │
Use Fixed Sliding Window

---

# Problem 1343 - Number of Subarrays of Size K and Average ≥ Threshold

## Actual Pattern

Fixed Size Sliding Window

---

## Key Observation

Average

=

Sum / k

Instead of dividing every time,

compare

Sum

with

k × threshold

This avoids repeated division.

---

## Pattern

Fixed Window

Window Size = k

---

## Dry Run

Input

arr = [2,2,2,2,5,5,5,8]

k = 3

threshold = 4

Need

sum ≥ 12

Window

2+2+2 = 6

No

↓

2+2+2 = 6

No

↓

2+2+5 = 9

No

↓

2+5+5 = 12

Yes

↓

5+5+5 = 15

Yes

↓

5+5+8 = 18

Yes

Answer

3

---

## Window Update

sum += arr[i]

sum -= arr[i-k]

---

## Trick

Need

Average ≥ threshold

Don't divide.

Compare

sum ≥ k × threshold

---

## Common Mistakes

❌ Dividing every window

❌ Forgetting the first window

❌ Forgetting to remove the outgoing element

---

## Interview Question

Why compare

sum ≥ k × threshold

instead of

(sum / k) ≥ threshold?

It avoids unnecessary division and is slightly more efficient.

Time Complexity

O(n)

Space Complexity

O(1)

---

# Fixed Sliding Window Comparison

## 643

Goal

Maximum Sum

↓

Return Maximum Average

---

## 1343

Goal

Count Windows

↓

Return Number of Valid Windows

---

Both use exactly the same window movement.

Only the processing of each window changes.     

Window size given?

        │
       YES
        │
Need Maximum?

        │
       YES → 643

--------------------

Need Count?

        │
       YES → 1343

       ---

# Problem 209 - Minimum Size Subarray Sum

## Actual Pattern

Variable Size Sliding Window

---

## Key Observation

The window size is NOT fixed.

Expand the window until the condition becomes true.

Once it becomes true,

shrink the window while keeping the condition true.

This helps us find the minimum window.

---

## Pattern

Expand

↓

Condition becomes true

↓

Shrink

↓

Repeat

---

## Dry Run

Input

target = 7

nums = [2,3,1,2,4,3]

Window

2

sum = 2

Expand

↓

2,3

sum = 5

Expand

↓

2,3,1

sum = 6

Expand

↓

2,3,1,2

sum = 8

Condition satisfied

Length = 4

Shrink

↓

3,1,2

sum = 6

Expand

↓

3,1,2,4

sum = 10

Length = 4

Shrink

↓

1,2,4

sum = 7

Length = 3

Shrink

↓

2,4

sum = 6

Expand

↓

2,4,3

sum = 9

Length = 3

Shrink

↓

4,3

sum = 7

Length = 2 ✅

Answer = 2

---

## Window Movement

sum < target

↓

Expand

(right++)

---------------------

sum >= target

↓

Shrink

(left++)

---

## Trick

Need

Minimum

Shortest

Smallest

contiguous subarray

↓

Think Variable Sliding Window

---

## Common Mistakes

❌ Using if instead of while

❌ Forgetting to shrink repeatedly

❌ Returning Integer.MAX_VALUE

instead of 0

---

## Interview Question

Why does Variable Sliding Window work?

Because all numbers are positive.

Adding elements always increases the sum.

Removing elements always decreases the sum.

This property makes the window movement valid.

Time Complexity

O(n)

Space Complexity

O(1)

---

# Fixed vs Variable Sliding Window

## Fixed Window

Window size is known.

Move both pointers together.

Examples

- 643
- 1343

------------------------

## Variable Window

Window size changes.

Expand

↓

Condition becomes true

↓

Shrink

↓

Repeat

Examples

- 209
- Longest Substring Without Repeating Characters
- Minimum Window Substring

---

# Problem 1004 - Max Consecutive Ones III

## Actual Pattern

Variable Size Sliding Window

---

## Key Observation

We can flip at most **k** zeroes.

Instead of actually flipping them,

maintain a window that contains at most **k** zeroes.

If the number of zeroes becomes greater than **k**,

the window becomes invalid.

Shrink it until it becomes valid again.

The largest valid window is the answer.

---

## Pattern

Expand

↓

Count zeroes

↓

Zeroes > k ?

↓

YES

↓

Shrink

↓

Repeat

---

## Dry Run

Input

nums = [1,1,1,0,0,0,1,1,1,1,0]

k = 2

Window

[1,1,1]

Zero Count = 0

Expand

↓

[1,1,1,0]

Zero Count = 1

Expand

↓

[1,1,1,0,0]

Zero Count = 2

Valid

Expand

↓

[1,1,1,0,0,0]

Zero Count = 3

Invalid

Shrink until

Zero Count = 2

Continue

Update maximum window length whenever the window is valid.

---

## Window Movement

Zero Count ≤ k

↓

Expand

(right++)

----------------------

Zero Count > k

↓

Shrink

(left++)

Decrease zero count when a zero leaves the window.

---

## Trick

Whenever the problem says:

- At most k
- Can change at most k elements
- Flip at most k zeroes
- Replace at most k characters

Think:

Variable Sliding Window

Maintain the count of invalid elements.

---

## Common Mistakes

❌ Actually flipping the zeroes

❌ Forgetting to decrease zeroCount while shrinking

❌ Using if instead of while

❌ Updating the answer before making the window valid

---

## Interview Question

Why do we shrink only when zeroCount > k?

Because the window is valid as long as it contains at most **k** zeroes.

We only need to shrink when the constraint is violated.

Time Complexity

O(n)

Space Complexity

O(1)

---

# Variable Sliding Window Comparison

## 209. Minimum Size Subarray Sum

Goal

Find the **smallest** valid window.

Condition

sum ≥ target

Shrink immediately after the condition becomes true.

---

## 1004. Max Consecutive Ones III

Goal

Find the **largest** valid window.

Condition

zeroCount ≤ k

Shrink only when the condition becomes false.

---

### Memory Trick

Need the **minimum** window?

➡️ Shrink as soon as the condition is satisfied.

Need the **maximum** window?

➡️ Keep expanding while the window is valid.