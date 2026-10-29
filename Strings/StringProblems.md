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






# LeetCode 1704 — Determine if String Halves Are Alike

🔗 **Problem:** [LeetCode 1704](https://leetcode.com/problems/determine-if-string-halves-are-alike/)

## 1. Problem Overview

Given a string `s` of even length, divide it into two equal halves:

- **First half:** The first `n / 2` characters.
- **Second half:** The remaining `n / 2` characters.

The string is considered alike if both halves contain the same number of vowels.

Vowels are `a`, `e`, `i`, `o`, and `u`, regardless of case.

Return `true` if both halves contain the same number of vowels; otherwise, return `false`.

### Examples

| Input | Output | Explanation |
|---|---|---|
| `"book"` | `true` | Both halves contain one vowel. |
| `"textbook"` | `false` | First half has one vowel; second half has two. |
| `"MerryChristmas"` | `false` | The two halves have different vowel counts. |

---

## 2. Pattern Identification

**Primary pattern:** String Traversal

**Related concepts:**
- Character access using `charAt()`
- Frequency counting
- Two pointers / paired indexing
- Boolean conditions
- Auxiliary space optimization

### When is this pattern useful?

Use this approach when a problem requires you to:
- Compare two portions of a string.
- Count particular characters.
- Compare frequencies between two groups.
- Process corresponding positions from different sections.

---

## 3. Approach 1 — Two Character Arrays

### Idea

1. Create an array for each half.
2. Copy characters from the original string into the arrays.
3. Count the vowels in each array.
4. Compare the counts.

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(n)`

### Advantages

- Beginner-friendly.
- Makes the two halves explicit.
- Useful for learning indexing and character arrays.

### Disadvantages

- Requires additional memory.
- Array creation is unnecessary because the original string already provides character access.

**Learning note:** This is a valid starting approach, but it is not space-optimal.

---

## 4. Approach 2 — Two Counters Without Arrays

### Idea

1. Calculate the midpoint.
2. Access the first half using `s.charAt(i)`.
3. Access the second half using `s.charAt(i + mid)`.
4. Count vowels separately.
5. Compare the two counters.

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(1)`

### Advantages

- Removes both character arrays.
- Keeps the original two-counter logic.
- Uses constant auxiliary space.

### Why is this better than Approach 1?

The algorithm performs the same essential counting work but avoids allocating arrays proportional to the input length.

---

## 5. Approach 3 — Single Counter (Recommended)

### Idea

Instead of counting each half independently, maintain one balance counter.

- Vowel in the first half: `count++`
- Vowel in the second half: `count--`
- Non-vowel: No change

At the end:

- `count == 0` means both halves contain equal numbers of vowels.
- `count != 0` means their vowel counts differ.

### Java Code

```java
class Solution {
    public boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;
        int count = 0;

        for (int i = 0; i < mid; i++) {
            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(i + mid));

            if (isVowel(left)) {
                count++;
            }

            if (isVowel(right)) {
                count--;
            }
        }

        return count == 0;
    }

    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);

        return ch == 'a' || ch == 'e' || ch == 'i'
                || ch == 'o' || ch == 'u';
    }
}
```

### Complexity

- **Time:** `O(n)`
- **Auxiliary space:** `O(1)`

Here, `n` is the length of the string.

### Why is this optimal?

In the worst case, every character must be inspected to determine the vowel counts. This solution achieves linear time and constant auxiliary space.

It also avoids creating a lowercase copy of the original string.

---

## 6. Dry Run — Input `"book"`

The midpoint is `4 / 2 = 2`.

The first half is `"bo"`.
The second half is `"ok"`.

| Iteration | Left character | Right character | Count after processing |
|---|---|---|---:|
| `i = 0` | `b` — not a vowel | `o` — vowel | `-1` |
| `i = 1` | `o` — vowel | `k` — not a vowel | `0` |

Final count: `0`

**Output:** `true`

Both halves contain one vowel.

---

## 7. Important Java Concepts

### `String.length()`

Returns the number of characters in the string.

```java
int mid = s.length() / 2;
```

### `String.charAt(index)`

Returns the character at a particular index.

```java
char ch = s.charAt(i);
```

### `Character.toLowerCase(ch)`

Converts an uppercase character to lowercase.

```java
char ch = Character.toLowerCase(s.charAt(i));
```

This avoids creating an entirely new lowercase string.

### Helper method

```java
private boolean isVowel(char ch)
```

A helper method avoids repeating the same vowel-checking condition.

### Comparing counts

```java
return count == 0;
```

This directly returns a boolean, so a separate `if-else` statement is unnecessary.

---

## 8. Common Mistakes

- Using `=` instead of `==` in comparisons.
- Writing `'0'` instead of `'o'`.
- Processing only part of the first half because of an incorrect loop boundary.
- Using a string index directly as an array index for the second half.
- Using a loop condition that is always false.
- Forgetting uppercase vowels.
- Creating arrays when direct character access is sufficient.
- Claiming `O(1)` auxiliary space while creating a lowercase copy of the string.

---

## 9. Complexity Comparison

| Approach | Time | Auxiliary Space |
|---|---|---|
| Two character arrays | `O(n)` | `O(n)` |
| Two counters | `O(n)` | `O(1)` |
| Single counter | `O(n)` | `O(1)` |

The two-counter and single-counter approaches are both optimal in asymptotic time and auxiliary space.

The single-counter approach is a compact implementation, not a better asymptotic complexity class.

---

## 10. Interview Explanation

"I divide the string into two equal halves and traverse corresponding positions in both halves. I increment a counter for vowels in the first half and decrement it for vowels in the second half. If the final counter is zero, both halves have the same number of vowels. This solution takes O(n) time and O(1) auxiliary space."

---

## 11. Revision Checklist

- [ ] Explain why two arrays are unnecessary.
- [ ] Calculate the midpoint correctly.
- [ ] Access the second half using `i + mid`.
- [ ] Handle uppercase vowels.
- [ ] Explain the single-counter technique.
- [ ] State time and auxiliary space complexity.
- [ ] Implement the optimal solution without looking at the notes.

---

## 12. Key Takeaway

Start with a straightforward solution, verify correctness, and then optimize.

For this problem, the key improvement is moving from storing both halves in arrays to accessing the original string directly. A single counter then tracks the difference between the two vowel counts.

**Recommended revision:** Reimplement the single-counter approach from memory and explain why `count == 0` is sufficient.



# LeetCode 58 — Length of Last Word

🔗 **Problem Link:** https://leetcode.com/problems/length-of-last-word/

## 1. Problem Statement

Given a string `s` consisting of words and spaces, return the length of the last word.

A word is a maximal substring consisting of non-space characters only.

### Examples

| Input | Output | Explanation |
|---|---|---|
| `"Hello World"` | `5` | Last word is `"World"`. |
| `" fly me to the moon "` | `4` | Last word is `"moon"`. |
| `"luffy is still joyboy"` | `6` | Last word is `"joyboy"`. |

---

## 2. DSA Pattern

**Topic:** Strings

**Pattern:** Reverse Traversal

### Concepts used
- `String.length()`
- `String.charAt(index)`
- `while` loops
- Boundary checking
- Counting characters
- Auxiliary space optimization

### When is this pattern useful?

Reverse traversal is useful when:
- You need to find the last word or character sequence.
- The input contains trailing spaces.
- You need to process a string from the end.
- You want to avoid creating additional arrays.

---

## 3. Approach 1 — Using split()

### Idea

1. Remove leading and trailing spaces using `trim()`.
2. Split the string into words.
3. Access the final word.
4. Return its length.

### Code

```java
public int lengthOfLastWord(String s) {
    String[] s1 = s.trim().split("\\s+");
    int j = 0;

    for (int i = 0; i < s1.length; i++) {
        if (i == s1.length - 1) {
            j = s1[i].length();
        }
    }

    return j;
}
```

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(n)`

### Limitations

- Creates an array of words.
- Requires extra memory.
- The loop is unnecessary when the last array element can be accessed directly.

This is a useful beginner approach for learning string splitting and arrays.

---

## 4. Approach 2 — Reverse Traversal (Optimal)

### Idea

Instead of splitting the string, start at the last index and move backward.

**Step 1:** Skip trailing spaces.

**Step 2:** Count characters until a space or the beginning of the string is reached.

**Step 3:** Return the count.

### Java Code

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int c = 0;

        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        while (i >= 0 && s.charAt(i) != ' ') {
            c++;
            i--;
        }

        return c;
    }
}
```

### Complexity

- **Time:** `O(n)`
- **Auxiliary space:** `O(1)`

Here, `n` is the length of the input string.

The algorithm uses only two integer variables and does not create an additional string or array.

---

## 5. Dry Run

### Input

```text
"Hello World   "
```

The last word is `"World"`, so the expected output is `5`.

### Step 1: Initialize

```java
int i = s.length() - 1;
int c = 0;
```

- `i` points to the last character.
- `c` stores the length of the last word.

### Step 2: Skip trailing spaces

```java
while (i >= 0 && s.charAt(i) == ' ') {
    i--;
}
```

The loop moves backward until it reaches `'d'`.

### Step 3: Count the last word

```java
while (i >= 0 && s.charAt(i) != ' ') {
    c++;
    i--;
}
```

| Character | Count |
|---|---:|
| `d` | 1 |
| `l` | 2 |
| `r` | 3 |
| `o` | 4 |
| `W` | 5 |

The loop stops when it reaches the space before `"World"`.

### Step 4: Return

```java
return c;
```

**Output:** `5`

---

## 6. Understanding the Conditions

### `i >= 0`

Ensures that the index remains valid.

Without this condition, accessing `s.charAt(i)` after `i` becomes `-1` would cause an exception.

### `s.charAt(i) == ' '`

Checks whether the current character is a space.

The first loop uses this condition to skip trailing spaces.

### `s.charAt(i) != ' '`

Checks whether the current character belongs to the last word.

The second loop uses this condition to count the word's characters.

### `i--`

Moves one position to the left.

### `c++`

Increases the word-length counter by one.

---

## 7. Complexity Comparison

| Metric | split() approach | Reverse traversal |
|---|---|---|
| Time | `O(n)` | `O(n)` |
| Auxiliary space | `O(n)` | `O(1)` |
| Creates an array | Yes | No |
| Handles trailing spaces | With trimming | Naturally |
| Recommended | Beginner approach | Optimal approach |

Both have linear worst-case time complexity. Reverse traversal improves auxiliary space usage.

---

## 8. Common Mistakes

- Using `s.length` instead of `s.length()`.
- Returning the index of the last word instead of its length.
- Forgetting to skip trailing spaces.
- Accessing `s.charAt(i)` before checking `i >= 0`.
- Creating an array when direct traversal is sufficient.
- Using an incorrect loop condition that skips characters or never executes.

---

## 9. Interview Explanation

"I traverse the string from right to left. First, I skip trailing spaces. Then, I count characters until I encounter a space or reach the beginning of the string. The count gives the length of the last word. This approach takes O(n) time and O(1) auxiliary space."

---

## 10. Revision Checklist

- [ ] Explain why `s.length() - 1` is the last index.
- [ ] Explain why trailing spaces must be skipped.
- [ ] Explain the purpose of both `while` loops.
- [ ] Trace the code for `"Hello World   "`.
- [ ] Explain why `i >= 0` prevents an invalid index.
- [ ] State time complexity as `O(n)`.
- [ ] State auxiliary space as `O(1)`.
- [ ] Reimplement the solution without referring to the notes.

---

## 11. Key Takeaway

The important optimization is replacing `split()` and an array with reverse traversal.

You do not need to store every word to find the last word's length. Start from the end, skip spaces, count characters, and return the result.
