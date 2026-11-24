# LeetCode 3 – Longest Substring Without Repeating Characters

## 1. Problem Information

| Property | Details |
|---|---|
| LeetCode Number | 3 |
| Problem Name | Longest Substring Without Repeating Characters |
| Difficulty | Medium |
| Topic | Strings |
| Pattern | Variable Size Sliding Window |
| Data Structure | HashSet |
| Time Complexity | O(n) |
| Space Complexity | O(min(n, character set size)) |

**Problem statement:**

Given a string `s`, find the length of the longest substring without repeating characters.

### Examples

| Input | Output | Explanation |
|---|---:|---|
| `"abcabcbb"` | 3 | `"abc"` |
| `"bbbbb"` | 1 | `"b"` |
| `"pwwkew"` | 3 | `"wke"` |
| `"abba"` | 2 | `"ab"` or `"ba"` |
| `""` | 0 | Empty string |

---

## 2. Important Concepts

### Substring vs Subsequence

| Substring | Subsequence |
|---|---|
| Characters must be continuous | Characters need not be continuous |
| `"abc"` is a substring of `"abcde"` | `"ace"` is a subsequence of `"abcde"` |

This problem specifically asks for a substring.

### Sliding Window

A sliding window is a range of elements maintained using two pointers.

- `l`: Left pointer
- `r`: Right pointer
- Window: From index `l` to index `r`

Window length:

`r - l + 1`

### HashSet

A HashSet stores unique elements.

Important operations:

- `add()` – Adds an element.
- `contains()` – Checks whether an element exists.
- `remove()` – Removes an element.
- `size()` – Returns the number of elements.

In this problem, the HashSet ensures that the current window contains no repeated characters.

---

## 3. Approaches

### Approach 1: Brute Force

1. Generate all possible substrings.
2. Check each substring for duplicate characters.
3. Calculate the length of valid substrings.
4. Return the maximum length.

**Complexity:**
- Time: O(n³)
- Auxiliary Space: O(min(n, character set size)) for duplicate checking.

### Approach 2: Improved Brute Force

1. Select a starting index.
2. Expand the substring one character at a time.
3. Use a HashSet to detect duplicates.
4. Stop expanding when a duplicate appears.
5. Update the maximum length.

**Complexity:**
- Time: O(n²)
- Space: O(min(n, character set size))

### Approach 3: Optimal – Sliding Window + HashSet

Instead of generating every substring, maintain one valid window.

**Steps:**

1. Initialize a HashSet.
2. Set `l = 0` and `maxWind = 0`.
3. Move the right pointer through the string.
4. Check whether the current character already exists.
5. If duplicate exists:
   - Remove the character at the left pointer.
   - Increment the left pointer.
   - Continue until the duplicate is removed.
6. Add the current character.
7. Calculate `r - l + 1`.
8. Update `maxWind`.
9. Return `maxWind`.

**Complexity:**
- Time: O(n)
- Space: O(min(n, character set size))

---

## 4. Complete Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();

        int l = 0;
        int maxWind = 0;

        for (int r = 0; r < s.length(); r++) {

            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(s.charAt(r));

            int windowLength = r - l + 1;

            maxWind = Math.max(maxWind, windowLength);
        }

        return maxWind;
    }
}
```

---

## 5. Detailed Dry Run – Example 1

**Input:** `"abcabcbb"`

**Expected Output:** `3`

Initial values:

- `l = 0`
- `maxWind = 0`
- `set = {}`

| r | Character | Operation | Set | l | Window | Length | maxWind |
|---:|---|---|---|---:|---|---:|---:|
| 0 | a | Add a | {a} | 0 | a | 1 | 1 |
| 1 | b | Add b | {a,b} | 0 | ab | 2 | 2 |
| 2 | c | Add c | {a,b,c} | 0 | abc | 3 | 3 |
| 3 | a | Remove old a, add new a | {b,c,a} | 1 | bca | 3 | 3 |
| 4 | b | Remove old b, add new b | {c,a,b} | 2 | cab | 3 | 3 |
| 5 | c | Remove old c, add new c | {a,b,c} | 3 | abc | 3 | 3 |
| 6 | b | Remove a and b, add b | {c,b} | 5 | cb | 2 | 3 |
| 7 | b | Remove c and b, add b | {b} | 7 | b | 1 | 3 |

**Final Answer: 3**

Longest valid substrings include:
- `"abc"`
- `"bca"`
- `"cab"`

### Important: Duplicate handling at r = 6

Before processing index 6:

- Current window: `"abc"`
- `l = 3`
- Current character: `b`

The character `b` already exists.

**First removal:**
- Remove `s.charAt(3)` = `a`
- `l = 4`
- Set = `{b,c}`

The duplicate `b` still exists.

**Second removal:**
- Remove `s.charAt(4)` = `b`
- `l = 5`
- Set = `{c}`

Now the duplicate is removed.

Add the new `b`:
- Set = `{c,b}`
- Window = `"cb"`
- Length = 2

`maxWind` remains 3.

---

## 6. Dry Run – Example 2

**Input:** `"bbbbb"`

| r | Character | Operation | l | Set | maxWind |
|---:|---|---|---:|---|---:|
| 0 | b | Add b | 0 | {b} | 1 |
| 1 | b | Remove old b, add b | 1 | {b} | 1 |
| 2 | b | Remove old b, add b | 2 | {b} | 1 |
| 3 | b | Remove old b, add b | 3 | {b} | 1 |
| 4 | b | Remove old b, add b | 4 | {b} | 1 |

**Final Answer: 1**

---

## 7. Dry Run – Example 3

**Input:** `"pwwkew"`

| r | Character | Operation | l | Current Window | maxWind |
|---:|---|---|---:|---|---:|
| 0 | p | Add p | 0 | p | 1 |
| 1 | w | Add w | 0 | pw | 2 |
| 2 | w | Remove p and old w, add w | 2 | w | 2 |
| 3 | k | Add k | 2 | wk | 2 |
| 4 | e | Add e | 2 | wke | 3 |
| 5 | w | Remove old w, add new w | 3 | kew | 3 |

**Final Answer: 3**

---

## 8. Mistakes Made During Implementation

### Mistake 1: Incorrect contains() argument

Initially:

```java
set.contains(s.charAt(r) - 'a')
```

Problem:
- `s.charAt(r) - 'a'` produces an integer.
- The HashSet stores Character objects.

Correction:

```java
set.contains(s.charAt(r))
```

### Mistake 2: Using if instead of while

Initially, duplicate handling was attempted using `if`.

Problem:
- A single removal may not eliminate the duplicate.
- Multiple characters may need to be removed.

Correction:

```java
while (set.contains(s.charAt(r))) {
    set.remove(s.charAt(l));
    l++;
}
```

### Mistake 3: Removing the wrong character

Incorrect logic:


set.remove(s.charAt(r));
```

