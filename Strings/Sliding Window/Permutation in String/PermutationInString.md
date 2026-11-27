
# LeetCode 567 – Permutation in String

## 1. Problem Information

- **LeetCode Number:** 567
- **Problem:** Permutation in String
- **Link:** https://leetcode.com/problems/permutation-in-string/
- **Difficulty:** Medium
- **Topic:** Strings
- **DSA Pattern:** Fixed-Size Sliding Window + Frequency Counting
- **Language:** Java

## 2. Problem Statement

You are given two strings, `s1` and `s2`.

Return `true` if `s2` contains a substring that is a permutation of `s1`.

Otherwise, return `false`.

A permutation means rearranging the characters without changing their frequencies.

The substring must be continuous.

### Example 1

**Input:**
```java
s1 = "ab"
s2 = "eidbaooo"
```

**Output:**
```java
true
```

**Explanation:**

The substring `"ba"` is a permutation of `"ab"`.

### Example 2

**Input:**
```java
s1 = "ab"
s2 = "eidboaoo"
```

**Output:**
```java
false
```

### Example 3

**Input:**
```java
s1 = "adc"
s2 = "dcda"
```

**Output:**
```java
true
```

The substring `"cda"` is a permutation of `"adc"`.

---

## 3. Approach 1 – Brute Force

### Dry Run

**Input:**
```java
s1 = "ab"
s2 = "eidba"
```

Sorted `s1`:
```text
ab
```

Window size = 2.

| Iteration | Window | Sorted Window | Sorted s1 | Match |
|---|---|---|---|---|
| 1 | ei | ei | ab | No |
| 2 | id | di | ab | No |
| 3 | db | bd | ab | No |
| 4 | ba | ab | ab | Yes |

**Output:**
```java
true
```

### Time Complexity

Let:
- `n` = length of s1
- `m` = length of s2

Number of windows:
```text
m - n + 1
```

For every window:
- Creating the substring takes O(n).
- Converting it to a character array takes O(n).
- Sorting the character array takes O(n log n).
- Comparing sorted arrays takes O(n).

Sorting dominates the work.

Therefore:

```text
O((m - n + 1) * n log n)
```

### Auxiliary Space Complexity

- Target character array: O(n)
- Current window character array: O(n)
- Sorting may use additional temporary space.

Overall:

```text
O(n)
```

---

## 4. Approach 2 – Sliding Window: Initial Window Method

### Input

```java
s1 = "ab"
s2 = "eidbaooo"
```

### Initial Values

```text
n = 2
m = 8
```

Required frequency array:

```text
freq:
a = 1
b = 1
all other characters = 0
```

The first window contains the first two characters of s2.

```text
s2 = e i d b a o o o
     ↑ ↑
```

Current window:

```text
ei
```

Window frequency:

```text
windowFreq:
e = 1
i = 1
all other characters = 0
```

The arrays do not match.

### Dry Run – Every Window

| Iteration | Outgoing | Incoming | Current Window | Match |
|---|---|---|---|---|
| Initial | — | — | ei | No |
| 1 | e | d | id | No |
| 2 | i | b | db | No |
| 3 | d | a | ba | Yes |

### Detailed Pointer and Frequency Transitions

**Initial window: `"ei"`**

```text
windowFreq:
e = 1
i = 1
```

**Move to `"id"`**

Remove:
```java
windowFreq[s2.charAt(0) - 'a']--;
```

Character removed: `e`

Add:
```java
windowFreq[s2.charAt(2) - 'a']++;
```

Character added: `d`

New frequency:
```text
i = 1
d = 1
```

**Move to `"db"`**

Remove:
```text
i
```

Add:
```text
b
```

New frequency:
```text
d = 1
b = 1
```

**Move to `"ba"`**

Remove:
```text
d
```

Add:
```text
a
```

New frequency:
```text
b = 1
a = 1
```

Now:

```text
freq == windowFreq
```

Both arrays match.

**Output:**
```java
true
```

### Time Complexity

- Counting s1: O(n)
- Creating the first window: O(n)
- Number of window movements: O(m - n)
- Each movement updates two array positions: O(1)
- Each `Arrays.equals()` comparison checks 26 elements: O(26)

Total:

```text
O(n + n + (m - n) * 26)
```

Since 26 is constant:

```text
O(m)
```

### Auxiliary Space Complexity

Two frequency arrays:

```java
int[] freq = new int[26];
int[] windowFreq = new int[26];
```

Total:

```text
O(26 + 26) = O(1)
```

---

## 5. Approach 3 – Sliding Window: Two Pointer Method

### Input

```java
s1 = "ab"
s2 = "eidbaooo"
```

### Initial Values

```text
n = 2
m = 8

l = 0
r = 0
```

Required frequency:

```text
freq:
a = 1
b = 1
```

Initially, the window is empty.

### Dry Run – Every Iteration

The right pointer adds a character to the window.

When the window size exceeds 2:
- Remove the character at `l`.
- Increase `l` by 1.

