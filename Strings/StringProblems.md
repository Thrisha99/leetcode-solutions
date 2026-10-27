# 1. LeetCode 1832 — Check if the Sentence Is Pangram

**Difficulty:** Easy  
**Topics:** Strings, Arrays, Hashing, HashSet  
**Language:** Java

## 1. Problem Statement

A pangram is a sentence in which every letter of the English alphabet appears at least once.

Given a string `sentence` containing only lowercase English letters, return `true` if it is a pangram; otherwise, return `false`.

### Examples

**Example 1**

Input:
```text
thequickbrownfoxjumpsoverthelazydog
```

Output:
```text
true
```

Explanation: Every letter from `a` to `z` appears at least once.

**Example 2**

Input:
```text
leetcode
```

Output:
```text
false
```

Explanation: Several letters of the alphabet are missing.

---

## 2. Core Observation

There are exactly 26 lowercase English letters:

```text
a b c d e f g h i j k l m
n o p q r s t u v w x y z
```

We need to determine whether all 26 letters occur at least once.

Repeated letters do not matter.

For example:

```text
sentence = "aabbcc"
```

The distinct letters are `a`, `b`, and `c`. Since the other letters are missing, the sentence is not a pangram.

**Key idea:** Track which letters have appeared, or count the distinct letters.

---

## 3. Approach 1 — Boolean Array

### Code

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] seen = new boolean[26];
        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            int idx = c - 'a';

            if (!seen[idx]) {
                seen[idx] = true;
                count++;

                if (count == 26) {
                    return true;
                }
            }
        }

        return false;
    }
}
```

### Step 1: Create the array

```java
boolean[] seen = new boolean[26];
```

Each index represents a letter.

| Letter | Index |
|---|---:|
| `a` | 0 |
| `b` | 1 |
| `c` | 2 |
| `d` | 3 |
| ... | ... |
| `z` | 25 |

Initially, all array elements are `false`.

- `false`: the letter has not been seen.
- `true`: the letter has already been seen.

### Step 2: Convert a character into an index

```java
int idx = c - 'a';
```

Java stores characters using numeric values.

For lowercase English letters:

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
...
'z' - 'a' = 25
```

For example, if `c = 'd'`:

```java
int idx = 'd' - 'a';  // 3
```

Therefore, `seen[3]` represents the letter `d`.

### Step 3: Count only new letters

```java
if (!seen[idx]) {
    seen[idx] = true;
    count++;
}
```

The `!` operator means NOT.

If a letter has not been seen before, mark it and increment `count`.

If it appears again, do not increment the count.

### Step 4: Check the count

```java
if (count == 26) {
    return true;
}
```

When the count reaches 26, every alphabet letter has appeared.

If the loop finishes without reaching 26, return `false`.

### Dry Run

Input:

```text
abcabc
```

| Character | Index | Previously seen? | Count |
|---|---:|---|---:|
| `a` | 0 | No | 1 |
| `b` | 1 | No | 2 |
| `c` | 2 | No | 3 |
| `a` | 0 | Yes | 3 |
| `b` | 1 | Yes | 3 |
| `c` | 2 | Yes | 3 |

Final count is 3, so the result is `false`.

### Complexity

- **Time:** `O(n)` — each character is processed at most once.
- **Auxiliary space:** `O(1)` — the array always contains 26 elements.

---

## 4. Approach 2 — HashSet with Enhanced For Loop

### Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            set.add(c);
        }

        return set.size() == 26;
    }
}
```

### How it works

A `HashSet` stores distinct elements. It automatically ignores duplicate insertions.

```java
set.add(c);
```

If the character is already present, the set does not create another copy.

For example:

```text
Input:  "aabcc"
Set:    [a, b, c]
Size:   3
```

Because the sentence contains only lowercase English letters, a set size of 26 means every alphabet letter is present.

### Important methods

- `sentence.toCharArray()` converts the string into a character array.
- `set.add(c)` inserts a character into the set.
- `set.size()` returns the number of distinct characters.

### Complexity

- **Time:** `O(n)` expected.
- **Auxiliary space:** `O(1)` for the set, bounded by 26 possible letters.

---

## 5. Approach 3 — HashSet with Indexed For Loop

### Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < sentence.length(); i++) {
            set.add(sentence.charAt(i));
        }

        return set.size() == 26;
    }
}
```

This approach uses the same logic as Approach 2.

The only difference is how the characters are accessed.

Enhanced for loop:

```java
for (char c : sentence.toCharArray())
```

Indexed for loop:

```java
for (int i = 0; i < sentence.length(); i++) {
    set.add(sentence.charAt(i));
}
```

The indexed version avoids explicitly creating a character array with `toCharArray()`.

Both approaches have expected `O(n)` time complexity.

---

## 6. Approach 4 — Using String.contains()

