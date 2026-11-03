
# 242. Valid Anagram

| Property | Details |
|---|---|
| LeetCode Number | 242 |
| Problem Name | Valid Anagram |
| Difficulty | Easy |
| Topic | Strings |
| Pattern | Hashing |
| Sub-pattern | Frequency Counting |
| Language | Java |
| Optimal Time | O(n) |
| Optimal Auxiliary Space | O(1) |

**Problem Link:** https://leetcode.com/problems/valid-anagram/

---

# 1. Problem Description

Given two strings `s` and `t`, return true if `t` is
an anagram of `s`, and false otherwise.

An anagram is a word or string formed by rearranging
the characters of another word or string.

Both strings must contain:
- The same characters.
- The same frequency of each character.
- The characters can appear in any order.

### Example 1

Input:
```text
s = "anagram"
t = "nagaram"
```

Output:
```text
true
```

Explanation:

Both strings contain:
- a = 3 times
- n = 1 time
- g = 1 time
- r = 1 time
- m = 1 time

Therefore, they are anagrams.

### Example 2

Input:
```text
s = "rat"
t = "car"
```

Output:
```text
false
```

Explanation:

The first string contains `r`, `a`, `t`.

The second string contains `c`, `a`, `r`.

The character frequencies are different.

### Example 3

Input:
```text
s = "a"
t = "ab"
```

Output:
```text
false
```

Explanation:

The lengths are different, so they cannot be anagrams.

---

# 2. Important Concepts

## A. What is an Anagram?

An anagram is formed by rearranging the characters
of another string without changing their frequencies.

Examples:

| String 1 | String 2 | Anagram? |
|---|---|---|
| listen | silent | Yes |
| earth | heart | Yes |
| rat | car | No |
| aabb | abab | Yes |
| aabb | abbb | No |

## B. Frequency Counting

Frequency counting means calculating how many
times each character occurs in a string.

Example:

```text
s = "aabbc"
```

Frequency table:

| Character | Frequency |
|---|---:|
| a | 2 |
| b | 2 |
| c | 1 |

In Java, frequency counting can be implemented using:
- int array
- HashMap

## C. Hashing

Hashing helps us store and retrieve information
efficiently using keys.

For this problem:
- Character = Key
- Frequency = Value

Example:

```text
a → 2
b → 2
c → 1
```

## D. ASCII Character Mapping

The problem guarantees lowercase English letters.

We can map each character to an array index
using:

```java
s.charAt(i) - 'a'
```

Example:

| Character | Index |
|---|---:|
| a | 0 |
| b | 1 |
| c | 2 |
| d | 3 |
| ... | ... |
| z | 25 |

Therefore, an array of size 26 is sufficient.

---

# 3. Approach 1: Brute Force

## Technique

Sorting + Character Array Comparison.

## Idea

If two strings are anagrams, sorting their characters
should produce exactly the same result.

Steps:

1. Check whether both strings have equal lengths.
2. Convert both strings into character arrays.
3. Sort both character arrays.
4. Compare the sorted arrays.
5. If equal, return true; otherwise, return false.

## Code

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }
}
```

## Dry Run

Input:

```text
s = "anagram"
t = "nagaram"
```

Step 1: Check lengths.

```text
s.length() = 7
t.length() = 7
```

Both lengths are equal.

Step 2: Convert strings into character arrays.

```text
arr1 = [a, n, a, g, r, a, m]
arr2 = [n, a, g, a, r, a, m]
```

Step 3: Sort both arrays.

```text
arr1 = [a, a, a, g, m, n, r]
arr2 = [a, a, a, g, m, n, r]
```

Step 4: Compare arrays.

```java
Arrays.equals(arr1, arr2)
```

Both arrays are equal.

Output:

```text
true
```

## Complexity Analysis

Time Complexity: O(n log n)

- Converting strings to arrays takes O(n).
- Sorting both arrays takes O(n log n).
- Comparing arrays takes O(n).

Auxiliary Space: O(n)

Two additional character arrays are created.

---

# 4. Approach 2: HashMap Frequency Counting

## Technique

HashMap + Frequency Counting.

## Idea

Instead of sorting characters, store the frequency
of every character in a HashMap.

Steps:

1. Check whether both strings have equal lengths.
2. Create a HashMap.
3. Traverse the first string and increase character frequencies.
4. Traverse the second string and decrease frequencies.
5. If a character has frequency zero before decrementing,
   return false.
6. If all characters are processed, return true.

## Code

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            map.put(
                s.charAt(i),
                map.getOrDefault(s.charAt(i), 0) + 1
            );
        }

        for (int i = 0; i < t.length(); i++) {

            if (map.getOrDefault(t.charAt(i), 0) == 0) {
                return false;
            }

            map.put(
                t.charAt(i),
                map.get(t.charAt(i)) - 1
            );
        }

        return true;
    }
}
```

## Dry Run

Input:

```text
s = "aabbc"
t = "abcab"
```

### Step 1: Build the HashMap using s

Initially:

```text
map = {}
```

Traverse `s`.

| i | s.charAt(i) | Frequency update | HashMap |
|---:|---|---|---|
| 0 | a | a = 1 | {a=1} |
| 1 | a | a = 2 | {a=2} |
| 2 | b | b = 1 | {a=2, b=1} |
| 3 | b | b = 2 | {a=2, b=2} |
| 4 | c | c = 1 | {a=2, b=2, c=1} |

### Step 2: Traverse t and decrease frequencies

| i | t.charAt(i) | Before | After |
|---:|---|---:|---:|
| 0 | a | 2 | 1 |
| 1 | b | 2 | 1 |
| 2 | c | 1 | 0 |
| 3 | a | 1 | 0 |
| 4 | b | 1 | 0 |

All frequencies are successfully consumed.

Output:

```text
true
```

### Important: Why getOrDefault()?

```java
map.getOrDefault(ch, 0)
```

It returns:
- The stored frequency if the character exists.
- Zero if the character does not exist.

For example:

```text
map = {a=2, b=1}
```

Then:

```java
map.getOrDefault('a', 0) → 2
map.getOrDefault('c', 0) → 0
```

This helps detect characters that do not exist
in the original string.

## Complexity Analysis

Time Complexity: O(n) average.

- First traversal takes O(n).
- Second traversal takes O(n).
- HashMap operations take O(1) average time.

Auxiliary Space: O(k)

Where k is the number of distinct characters.

For the lowercase English alphabet, k is at most 26,
so this is also O(1) under the given constraints.

---

# 5. Approach 3: Optimal Frequency Array

## Technique

Array Hashing + Frequency Counting.

## Idea

Since the problem guarantees lowercase English letters,
we don't need a HashMap.

We can use a fixed-size integer array of 26 elements.

Each index represents one character.

Steps:

1. Check whether both strings have equal lengths.
2. Create an integer array of size 26.
3. Traverse string s and increase the frequency.
4. Traverse string t and decrease the frequency.
5. If any frequency becomes negative, return false.
6. If all characters are processed, return true.

## Code

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {

            arr[t.charAt(i) - 'a']--;

            if (arr[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
```

## Detailed Dry Run

Input:

```text
s = "anagram"
t = "nagaram"
```

### Step 1: Check lengths

```text
s.length() = 7
t.length() = 7
```

Lengths are equal.

### Step 2: Create the frequency array

```java
int[] arr = new int[26];
```

Initially, every element is zero.

```text
[a, b, c, d, e, f, g, h, i, j, k, l, m,
 n, o, p, q, r, s, t, u, v, w, x, y, z]

[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
```

### Step 3: Traverse the first string

```java
arr[s.charAt(i) - 'a']++;
```

We increase the frequency of each character.

| i | Character | Index | Frequency update |
|---:|---|---:|---|
| 0 | a | 0 | arr[0] = 1 |
| 1 | n | 13 | arr[13] = 1 |
| 2 | a | 0 | arr[0] = 2 |
| 3 | g | 6 | arr[6] = 1 |
| 4 | r | 17 | arr[17] = 1 |
| 5 | a | 0 | arr[0] = 3 |
| 6 | m | 12 | arr[12] = 1 |

After processing `s`:

```text
a = 3
g = 1
m = 1
n = 1
r = 1
```

All other frequencies are zero.

### Step 4: Traverse the second string

```java
arr[t.charAt(i) - 'a']--;
```

Now we decrease the frequency of every character.

| i | Character | Before | After | Negative? |
|---:|---|---:|---:|---|
| 0 | n | 1 | 0 | No |
| 1 | a | 3 | 2 | No |
| 2 | g | 1 | 0 | No |
| 3 | a | 2 | 1 | No |
| 4 | r | 1 | 0 | No |
| 5 | a | 1 | 0 | No |
| 6 | m | 1 | 0 | No |

All required frequencies become zero.

No negative frequency is found.

### Step 5: Final return

```java
return true;
```

Output:

```text
true
```

---

# 6. Understand the Negative Frequency Check

This is the most important line in your optimal solution:

```java
if (arr[t.charAt(i) - 'a'] < 0) {
    return false;
}
```

Why do we check whether the frequency becomes negative?

Consider:

```text
s = "aab"
t = "abb"
```

After processing `s`:

```text
a = 2
b = 1
```

Now process `t`:

| i | Character | Before | After | Result |
|---:|---|---:|---:|---|
| 0 | a | 2 | 1 | Valid |
| 1 | b | 1 | 0 | Valid |
| 2 | b | 0 | -1 | Invalid |

At index 2:

```java
arr['b' - 'a']--;
```

The frequency becomes -1.

This means string `t` contains more occurrences
of `b` than string `s`.

Therefore, return false immediately.

**Important observation:**

Because both strings have equal lengths, if no
frequency becomes negative while processing `t`,
all frequencies must eventually become zero.

That is why a final array traversal is unnecessary.

---

# 7. Approach Comparison

| Approach | Technique | Time | Auxiliary Space |
|---|---|---|---|
| Brute Force | Sorting | O(n log n) | O(n) |
| Better | HashMap | O(n) average | O(k) |
| Optimal | Frequency Array | O(n) | O(1) |

**Recommended approach:** Frequency Array.

Why?

- The character set is fixed at 26 lowercase letters.
- No HashMap is needed.
- No sorting is needed.
- Frequency lookup and update take O(1).
- The additional array always has 26 elements.

---

# 8. Important Java Methods and Concepts

| Java concept | Purpose |
|---|---|
| `s.length()` | Returns the length of a string |
| `s.charAt(i)` | Returns the character at index i |
| `new int[26]` | Creates a frequency array |
| `arr[index]++` | Increases frequency |
| `arr[index]--` | Decreases frequency |
| `HashMap` | Stores character-frequency pairs |
| `getOrDefault()` | Returns stored value or default |
| `put()` | Inserts or updates a key-value pair |
| `Arrays.sort()` | Sorts an array |
| `Arrays.equals()` | Compares array contents |

---

# 9. Common Mistakes

1. Forgetting to check whether the string lengths are equal.
2. Using `arr[s.charAt(i)]` instead of `arr[s.charAt(i) - 'a']`.
3. Using an array of incorrect size.
4. Increasing frequencies for both strings instead of decreasing the second string's frequencies.
5. Forgetting to check for negative frequencies.
6. Returning true immediately after processing the first string.
7. Using `==` to compare arrays instead of `Arrays.equals()`.
8. Assuming the 26-element array works for uppercase letters or arbitrary Unicode characters.

---

# 10. Edge Cases

| Input s | Input t | Expected |
|---|---|---|
| `"anagram"` | `"nagaram"` | true |
| `"rat"` | `"car"` | false |
| `"a"` | `"a"` | true |
| `"a"` | `"b"` | false |
| `"aabb"` | `"abab"` | true |
| `"aabb"` | `"abbb"` | false |
| `"abc"` | `"ab"` | false |

---

# 11. Interview Explanation

"I solve this problem using frequency counting.

First, I check whether both strings have equal lengths.

Since the problem contains only lowercase English letters,
I create an integer array of size 26.

I traverse the first string and increase the frequency
of each character using its index calculated by
subtracting 'a' from the character.

Then I traverse the second string and decrease
the corresponding frequencies.

If any frequency becomes negative, it means the
second string contains more occurrences of a
character than the first string, so I return false.

If the traversal completes without a negative
frequency, I return true.

The solution takes O(n) time and O(1) auxiliary space."

---

# 12. Key Takeaways

**Main DSA Pattern:** Hashing.

**Sub-pattern:** Frequency Counting.

**Core Logic:**

Count characters from the first string and subtract
the character frequencies of the second string.

If any frequency becomes negative, the strings
cannot be anagrams.

**Best Approach:** Fixed-size Frequency Array.

**Time Complexity:** O(n)

**Auxiliary Space:** O(1)

---

# 13. Revision Checklist

- [ ] Understand what an anagram means.
- [ ] Understand character frequency counting.
- [ ] Understand HashMap frequency counting.
- [ ] Understand `getOrDefault()`.
- [ ] Understand character-to-index mapping.
- [ ] Implement the sorting approach.
- [ ] Implement the HashMap approach.
- [ ] Implement the frequency array approach.
- [ ] Explain why negative frequency means false.
- [ ] Explain O(n) time complexity.
- [ ] Explain O(1) auxiliary space.
- [ ] Explain the solution in an interview.

**Final takeaway:**

Whenever a problem asks whether two strings
contain the same characters with the same
frequencies, think about frequency counting
using HashMap or an integer array.
```

## Git commands

Run these from your repository root:

```bash
git add Strings/0242-Valid-Anagram.java Strings/0242-Valid-Anagram.md

git commit -m "Add LeetCode 242 Valid Anagram solutions and notes"

git push
```

## Final revision summary

| Approach | Time | Auxiliary space |
|---|---:|---:|
| Sorting | O(n log n) | O(n) |
| HashMap | O(n) average | O(k), at most 26 here |
| Frequency array | O(n) | O(1) |

**Interview recommendation:** Use your frequency-array solution. It is concise, efficient, and demonstrates a strong understanding of hashing without needing the Collections Framework.



# LeetCode 383 – Ransom Note

## 1. Problem Information

| Property | Details |
|---|---|
| LeetCode Number | 383 |
| Problem Name | Ransom Note |
| Link | https://leetcode.com/problems/ransom-note/ |
| Difficulty | Easy |
| Language | Java |
| Topic | Strings, Hashing |
| Pattern | Frequency Counting |

### Problem Statement

Given two strings, `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed using the characters from `magazine`.

Otherwise, return `false`.

Each character in `magazine` can only be used once.

### Examples

**Example 1**

Input:
```text
ransomNote = "a"
magazine = "b"
```

Output:
```text
false
```

Explanation: Magazine does not contain the required character `a`.

**Example 2**

Input:
```text
ransomNote = "aa"
magazine = "ab"
```

Output:
```text
false
```

Explanation: Magazine contains only one `a`, but ransomNote requires two.

**Example 3**

Input:
```text
ransomNote = "aa"
magazine = "aab"
```

Output:
```text
true
```

Explanation: Magazine contains two `a` characters, so the ransom note can be constructed.

---

## 2. Important Constraints

- Both strings contain lowercase English letters.
- Every magazine character can be used only once.
- Repeated characters must be counted separately.
- The order of characters does not matter.

## 3. Prerequisites

Before solving this problem, understand:

1. Java Strings and `charAt()`.
2. Character-to-index conversion.
3. Arrays and frequency counting.
4. HashMap and `getOrDefault()`.
5. Nested loops.
6. Time and auxiliary space complexity.

## 4. DSA Pattern – Hashing / Frequency Counting

### Core Idea

We need to determine whether magazine has enough occurrences of every character required by ransomNote.

Instead of searching repeatedly, store the frequency of each available character.

### Algorithm Flow

```text
          Start
            |
            v
     Count characters
       in magazine
            |
            v
    Process ransomNote
            |
            v
    Decrease frequency
            |
            v
    Is frequency < 0?
        /       \
      Yes        No
       |          |
       v          v
   Return       Continue
    false          |
                   v
           All characters
             processed?
                   |
                   v
              Return true
```

---

# 5. Approach 1 – Brute Force

### Concept

For every character in ransomNote, search for a matching unused character in magazine.

Use a boolean array to remember which magazine positions have already been used.

### Algorithm

1. Create a boolean array of magazine length.
2. Traverse every character in ransomNote.
3. Search magazine for a matching character.
4. Check whether that position has already been used.
5. If a match is found, mark the position as used.
6. If no unused matching character is found, return false.
7. If all characters are matched, return true.

### Dry Run

Input:

```text
ransomNote = "aa"
magazine = "aab"
```

Initially:

```text
used = [false, false, false]
```

Magazine positions:

| Index | Character | Used? |
|---|---|---|
| 0 | a | false |
| 1 | a | false |
| 2 | b | false |

**Iteration 1: ransomNote character = 'a'**

Search magazine:

- Index 0 contains `a`.
- Index 0 is unused.
- Mark index 0 as used.

```text
used = [true, false, false]
```

**Iteration 2: ransomNote character = 'a'**

Search magazine again:

- Index 0 contains `a`, but it is already used.
- Index 1 contains `a` and is unused.
- Mark index 1 as used.

```text
used = [true, true, false]
```

Both characters were found.

Output:

```text
true
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n * m) | Search magazine for each ransomNote character |
| Auxiliary Space | O(m) | Boolean used array |

### Limitation

Repeatedly searching the magazine is inefficient. We can improve this using frequency counting.

---

# 6. Approach 2 – Better: HashMap Frequency Counting

### Concept

Use a HashMap to store the frequency of every character in magazine.

Instead of repeatedly searching for characters, access their frequency directly.

### Algorithm

1. Create a HashMap<Character, Integer>.
2. Traverse magazine.
3. Store the frequency of each character.
4. Traverse ransomNote.
5. Check whether the required character has a positive frequency.
6. If its frequency is zero, return false.
7. Otherwise, decrement its frequency.
8. If all characters are processed, return true.

### Important Java Methods

```java
map.getOrDefault(ch, 0)
```

Returns the existing frequency or zero if the character is absent.

```java
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

Increases the character frequency.

```java
map.put(ch, map.get(ch) - 1);
```

Consumes one occurrence of the character.

### Dry Run

Input:

```text
ransomNote = "aab"
magazine = "aabb"
```

**Step 1: Count magazine**

| Character | Frequency |
|---|---:|
| a | 2 |
| b | 2 |

**Step 2: Process ransomNote**

| i | Character | Frequency before | Frequency after |
|---|---|---:|---:|
| 0 | a | 2 | 1 |
| 1 | a | 1 | 0 |
| 2 | b | 2 | 1 |

No character has insufficient frequency.

Output:

```text
true
```

### Failure Case

Input:

```text
ransomNote = "aab"
magazine = "ab"
```

Initial frequency:

| Character | Frequency |
|---|---:|
| a | 1 |
| b | 1 |

Process:

| i | Character | Frequency after use |
|---|---|---:|
| 0 | a | 0 |
| 1 | a | - Not available |

At the second `a`, the frequency is zero before consumption.

Return:

```text
false
```

### Complexity Analysis

| Complexity | Value |
|---|---|
| Time | O(n + m) average |
| Auxiliary Space | O(k) |

Here, k represents the number of distinct characters stored in the HashMap.

### Limitation

HashMap provides flexible character storage, but it requires additional data structure overhead.

Since this problem only contains lowercase English letters, a fixed-size array is sufficient.

---

# 7. Approach 3 – Optimal: Frequency Array

### Concept

Use an integer array of size 26 instead of a HashMap.

Each index represents one lowercase English letter.

### Character Mapping

| Character | Index calculation | Index |
|---|---|---:|
| a | 'a' - 'a' | 0 |
| b | 'b' - 'a' | 1 |
| c | 'c' - 'a' | 2 |
| d | 'd' - 'a' | 3 |
| ... | ... | ... |
| z | 'z' - 'a' | 25 |

### Algorithm

**Step 1: Create frequency array**

```java
int[] arr = new int[26];
```

All frequencies initially equal zero.

**Step 2: Count magazine characters**

```java
for (int i = 0; i < magazine.length(); i++) {
    arr[magazine.charAt(i) - 'a']++;
}
```

Increase the frequency for every available character.

**Step 3: Process ransomNote**

```java
for (int i = 0; i < ransomNote.length(); i++) {
    int index = ransomNote.charAt(i) - 'a';
    arr[index]--;
    
    if (arr[index] < 0) {
        return false;
    }
}
```

Decrease the frequency whenever a character is used.

**Step 4: Return true**

If all required characters are available, return true.

### Detailed Dry Run 1 – Successful Case

Input:

```text
ransomNote = "aa"
magazine = "aab"
```

**Step 1: Initialize**

```text
arr = [0, 0, 0, 0, ...]
```

**Step 2: Count magazine**

Magazine characters: `a`, `a`, `b`.

| Iteration | i | Character | Index | Updated frequency |
|---|---:|---|---:|---|
| 1 | 0 | a | 0 | arr[0] = 1 |
| 2 | 1 | a | 0 | arr[0] = 2 |
| 3 | 2 | b | 1 | arr[1] = 1 |

After counting:

```text
arr[0] = 2  // a
arr[1] = 1  // b
```

**Step 3: Process ransomNote**

First iteration:

```text
i = 0
ransomNote.charAt(0) = 'a'

index = 'a' - 'a'
index = 0

arr[0] = 2 - 1
arr[0] = 1
```

Condition:

```text
arr[0] < 0
1 < 0 = false
```

Continue.

Second iteration:

```text
i = 1
ransomNote.charAt(1) = 'a'

index = 'a' - 'a'
index = 0

arr[0] = 1 - 1
arr[0] = 0
```

Condition:

```text
arr[0] < 0
0 < 0 = false
```

The loop completes.

Output:

```text
true
```

### Detailed Dry Run 2 – Failure Case

Input:

```text
ransomNote = "aa"
magazine = "ab"
```

**Step 1: Count magazine**

| Character | Frequency |
|---|---:|
| a | 1 |
| b | 1 |

**Step 2: Process ransomNote**

First iteration:

```text
i = 0
Character = 'a'

arr[0] = 1 - 1
arr[0] = 0
```

Condition:

```text
0 < 0 = false
```

Continue.

Second iteration:

```text
i = 1
Character = 'a'

arr[0] = 0 - 1
arr[0] = -1
```

Condition:

```text
-1 < 0 = true
```

The method immediately returns false.

Output:

```text
false
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(m + n) | Two separate traversals |
| Auxiliary Space | O(1) | Fixed array of 26 integers |

This is the optimal approach for the given lowercase English alphabet constraint.

---

# 8. Approach Comparison

| Feature | Brute Force | HashMap | Frequency Array |
|---|---|---|---|
| Technique | Nested search | Hashing | Direct indexing |
| Time | O(n*m) | O(n+m) avg | O(n+m) |
| Auxiliary space | O(m) | O(k) | O(1) |
| Repeated search | Yes | No | No |
| Best for | Understanding basic matching | Flexible character sets | Fixed lowercase alphabet |

**Final selection:** Frequency Array.

Reason: The problem guarantees lowercase English letters, so a fixed array of 26 elements is sufficient.

---

# 9. Important Concepts Learned

### 1. Frequency Counting

Instead of searching for each character repeatedly, store its occurrence count.

### 2. Character-to-Index Conversion

```java
ch - 'a'
```

Converts a lowercase character into an array index between 0 and 25.

### 3. Frequency Increment

```java
arr[index]++;
```

Represents an available character.

### 4. Frequency Decrement

```java
arr[index]--;
```

Represents consuming a character.

### 5. Negative Frequency

```java
if (arr[index] < 0)
```

Indicates that more occurrences are required than are available.

---

# 10. Common Mistakes

| Mistake | Why it is incorrect | Correct understanding |
|---|---|---|
| `new int[m]` | Magazine length is not alphabet size | Use 26 |
| Checking `ransomNote.charAt(i) < 0` | Checks a character, not its frequency | Check `arr[index] < 0` |
| Only counting magazine | Does not verify required characters | Process ransomNote too |
| Not decrementing frequency | Allows the same character to be reused | Decrement after use |
| Ignoring duplicates | Repeated letters need separate occurrences | Maintain frequency counts |

---

# 11. Edge Cases

| Input | Expected output | Reason |
|---|---|---|
| `""`, `""` | true | Empty note needs no letters |
| `"a"`, `"a"` | true | Exact match |
| `"a"`, `"b"` | false | Required character unavailable |
| `"aa"`, `"a"` | false | Insufficient occurrences |
| `"aab"`, `"aabb"` | true | All required characters available |
| `"abc"`, `"ab"` | false | Missing c |
| `"abc"`, `"cba"` | true | Order does not matter |

---

# 12. Interview Explanation

"I solve this problem using frequency counting.

First, I create an integer array of size 26 because the problem contains only lowercase English letters.

I traverse the magazine string and count the frequency of each character.

Then I traverse the ransomNote string and decrement the corresponding frequency.

If any frequency becomes negative, it means the magazine does not contain enough occurrences of that character, so I return false.

If the entire ransomNote is processed successfully, I return true.

The time complexity is O(n + m), and the auxiliary space complexity is O(1), since the frequency array always contains 26 elements."

---

# 13. Revision Checklist

- [ ] Understand the problem statement.
- [ ] Implement brute force using a used array.
- [ ] Understand HashMap frequency counting.
- [ ] Understand fixed-size frequency arrays.
- [ ] Remember character-to-index conversion.
- [ ] Understand why negative frequency means failure.
- [ ] Dry-run successful and unsuccessful examples.
- [ ] Explain O(n + m) time complexity.
- [ ] Explain O(1) auxiliary space.
- [ ] Solve the problem again without referring to notes.

---

# 14. GitHub Commands

Run these commands from your repository root:

```bash
git add Strings/0383-Ransom-Note.java Strings/0383-Ransom-Note.md

git commit -m "Add LC 383 Ransom Note solutions and notes"

git push origin main
```

If your working branch is not `main`, replace it with your actual branch name.

---

## 15. Final Learning Summary

| Item | Completed |
|---|---|
| Brute Force | Documented |
| Better – HashMap | Documented |
| Optimal – Frequency Array | Documented |
| Dry Runs | 2 detailed examples |
| Complexity Comparison | Completed |
| Edge Cases | Covered |
| Interview Explanation | Covered |

**Key takeaway:** When a problem asks whether characters can be rearranged, matched, consumed, or counted, consider whether a frequency-counting technique can solve it efficiently.



# LeetCode 387 – First Unique Character in a String

## 1. Problem Information

| Property | Details |
|---|---|
| LeetCode Number | 387 |
| Problem Name | First Unique Character in a String |
| Link | https://leetcode.com/problems/first-unique-character-in-a-string/ |
| Difficulty | Easy |
| Language | Java |
| Topic | Strings, Hashing |
| Pattern | Frequency Counting |

### Problem Statement

Given a string `s`, find the first non-repeating character and return its index.

If it does not exist, return -1.

### Examples

**Example 1**

Input:
```text
s = "leetcode"
```

Output:
```text
0
```

Explanation: `l` is the first character that appears only once.

**Example 2**

Input:
```text
s = "loveleetcode"
```

Output:
```text
2
```

Explanation: `v` is the first non-repeating character.

**Example 3**

Input:
```text
s = "aabb"
```

Output:
```text
-1
```

Explanation: Every character appears more than once.

---

## 2. Important Constraints

- The string contains lowercase English letters.
- We must return the index, not the character.
- The first unique character must be identified according to its original position.
- If no unique character exists, return -1.

## 3. Prerequisites

1. Java Strings and charAt().
2. Character-to-index conversion.
3. Arrays and frequency counting.
4. HashMap and getOrDefault().
5. Nested loops.
6. Time and auxiliary space complexity.

---

# 4. DSA Pattern – Hashing / Frequency Counting

### Core Idea

We need to find the first character whose frequency is exactly 1.

Instead of repeatedly searching the string, calculate character frequencies first.

Then traverse the original string again to find the first unique character.

### Algorithm Flow

```text
           Start
             |
             v
       Count character
         frequencies
             |
             v
       Traverse original
            string
             |
             v
       Is frequency == 1?
          /       \
        Yes        No
         |          |
         v          v
    Return index   Continue
         |
         v
       End loop
         |
         v
      Return -1
```

---

# 5. Approach 1 – Brute Force

### Concept

For every character, traverse the complete string and count its occurrences.

If the character occurs exactly once, return its index.

### Algorithm

1. Traverse the string using index i.
2. For each character, initialize count = 0.
3. Traverse the complete string using index j.
4. Compare characters at i and j.
5. If they match, increment count.
6. If count == 1, return i.
7. If no unique character is found, return -1.

### Dry Run

Input:

```text
s = "leetcode"
```

**Iteration 1**

i = 0

Character = 'l'

Compare with every character:

| j | s.charAt(j) | Match with 'l'? |
|---|---|---|
| 0 | l | Yes |
| 1 | e | No |
| 2 | e | No |
| 3 | t | No |
| 4 | c | No |
| 5 | o | No |
| 6 | d | No |
| 7 | e | No |

Count = 1.

Since count == 1, return index 0.

Output:

```text
0
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n²) | Nested traversal |
| Auxiliary Space | O(1) | Only a counter is used |

### Limitation

Repeatedly traversing the entire string for every character is inefficient.

We can improve this using frequency counting.

---

# 6. Approach 2 – Better: HashMap Frequency Counting

### Concept

Use a HashMap to store each character and its frequency.

Instead of repeatedly counting occurrences, calculate all frequencies in one traversal.

### Algorithm

1. Create a HashMap<Character, Integer>.
2. Traverse the string.
3. Count the frequency of each character.
4. Traverse the original string again.
5. Check whether the current character's frequency is exactly 1.
6. Return its index if it is unique.
7. If no unique character exists, return -1.

### Important Java Methods

**getOrDefault()**

```java
map.getOrDefault(ch, 0)
```

Returns the existing frequency or 0 if the character is absent.

**put()**

```java
map.put(ch, map.getOrDefault(ch, 0) + 1);
```

Increases the character frequency.

**get()**

```java
map.get(ch)
```

Retrieves the stored frequency.

### Dry Run

Input:

```text
s = "loveleetcode"
```

**Step 1: Count frequencies**

| Character | Frequency |
|---|---:|
| l | 1 |
| o | 2 |
| v | 1 |
| e | 4 |
| t | 1 |
| c | 1 |
| d | 1 |

**Step 2: Traverse original string**

| Index | Character | Frequency | Action |
|---:|:---:|---:|---|
| 0 | l | 1 | Return 0 |

Output:

```text
0
```

### Complexity Analysis

| Complexity | Value |
|---|---|
| Time | O(n) average |
| Auxiliary Space | O(k) |

k = number of distinct characters stored in the HashMap.

### Limitation

HashMap is flexible, but this problem only contains lowercase English letters.

A fixed-size array can represent the frequencies more directly.

---

# 7. Approach 3 – Optimal: Frequency Array

### Concept

Use an integer array of size 26.

Each index represents one lowercase English letter.

### Character Mapping

| Character | Index calculation | Index |
|---|---|---:|
| a | 'a' - 'a' | 0 |
| b | 'b' - 'a' | 1 |
| c | 'c' - 'a' | 2 |
| d | 'd' - 'a' | 3 |
| ... | ... | ... |
| z | 'z' - 'a' | 25 |

### Algorithm

**Step 1: Create frequency array**

```java
int[] arr = new int[26];
```

All elements initially contain zero.

**Step 2: Count character frequencies**

```java
for (int i = 0; i < s.length(); i++) {
    arr[s.charAt(i) - 'a']++;
}
```

**Step 3: Traverse original string**

```java
for (int i = 0; i < s.length(); i++) {

    int index = s.charAt(i) - 'a';

    if (arr[index] == 1) {
        return i;
    }
}
```

**Step 4: Return -1**

If no character has frequency 1, return -1.

---

### Detailed Dry Run 1 – "loveleetcode"

Input:

```text
s = "loveleetcode"
```

**Step 1: Initialize**

```text
arr = [0, 0, 0, 0, ...]
```

**Step 2: Count frequencies**

String positions:

| Index | Character | Array index | Updated frequency |
|---:|:---:|---:|---|
| 0 | l | 11 | arr[11] = 1 |
| 1 | o | 14 | arr[14] = 1 |
| 2 | v | 21 | arr[21] = 1 |
| 3 | e | 4 | arr[4] = 1 |
| 4 | l | 11 | arr[11] = 2 |
| 5 | e | 4 | arr[4] = 2 |
| 6 | e | 4 | arr[4] = 3 |
| 7 | t | 19 | arr[19] = 1 |
| 8 | c | 2 | arr[2] = 1 |
| 9 | o | 14 | arr[14] = 2 |
| 10 | d | 3 | arr[3] = 1 |
| 11 | e | 4 | arr[4] = 4 |

Final frequencies:

| Character | Frequency |
|---|---:|
| l | 2 |
| o | 2 |
| v | 1 |
| e | 4 |
| t | 1 |
| c | 1 |
| d | 1 |

**Step 3: Traverse original string again**

| i | Character | Frequency | Decision |
|---:|:---:|---:|---|
| 0 | l | 2 | Continue |
| 1 | o | 2 | Continue |
| 2 | v | 1 | Return 2 |

Output:

```text
2
```

### Detailed Dry Run 2 – "aabb"

Input:

```text
s = "aabb"
```

**Step 1: Count frequencies**

| Character | Frequency |
|---|---:|
| a | 2 |
| b | 2 |

**Step 2: Traverse original string**

| i | Character | Frequency | Decision |
|---:|:---:|---:|---|
| 0 | a | 2 | Continue |
| 1 | a | 2 | Continue |
| 2 | b | 2 | Continue |
| 3 | b | 2 | Continue |

The loop completes without finding frequency 1.

Output:

```text
-1
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n) | Two traversals |
| Auxiliary Space | O(1) | Fixed array of 26 integers |

This is optimal for the given lowercase-English-letter constraint.

---

# 8. Approach Comparison

| Feature | Brute Force | HashMap | Frequency Array |
|---|---|---|---|
| Technique | Nested counting | Hashing | Direct indexing |
| Time | O(n²) | O(n) average | O(n) |
| Auxiliary Space | O(1) | O(k) | O(1) |
| Traversals | Repeated | 2 | 2 |
| Best use | Basic understanding | Flexible character sets | Fixed alphabet |

**Final selection: Frequency Array.**

Reason: The problem restricts the input to lowercase English letters, so a fixed array of 26 elements is sufficient.

---

# 9. Important Concepts Learned

### 1. Frequency Counting

Store how many times each character occurs.

### 2. Character-to-Index Conversion

```java
ch - 'a'
```

Converts a lowercase character into an array index from 0 to 25.

### 3. Frequency Increment

```java
arr[index]++;
```

Counts an occurrence.

### 4. Checking Unique Characters

```java
arr[index] == 1
```

Identifies a character that appears exactly once.

### 5. Original String Traversal

Always traverse the original string from left to right when the problem asks for the first unique character.

This preserves the original order.

### 6. Return Index, Not Character

```java
return i;
```

The required answer is the original string index.

---

# 10. Common Mistakes

| Mistake | Why incorrect | Correct understanding |
|---|---|---|
| Traversing only the frequency array | Alphabet order is not string order | Traverse original string |
| Returning array index | It represents alphabet position | Return original string index |
| Checking frequency == 0 | Zero means absent | Check frequency == 1 |
| Returning the character | Problem asks for index | Return i |
| Forgetting -1 | No unique character may exist | Return -1 after loop |

---

# 11. Edge Cases

| Input | Expected output | Reason |
|---|---:|---|
| `"a"` | 0 | Single character is unique |
| `"leetcode"` | 0 | l is unique and first |
| `"loveleetcode"` | 2 | v is the first unique character |
| `"aabb"` | -1 | No unique character |
| `"aabbc"` | 4 | c appears once |
| `"abcabc"` | -1 | Every character repeats |
| `"z"` | 0 | Single z is unique |

---

# 12. Interview Explanation

"I solve this problem using frequency counting.

First, I create an integer array of size 26 because the input contains lowercase English letters.

I traverse the string once and count the frequency of every character.

Then I traverse the original string again from left to right. For each character, I check whether its frequency is exactly one.

If I find such a character, I immediately return its original index.

If no unique character is found after the traversal, I return -1.

The time complexity is O(n), and auxiliary space complexity is O(1), because the frequency array always has 26 elements."

---

# 13. Revision Checklist

- [ ] Understand the problem statement.
- [ ] Implement brute force using nested loops.
- [ ] Understand HashMap frequency counting.
- [ ] Understand fixed-size frequency arrays.
- [ ] Remember character-to-index conversion.
- [ ] Understand why the original string must be traversed again.
- [ ] Understand why we return i instead of the character.
- [ ] Dry-run successful and unsuccessful examples.
- [ ] Explain O(n) time complexity.
- [ ] Explain O(1) auxiliary space.
- [ ] Solve independently without referring to notes.

---

# 14. GitHub Commands

Run these commands from your repository root:

```bash
git add Strings/0387-First-Unique-Character-in-a-String.java Strings/0387-First-Unique-Character-in-a-String.md

git commit -m "Add LC 387 First Unique Character solutions and notes"

git push origin main
```

If your current branch is not main, replace `main` with your actual branch name.

---

# 15. Final Learning Summary

| Item | Status |
|---|---|
| Brute Force | Documented |
| Better – HashMap | Documented |
| Optimal – Frequency Array | Documented |
| Detailed Dry Runs | Completed |
| Complexity Comparison | Completed |
| Edge Cases | Covered |
| Interview Explanation | Covered |

**Key takeaway:** When a problem asks for the first, earliest, or leftmost unique element, frequency counting followed by a second traversal of the original input is a useful pattern.



# LeetCode 205 – Isomorphic Strings

## 1. Problem Information

| Property | Details |
|---|---|
| LeetCode Number | 205 |
| Problem Name | Isomorphic Strings |
| Link | https://leetcode.com/problems/isomorphic-strings/ |
| Difficulty | Easy |
| Language | Java |
| Topic | Strings, Hashing |
| Pattern | Two-way Character Mapping |

### Problem Statement

Given two strings s and t, determine whether they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

Every occurrence of a character must be replaced with the same character.

Two different characters cannot map to the same character.

### Examples

**Example 1**

Input:
```text
s = "egg"
t = "add"
```

Output:
```text
true
```

Explanation:
- e maps to a.
- g maps to d.
- The mapping is consistent.

**Example 2**

Input:
```text
s = "foo"
t = "bar"
```

Output:
```text
false
```

Explanation:
- f maps to b.
- Both occurrences of o would need to map to the same character.
- However, the target characters are a and r.
- The mapping is inconsistent.

**Example 3**

Input:
```text
s = "paper"
t = "title"
```

Output:
```text
true
```

Explanation:

| Source | Target |
|---|---|
| p | t |
| a | i |
| p | t |
| e | l |
| r | e |

The mapping is consistent and one-to-one.

---

## 2. Important Constraints

- Both strings must have equal lengths.
- Character mappings must remain consistent.
- One source character cannot map to multiple target characters.
- Multiple source characters cannot map to the same target character.
- The problem supports ASCII characters.

## 3. Prerequisites

1. Java Strings and charAt().
2. Character comparison.
3. HashMap and containsKey().
4. HashMap put() and get().
5. Arrays and character indexing.
6. One-to-one mapping.
7. Time and auxiliary space complexity.

---

# 4. DSA Pattern – Two-way Character Mapping

### Core Idea

We need to establish a one-to-one relationship between characters of two strings.

There are two rules:

**Rule 1: Source to Target**

One character in s must always map to the same character in t.

Example:

```text
s = "egg"
t = "add"

e -> a
g -> d
```

This is valid.

But:

```text
s = "foo"
t = "bar"

o -> a
o -> r
```

This is invalid because the same source character maps to two different target characters.

**Rule 2: Target to Source**

Two different source characters cannot map to the same target character.

Example:

```text
s = "ab"
t = "cc"
```

Mapping:

```text
a -> c
b -> c
```

This is invalid because two different characters map to the same target character.

### Mapping Diagram

```text
             Source (s)
                 |
                 v
           Target (t)
                 |
                 v
        Check source mapping
                 |
                 v
        Check target mapping
                 |
                 v
       Both mappings consistent?
             /       \
           Yes        No
            |          |
            v          v
         Continue    Return false
            |
            v
      All characters processed
            |
            v
        Return true
```

---

# 5. Approach 1 – Brute Force

### Concept

Compare every character with all previously visited characters.

Instead of maintaining a mapping data structure, use nested loops to verify that previous character relationships are consistent.

### Algorithm

1. Check whether both strings have equal lengths.
2. Traverse the strings using index i.
3. Compare the current positions with all previous positions j.
4. If s[i] equals s[j], then t[i] must equal t[j].
5. If t[i] equals t[j], then s[i] must equal s[j].
6. If either condition fails, return false.
7. If all comparisons pass, return true.

### Dry Run

Input:

```text
s = "egg"
t = "add"
```

**Iteration 1: i = 0**

```text
s[0] = e
t[0] = a
```

No previous characters exist.

Continue.

**Iteration 2: i = 1**

```text
s[1] = g
t[1] = d
```

Compare with j = 0:

```text
s[1] == s[0]?
g == e -> false

t[1] == t[0]?
d == a -> false
```

No conflict.

Continue.

**Iteration 3: i = 2**

```text
s[2] = g
t[2] = d
```

Compare with j = 0:

```text
g == e -> false
d == a -> false
```

Compare with j = 1:

```text
g == g -> true
d == d -> true
```

The mapping is consistent.

Output:

```text
true
```

### Invalid Case

Input:

```text
s = "ab"
t = "cc"
```

At i = 1, j = 0:

```text
s[1] == s[0]?
b == a -> false

t[1] == t[0]?
c == c -> true
```

The same target character is being assigned to two different source characters.

Return:

```text
false
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n²) | Nested loops compare previous positions |
| Auxiliary Space | O(1) | No additional data structure |

### Limitation

Repeated comparisons make the approach inefficient for long strings.

We can improve this by maintaining character mappings.

---

# 6. Approach 2 – Better: Two HashMaps

### Concept

Use two HashMaps to maintain mappings in both directions.

Map 1:

```text
Source character -> Target character
```

Map 2:

```text
Target character -> Source character
```

Why two maps?

Because checking only one direction is insufficient to guarantee a one-to-one relationship.

### Algorithm

1. Check whether string lengths are equal.
2. Create two HashMaps.
3. Traverse both strings simultaneously.
4. Extract the current characters c1 and c2.
5. Check whether c1 is already mapped.
6. If mapped to a different character, return false.
7. Check whether c2 is already mapped.
8. If mapped to a different source character, return false.
9. Store both mappings.
10. Return true after processing all characters.

### Important Java Methods

**containsKey()**

```java
map.containsKey(c1)
```

Checks whether a character already has a mapping.

**get()**

```java
map.get(c1)
```

Retrieves the character currently mapped to c1.

**put()**

```java
map.put(c1, c2)
```

Stores a character mapping.

### Dry Run

Input:

```text
s = "paper"
t = "title"
```

Initially:

```text
map1 = {}
map2 = {}
```

| i | c1 | c2 | Source mapping | Target mapping | Result |
|---:|:---:|:---:|---|---|---|
| 0 | p | t | p → t | t → p | Store |
| 1 | a | i | a → i | i → a | Store |
| 2 | p | t | Existing p → t | Existing t → p | Valid |
| 3 | e | l | e → l | l → e | Store |
| 4 | r | e | r → e | e → r | Store |

No conflicts are found.

Output:

```text
true
```

### Failure Case

Input:

```text
s = "ab"
t = "cc"
```

At i = 0:

```text
map1 = {a -> c}
map2 = {c -> a}
```

At i = 1:

```text
c1 = b
c2 = c
```

Check map1:

```text
b is not mapped.
```

Check map2:

```text
c is already mapped to a.
```

But the current source character is b.

The mapping is inconsistent.

Return:

```text
false
```

### Complexity Analysis

| Complexity | Value |
|---|---|
| Time | O(n) average |
| Auxiliary Space | O(k) |

k represents the number of distinct characters stored in the maps.

### Limitation

HashMaps are flexible but have additional data structure overhead.

Since the problem uses ASCII characters, fixed-size arrays can represent the mappings directly.

---

# 7. Approach 3 – Optimal: Two Mapping Arrays

### Concept

Use two integer arrays instead of two HashMaps.

For ASCII characters, each array has 128 positions.

```java
int[] arr1 = new int[128];
int[] arr2 = new int[128];
```

Each character's ASCII value becomes its array index.

### Array Responsibilities

**arr1 – Source to Target**

```text
arr1[c1] = c2
```

Stores which target character is mapped to the source character.

**arr2 – Target to Source**

```text
arr2[c2] = c1
```

Stores which source character is mapped to the target character.

### Why Do We Need Two Arrays?

Consider:

```text
s = "ab"
t = "cc"
```

If we maintain only source-to-target mapping:

```text
a -> c
b -> c
```

Both mappings might appear valid.

But the reverse mapping reveals the conflict:

```text
c -> a
c -> b
```

The same target character cannot map back to two different source characters.

Therefore, both arrays are necessary.

### Sentinel Value

In the improved implementation:

```java
Arrays.fill(arr1, -1);
Arrays.fill(arr2, -1);
```

- `-1` means the character has not been mapped.
- Any non-negative value represents an existing ASCII character mapping.

This avoids confusing an unmapped character with ASCII character code 0.

### Algorithm

1. Check whether string lengths are equal.
2. Create two arrays of size 128.
3. Initialize both arrays with -1.
4. Traverse both strings simultaneously.
5. Extract c1 and c2.
6. Check whether c1 already has a different target mapping.
7. Check whether c2 already has a different source mapping.
8. If either condition fails, return false.
9. Store both mappings.
10. Return true if the loop completes.

### Detailed Dry Run 1 – Valid Mapping

Input:

```text
s = "egg"
t = "add"
```

Initially:

```text
arr1 = all -1
arr2 = all -1
```

**Iteration 1**

```text
i = 0

c1 = 'e'
c2 = 'a'
```

ASCII values:

```text
'e' = 101
'a' = 97
```

Check source mapping:

```text
arr1[101] = -1
```

Character e is not mapped yet.

Check target mapping:

```text
arr2[97] = -1
```

Character a is not mapped yet.

Store:

```text
arr1[101] = 97
arr2[97] = 101
```

Mapping:

```text
e -> a
```

**Iteration 2**

```text
i = 1

c1 = 'g'
c2 = 'd'
```

ASCII values:

```text
'g' = 103
'd' = 100
```

Check:

```text
arr1[103] = -1
arr2[100] = -1
```

No previous mapping exists.

Store:

```text
arr1[103] = 100
arr2[100] = 103
```

Mapping:

```text
g -> d
```

**Iteration 3**

```text
i = 2

c1 = 'g'
c2 = 'd'
```

Check source mapping:

```text
arr1[103] = 100

Does 100 != 100?
No.
```

Check target mapping:

```text
arr2[100] = 103

Does 103 != 103?
No.
```

Both mappings are consistent.

Store the same mappings again.

The loop completes.

Output:

```text
true
```

### Detailed Dry Run 2 – Invalid Mapping

Input:

```text
s = "foo"
t = "bar"
```

**Iteration 1**

```text
f -> b
```

Store:

```text
arr1['f'] = 'b'
arr2['b'] = 'f'
```

**Iteration 2**

```text
o -> a
```

Store:

```text
arr1['o'] = 'a'
arr2['a'] = 'o'
```

**Iteration 3**

```text
o -> r
```

Check source mapping:

```text
arr1['o'] = 'a'
```

Current target:

```text
'r'
```

Compare:

```text
'a' != 'r'
```

The same source character o is being mapped to two different target characters.

Return:

```text
false
```

### Detailed Dry Run 3 – Reverse Mapping Failure

Input:

```text
s = "ab"
t = "cc"
```

**Iteration 1**

```text
a -> c
```

Store:

```text
arr1['a'] = 'c'
arr2['c'] = 'a'
```

**Iteration 2**

```text
b -> c
```

Source mapping check:

```text
arr1['b'] = -1
```

No conflict in the first direction.

Target mapping check:

```text
arr2['c'] = 'a'
```

Current source:

```text
'b'
```

Compare:

```text
'a' != 'b'
```

The reverse mapping is inconsistent.

Return:

```text
false
```

### Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n) | One traversal of both strings |
| Auxiliary Space | O(1) | Two fixed arrays of 128 integers |

This is the optimal approach for the ASCII constraint.

---

# 8. Approach Comparison

| Feature | Brute Force | HashMap | Mapping Arrays |
|---|---|---|---|
| Technique | Previous-position checking | Two HashMaps | Two arrays |
| Time | O(n²) | O(n) average | O(n) |
| Auxiliary Space | O(1) | O(k) | O(1) |
| One-to-one mapping | Yes | Yes | Yes |
| Best use | Understanding mapping rules | Flexible character sets | ASCII input |

**Final selection:** Two Mapping Arrays.

Reason: The ASCII character set has a fixed size, so direct array indexing provides O(1) lookup and fixed auxiliary space.

---

# 9. Important Concepts Learned

### 1. Isomorphic Strings

Two strings are isomorphic when their characters have a consistent one-to-one mapping.

### 2. Source-to-Target Mapping

```java
arr1[c1] = c2;
```

One source character must always map to the same target character.

### 3. Target-to-Source Mapping

```java
arr2[c2] = c1;
```

One target character cannot be assigned to two different source characters.

### 4. Consistency Checking

```java
if (arr1[c1] != -1 && arr1[c1] != c2)
```

Checks whether an existing mapping conflicts with the current mapping.

### 5. Reverse Consistency Checking

```java
if (arr2[c2] != -1 && arr2[c2] != c1)
```

Prevents multiple source characters from mapping to the same target character.

### 6. Character as Array Index

Java stores characters as numeric character values.

For ASCII:

```java
arr1[c1]
```

uses the character's numeric value as the array index.

### 7. Sentinel Values

`-1` represents an unmapped character.

Using a sentinel that cannot represent a valid character avoids ambiguity.

---

# 10. Common Mistakes

| Mistake | Why incorrect | Correct understanding |
|---|---|---|
| Using only one mapping array | Does not prevent reverse conflicts | Use two mappings |
| Checking only source mapping | Multiple source characters may map to one target | Check both directions |
| Not checking string lengths | Different lengths cannot be isomorphic | Return false first |
| Overwriting mappings without validation | Can break consistency | Validate before updating |
| Using character values as mapping values without understanding them | Confuses characters and array indexes | Character values are numeric codes |
| Using 0 as an unmapped marker without considering ASCII 0 | Ambiguous sentinel | Initialize with -1 |

---

# 11. Edge Cases

| Input s | Input t | Expected | Reason |
|---|---|---|---|
| `"egg"` | `"add"` | true | Consistent mapping |
| `"foo"` | `"bar"` | false | One source maps to multiple targets |
| `"paper"` | `"title"` | true | One-to-one mapping |
| `"ab"` | `"cc"` | false | Two sources map to one target |
| `"a"` | `"a"` | true | Single character mapping |
| `"ab"` | `"ba"` | true | Valid reverse mapping |
| `"aa"` | `"bb"` | true | Repeated characters map consistently |
| `"ab"` | `"a"` | false | Different lengths |

---

# 12. Interview Explanation

"I solve this problem using two-way character mapping.

First, I check whether both strings have equal lengths.

Then I maintain two mappings. The first stores source-to-target relationships, and the second stores target-to-source relationships.

For each pair of characters, I check whether an existing mapping conflicts with the current pair.

If either direction is inconsistent, I return false.

Otherwise, I update both mappings and continue.

If every character pair is processed successfully, I return true.

The time complexity is O(n), and auxiliary space complexity is O(1) because both mapping arrays have a fixed size of 128 for ASCII characters."

---

# 13. Revision Checklist

- [ ] Understand what isomorphic strings mean.
- [ ] Understand one-to-one mapping.
- [ ] Implement brute force using previous-position comparisons.
- [ ] Understand why one HashMap is insufficient.
- [ ] Understand why two HashMaps are required.
- [ ] Understand source-to-target mapping.
- [ ] Understand target-to-source mapping.
- [ ] Understand array indexing using ASCII values.
- [ ] Understand sentinel value -1.
- [ ] Dry-run `"egg"` and `"add"`.
- [ ] Dry-run `"foo"` and `"bar"`.
- [ ] Dry-run `"ab"` and `"cc"`.
- [ ] Explain O(n) time complexity.
- [ ] Explain O(1) auxiliary space.
- [ ] Solve independently without notes.

---

# 14. GitHub Commands

Run these commands from your repository root:

```bash
git add Strings/0205-Isomorphic-Strings.java Strings/0205-Isomorphic-Strings.md

git commit -m "Add LC 205 Isomorphic Strings solutions and notes"

git push origin main
```

If your current branch is not main, replace `main` with your actual branch name.

---

# 15. Final Learning Summary

| Item | Status |
|---|---|
| Brute Force | Documented |
| Better – Two HashMaps | Documented |
| Optimal – Two Mapping Arrays | Documented |
| Your HashMap approach | Included |
| Your Array approach | Included and improved sentinel |
| Detailed Dry Runs | 3 examples |
| Complexity Comparison | Completed |
| Edge Cases | Covered |
| Interview Explanation | Covered |

**Key takeaway:** Whenever a problem requires a one-to-one relationship between two sets of characters, think about maintaining mappings in both directions.

**Important submission note:** Submit only one `class Solution` containing the optimal implementation to LeetCode. Keep the three differently named classes in your GitHub learning file.