| Iteration | l | r | Added | Removed | Window | Match |
|---|---:|---:|---|---|---|---|
| 1 | 0 | 0 | e | — | e | Not checked |
| 2 | 0 | 1 | i | — | ei | No |
| 3 | 1 | 2 | d | e | id | No |
| 4 | 2 | 3 | b | i | db | No |
| 5 | 3 | 4 | a | d | ba | Yes |

### Detailed Variable Transitions

**Iteration 1**

```text
l = 0
r = 0
```

Add `s2.charAt(0)`:

```text
e
```

Window size:

```text
r - l + 1 = 1
```

No comparison because the window size is not 2.

Increase `r`:

```text
r = 1
```

**Iteration 2**

```text
l = 0
r = 1
```

Add:

```text
i
```

Window:

```text
ei
```

Window size:

```text
1 - 0 + 1 = 2
```

Compare frequencies.

```text
freq       = a:1, b:1
freq1      = e:1, i:1
```

No match.

Increase `r`:

```text
r = 2
```

**Iteration 3**

```text
l = 0
r = 2
```

Add:

```text
d
```

Temporary window:

```text
eid
```

Window size:

```text
2 - 0 + 1 = 3
```

Since the size exceeds 2:

Remove character at `l = 0`:

```text
e
```

Increase left pointer:

```text
l = 1
```

Current window:

```text
id
```

Window size:

```text
2 - 1 + 1 = 2
```

Compare frequencies.

No match.

Increase `r`:

```text
r = 3
```

**Iteration 4**

```text
l = 1
r = 3
```

Add:

```text
b
```

Temporary window:

```text
idb
```

Window size = 3.

Remove character at `l = 1`:

```text
i
```

Increase `l`:

```text
l = 2
```

Current window:

```text
db
```

Compare frequencies.

No match.

Increase `r`:

```text
r = 4
```

**Iteration 5**

```text
l = 2
r = 4
```

Add:

```text
a
```

Temporary window:

```text
dba
```

Window size = 3.

Remove character at `l = 2`:

```text
d
```

Increase `l`:

```text
l = 3
```

Current window:

```text
ba
```

Window size:

```text
4 - 3 + 1 = 2
```

Frequency arrays:

```text
freq:
a = 1
b = 1

freq1:
a = 1
b = 1
```

Both arrays match.

**Output:**
```java
true
```

### Time Complexity

- Counting s1: O(n)
- Right pointer traverses s2: O(m)
- Left pointer moves at most O(m) times.
- Each frequency update: O(1)
- Each frequency comparison: O(26)

Total:

```text
O(n + 26m)
```

Since 26 is constant:

```text
O(m)
```

### Auxiliary Space Complexity

Two arrays of fixed size 26:

```text
O(26 + 26) = O(1)
```

---

## 6. Brute Force vs Sliding Window

| Factor | Brute Force | Initial Window | Two Pointers |
|---|---|---|---|
| Technique | Sorting | Fixed sliding window | Fixed sliding window |
| Frequency arrays | No | Two | Two |
| Sorting | Yes | No | No |
| Window movement | Generate each window | Remove and add | Left and right pointers |
| Time | O((m-n+1) × n log n) | O(m) | O(m) |
| Auxiliary Space | O(n) | O(1) | O(1) |

---

## 7. Edge Cases

| s1 | s2 | Expected Output |
|---|---|---|
| `"ab"` | `"eidbaooo"` | true |
| `"ab"` | `"eidboaoo"` | false |
| `"a"` | `"a"` | true |
| `"a"` | `"b"` | false |
| `"abc"` | `"ab"` | false |
| `"aab"` | `"eidbaaoo"` | true |
| `"abc"` | `"ccccbbbbaaaa"` | false |

---

## 8. Complexity Comparison

| Approach | Time Complexity | Auxiliary Space |
|---|---|---|
| Brute Force | O((m-n+1) × n log n) | O(n) |
| Initial Window | O(m) | O(1) |
| Two Pointers | O(m) | O(1) |

### Why O(m) for both sliding-window approaches?

The alphabet contains only 26 lowercase English letters.

Every frequency comparison takes O(26), which is constant.

The window traverses s2 only once.

Therefore:

```text
O(26 * m) = O(m)
```

### Why O(1) auxiliary space?

Both sliding-window implementations use two arrays, each containing 26 integers.

Their sizes never increase with the input strings.

Therefore:

```text
O(26 + 26) = O(1)
```

The brute-force approach creates character arrays proportional to the window length, so its auxiliary space is O(n).

---

## 9. Final Revision Checklist

- [ ] I understand the brute-force sorting approach.
- [ ] I can dry run every substring in brute force.
- [ ] I understand why two frequency arrays are required.
- [ ] I can explain the initial window method.
- [ ] I understand the outgoing and incoming characters.
- [ ] I can dry run the two-pointer method.
- [ ] I understand why the window size stays fixed.
- [ ] I can explain O(m) time complexity.
- [ ] I can explain O(1) auxiliary space.
- [ ] I can write both sliding-window implementations independently.
- [ ] I can explain the solution in an interview.

**Revision Schedule:**
- First revision: After 3 days
- Second revision: After 7 days
- Third revision: After 30 days
