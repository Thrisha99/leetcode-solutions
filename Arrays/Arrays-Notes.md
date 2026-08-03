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