Problem:
- The current character is at the right pointer.
- The window must shrink from the left.

Correction:

```java
set.remove(s.charAt(l));
l++;
```

### Mistake 4: Forgetting to update the left pointer

Without `l++`, the window does not shrink correctly.

### Mistake 5: Returning set.size()

Problem:
- `set.size()` represents only the current window.
- It does not preserve the maximum window length encountered earlier.

Correction:

```java
int windowLength = r - l + 1;
maxWind = Math.max(maxWind, windowLength);

return maxWind;
```

---

## 9. Why Is the Time Complexity O(n)?

There is a `for` loop and a `while` loop, but the overall complexity is not O(n²).

Reason:

- The right pointer moves forward at most n times.
- The left pointer also moves forward at most n times.
- Neither pointer moves backwards.
- Every character is added to and removed from the set at most once.

Therefore, total operations are proportional to n.

**Time Complexity: O(n)**

**Space Complexity: O(min(n, character set size))**

---

## 10. Edge Cases

| Input | Expected Output | Reason |
|---|---:|---|
| `""` | 0 | Empty string |
| `"a"` | 1 | One character |
| `"aaaa"` | 1 | All characters repeat |
| `"abcdef"` | 6 | All characters are unique |
| `"abba"` | 2 | Duplicate occurs after a valid window |
| `"abcabcbb"` | 3 | Repeating pattern |
| `"pwwkew"` | 3 | Multiple valid windows |
| `"dvdf"` | 3 | Longest substring is `"vdf"` |

---

## 11. Interview Explanation

"I solve this problem using a variable-size sliding window and a HashSet. The HashSet maintains unique characters in the current window. I use two pointers, left and right. The right pointer expands the window by adding characters. Whenever a duplicate character is encountered, I shrink the window from the left by removing characters until the duplicate is eliminated. After adding the current character, I calculate the window length using right minus left plus one and update the maximum length. Since both pointers move only forward and each character is inserted and removed at most once, the time complexity is O(n), with O(min(n, character set size)) auxiliary space."

---

## 12. Revision Checklist

- [ ] Understand substring vs subsequence.
- [ ] Understand variable-size sliding window.
- [ ] Understand HashSet operations.
- [ ] Know why duplicate handling requires while.
- [ ] Know why the left pointer moves forward.
- [ ] Remember the window-length formula.
- [ ] Understand why maxWind is different from set.size().
- [ ] Explain O(n) time complexity.
- [ ] Solve `"abcabcbb"` without looking at notes.
- [ ] Solve `"abba"` without looking at notes.

---

## 13. Related LeetCode Problems

| LeetCode | Problem | Pattern |
|---|---|---|
| 3 | Longest Substring Without Repeating Characters | Variable Sliding Window + HashSet |
| 424 | Longest Repeating Character Replacement | Variable Sliding Window + Frequency Array |
| 438 | Find All Anagrams in a String | Fixed Sliding Window + Frequency |
| 567 | Permutation in String | Fixed Sliding Window + Frequency |
| 76 | Minimum Window Substring | Variable Sliding Window + HashMap |

**Key takeaway:** When a problem asks for the longest continuous substring satisfying a condition, consider variable-size sliding window.