### Code

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) {
            return false;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (!sentence.contains(String.valueOf(c))) {
                return false;
            }
        }

        return true;
    }
}
```

### How it works

Instead of tracking letters while scanning the sentence, this approach asks 26 separate questions:

- Does the sentence contain `a`?
- Does the sentence contain `b`?
- Does the sentence contain `c`?
- ...
- Does the sentence contain `z`?

If even one letter is missing, return `false`.

### Important methods

```java
String.valueOf(c)
```

Converts a character into a string because `String.contains()` expects a `CharSequence`, not a primitive `char`.

```java
sentence.contains("a")
```

Returns `true` if the sentence contains `"a"`.

### Why check the length first?

```java
if (sentence.length() < 26) {
    return false;
}
```

A string shorter than 26 characters cannot contain 26 distinct letters.

However, having 26 or more characters does not guarantee that the sentence is a pangram.

### Complexity

Let `n` be the sentence length.

- **Time:** `O(n)` for this fixed 26-letter alphabet, because the string may be scanned up to 26 times.
- **Auxiliary space:** `O(1)`.

Although asymptotically linear for a fixed alphabet, this approach can repeatedly scan the string.

---

## 7. Approach 5 — Nested Loops

### Code

```java
class Solution {
    public boolean checkIfPangram(String s) {
        int n = s.length();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (ch == s.charAt(i)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }
}
```

### How it works

The outer loop selects one alphabet letter at a time.

The inner loop scans the sentence to find that letter.

For example, the outer loop first selects `a`. The inner loop checks every character until it finds `a` or reaches the end.

If `a` is missing, the method immediately returns `false`.

If it finds `a`, the outer loop moves to `b` and repeats the search.

### Important concepts

- `found` records whether the current letter exists.
- `break` stops the inner loop as soon as a match is found.
- `!found` means the letter is missing.

### Complexity

- **Time:** `O(26 × n)`, which simplifies to `O(n)` because 26 is constant.
- **Auxiliary space:** `O(1)`.

This approach is straightforward, but it may scan the sentence many times.

---

## 8. Approach 6 — HashSet Initialized with All Letters

### Code

```java
import java.util.HashSet;

class Solution {
    public boolean checkIfPangram(String sentence) {
        HashSet<Character> alpha = new HashSet<>();

        for (char c = 'a'; c <= 'z'; c++) {
            alpha.add(c);
        }

        for (int i = 0; i < sentence.length(); i++) {
            alpha.remove(sentence.charAt(i));

            if (alpha.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
```

### How it works

This approach starts with all 26 letters in the set.

Whenever a character occurs in the sentence, it removes that letter from the set.

Initially:

```text
{a, b, c, d, ..., z}
```

After processing `a`, the set no longer contains `a`.

After processing `b`, it no longer contains `b`.

Once the set becomes empty, all letters have appeared, so the method returns `true`.

### Important methods

- `alpha.add(c)` adds a letter.
- `alpha.remove(c)` removes a letter if present.
- `alpha.isEmpty()` checks whether all required letters have been removed.

### Complexity

- **Time:** `O(n)` expected.
- **Auxiliary space:** `O(1)` because the set contains at most 26 letters.

---

## 9. Comparison of All Approaches

| Approach | Main data structure | Time | Auxiliary space |
|---|---|---|---|
| Boolean array | `boolean[26]` | `O(n)` | `O(1)` |
| HashSet, enhanced loop | `HashSet` | `O(n)` expected | `O(1)` |
| HashSet, indexed loop | `HashSet` | `O(n)` expected | `O(1)` |
| `String.contains()` | String search | `O(n)` for fixed alphabet | `O(1)` |
| Nested loops | Variables and loops | `O(n)` for fixed alphabet | `O(1)` |
| HashSet initialized with alphabet | `HashSet` | `O(n)` expected | `O(1)` |

**Recommendation:** Use the boolean array for this problem. The alphabet size is fixed, indexing is direct, and you can return early when all 26 letters are found.

The HashSet approach is also useful practice for problems involving distinct characters.

---

## 10. Common Mistakes

### Mistake 1: Counting every character

Incorrect:

```java
count++;
```

for every character.

This counts duplicates, so it does not tell us how many distinct letters appeared.

Correct:

```java
if (!seen[idx]) {
    seen[idx] = true;
    count++;
}
```

### Mistake 2: Forgetting zero-based indexing

Incorrect:

```java
int idx = c;
```

Correct:

```java
int idx = c - 'a';
```

The subtraction maps letters to indexes from 0 through 25.

### Mistake 3: Assuming a length of 26 means pangram

A sentence of length 26 could contain the same letter 26 times.

For example:

```text
aaaaaaaaaaaaaaaaaaaaaaaaaa
```

Its length is 26, but it is not a pangram.

### Mistake 4: Forgetting imports

For HashSet solutions, include:

```java
import java.util.HashSet;
import java.util.Set;
```

---

## 11. Revision Checklist

- [ ] I understand how `char - 'a'` maps letters to indexes.
- [ ] I can implement a boolean-array solution without referring to notes.
- [ ] I understand why duplicate characters must not increase the distinct count.
- [ ] I can solve the problem using a HashSet.
- [ ] I understand the difference between `contains()`, `add()`, `remove()`, and `size()`.
- [ ] I can explain the time and space complexity.

## 12. Pattern Recognition

**Problem pattern:** Checking whether all elements from a fixed, known set have appeared.

Look for this pattern when a question asks you to:

- Check whether every alphabet letter appears.
- Count distinct lowercase letters.
- Check whether all required symbols are present.
- Determine whether a string contains every character from a known character set.

Useful techniques:

1. Boolean array for a fixed character range.
2. Frequency array when counts matter.
3. HashSet when tracking distinct elements is the main goal.
