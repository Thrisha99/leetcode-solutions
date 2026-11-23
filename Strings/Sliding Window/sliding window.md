# LeetCode 424 – Longest Repeating Character Replacement

## 1. Problem Information

| Property | Details |
|---|---|
| Problem Number | 424 |
| Problem Name | Longest Repeating Character Replacement |
| Platform | LeetCode |
| Difficulty | Medium |
| Language | Java |
| Topic | Strings |
| Pattern | Variable Size Sliding Window |
| Time Complexity | O(n) |
| Space Complexity | O(1) |

**Problem Link:** https://leetcode.com/problems/longest-repeating-character-replacement/

### Problem Statement

Given a string `s` consisting of uppercase English letters and an integer `k`, we can replace at most `k` characters with any uppercase English character.

Return the length of the longest substring containing the same character after performing at most `k` replacements.

### Important Terminology

- **Substring:** A continuous sequence of characters.
- **Replacement:** Changing one character into another character.
- **Window:** A continuous portion of the string between two pointers.
- **Frequency:** Number of occurrences of a character.
- **Sliding Window:** A technique that expands and shrinks a continuous range using two pointers.

---

## 2. Examples

### Example 1

Input:

```text
s = "ABAB"
k = 2
```

Output:

```text
4
```

Explanation:

Replace both B characters with A.

```text
ABAB
AAAA
```

The entire string becomes the same character.

### Example 2

Input:

```text
s = "AABABBA"
k = 1
```

Output:

```text
4
```

Explanation:

The substring `"AABA"` can become `"AAAA"` by replacing B with A.

The maximum possible length is 4.

### Example 3

Input:

```text
s = "AAAA"
k = 0
```

Output:

```text
4
```

Explanation:

All characters are already identical. No replacements are required.

---

## 3. Core Concept – The Most Important Formula

The main idea is to identify how many replacements are required to make the current window contain only one character.

**Formula:**

```text
Required Replacements = Window Length - Maximum Character Frequency
```

Mathematically:

\[
Replacements = WindowLength - MaxFrequency
\]

### Why does this formula work?

Consider:

```text
A A B A B
```

Frequency:

| Character | Frequency |
|---|---:|
| A | 3 |
| B | 2 |

Window length = 5.

Maximum frequency = 3.

We can keep the three A characters and replace the remaining two B characters.

Therefore:

```text
Required replacements = 5 - 3 = 2
```

If `k = 2`, the window is valid.

If `k = 1`, the window is invalid.

### Window validity condition

```text
windowLength - maxFrequency <= k
```

### Window invalidity condition

```text
windowLength - maxFrequency > k
```

This invalidity condition is used in the `while` loop.

**Important:** Equality is valid. If replacements required are exactly equal to `k`, the window must not shrink.

---

# 4. Approaches – Brute Force to Optimal

## Approach 1: Brute Force – Generate Every Substring

### Idea

Generate all possible substrings.

For every substring:

1. Count the frequency of each character.
2. Find the highest character frequency.
3. Calculate the required replacements.
4. Check whether replacements are at most `k`.
5. Update the maximum valid substring length.

### Example

```text
s = "AAB"
k = 1
```

Possible substrings:

| Substring | Length | Maximum Frequency | Replacements | Valid? |
|---|---:|---:|---:|---|
| A | 1 | 1 | 0 | Yes |
| AA | 2 | 2 | 0 | Yes |
| AAB | 3 | 2 | 1 | Yes |
| A | 1 | 1 | 0 | Yes |
| AB | 2 | 1 | 1 | Yes |
| B | 1 | 1 | 0 | Yes |

Longest valid substring length = 3.

### Complexity

| Type | Complexity |
|---|---|
| Time | O(n³) |
| Space | O(1) |

Reason:

- O(n²) possible substrings.
- Counting frequencies for every substring can take O(n).
- Therefore, O(n³).

---

## Approach 2: Improved Brute Force – Fix Starting Index and Expand

### Idea

Instead of recounting the frequency of every substring from scratch, fix the starting index and expand the ending index.

For each starting index:

1. Initialize a frequency array.
2. Move the ending index forward.
3. Increase the frequency of the newly included character.
4. Update the maximum frequency.
5. Calculate the required replacements.
6. If valid, update the answer.

### Why is it better?

The frequency array is updated incrementally.

We do not recount all characters every time we create a new substring.

### Complexity

| Type | Complexity |
|---|---|
| Time | O(n²) |
| Space | O(1) |

---

## Approach 3: Optimal – Variable Size Sliding Window

### Idea

Instead of checking every possible substring independently, use two pointers:

- `left` represents the starting index.
- `right` represents the ending index.

Expand the window using `right`.

If the window requires more than `k` replacements, shrink it using `left`.

Keep track of the maximum window length.

### Steps

1. Initialize `left = 0`.
2. Create a frequency array of size 26.
3. Initialize `maxFrequency = 0`.
4. Initialize `maxLength = 0`.
5. Move `right` from 0 to the end of the string.
6. Add the current character to the frequency array.
7. Update `maxFrequency`.
8. Calculate the current window length.
9. While replacements exceed `k`, remove the leftmost character and move `left`.
10. Update `maxLength`.
11. Return `maxLength`.

### Complexity

| Type | Complexity |
|---|---|
| Time | O(n) |
| Space | O(1) |

Why O(1) space?

The frequency array always has exactly 26 elements, regardless of the input length.

---

# 5. Java Code – Final Optimized Solution

```java
class Solution {
    public int characterReplacement(String s, int k) {

        int left = 0;
        int[] frequency = new int[26];

        int maxFrequency = 0;
        int windowLength = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            frequency[s.charAt(right) - 'A']++;

            maxFrequency = Math.max(
                maxFrequency,
                frequency[s.charAt(right) - 'A']
            );

            windowLength = right - left + 1;

            while (windowLength - maxFrequency > k) {

                frequency[s.charAt(left) - 'A']--;

                left++;

                windowLength = right - left + 1;
            }

            maxLength = Math.max(maxLength, windowLength);
        }

        return maxLength;
    }
}
```

---

# 6. Variable Explanation

| Variable | Purpose |
|---|---|
| `left` | Starting index of the current window |
| `right` | Ending index of the current window |
| `frequency` | Stores the frequency of each uppercase character |
| `maxFrequency` | Highest frequency observed during window expansion |
| `windowLength` | Length of the current window |
| `maxLength` | Longest window length found |
| `k` | Maximum number of allowed replacements |
| `s` | Input string |

### Character-to-Array Index Conversion

```java
frequency[s.charAt(right) - 'A']++;
```

Why subtract `'A'`?

Because we need to convert uppercase characters into array indexes.

| Character | Index |
|---|---:|
| A | 0 |
| B | 1 |
| C | 2 |
| D | 3 |
| E | 4 |
| ... | ... |
| Z | 25 |

This lets us use an array of size 26 instead of a HashMap.

---

# 7. Detailed Dry Run – AABABBA

### Input

```text
s = "AABABBA"
k = 1
```

### Initial values

| Variable | Value |
|---|---:|
| left | 0 |
| maxFrequency | 0 |
| windowLength | 0 |
| maxLength | 0 |
| k | 1 |

String with indexes:

```text
Index:  0  1  2  3  4  5  6
String: A  A  B  A  B  B  A
```

We will move `right` from index 0 to index 6.

## Iteration 1 – right = 0

Current character = A.

Frequency update:

```text
A = 1
B = 0
```

Maximum frequency:

```text
maxFrequency = 1
```

Window length:

\[
0-0+1=1
\]

Current window:

```text
A
```

Validity check:

\[
1-1>1
\]

```text
0 > 1 = false
```

The while loop does not execute.

Update answer:

```text
maxLength = max(0, 1) = 1
```

## Iteration 2 – right = 1

Current character = A.

Frequency:

```text
A = 2
B = 0
```

Maximum frequency = 2.

Window length:

\[
1-0+1=2
\]

Current window:

```text
AA
```

Validity check:

\[
2-2>1
\]

```text
0 > 1 = false
```

No shrinking.

Update answer:

```text
maxLength = max(1, 2) = 2
```

## Iteration 3 – right = 2

Current character = B.

Frequency:

```text
A = 2
B = 1
```

Maximum frequency = 2.

Window length:

\[
2-0+1=3
\]

Current window:

```text
AAB
```

Validity check:

\[
3-2>1
\]

```text
1 > 1 = false
```

No shrinking.

Update answer:

```text
maxLength = max(2, 3) = 3
```

One B can be replaced with A.

## Iteration 4 – right = 3

Current character = A.

Frequency:

```text
A = 3
B = 1
```

Maximum frequency = 3.

Window length:

\[
3-0+1=4
\]

Current window:

```text
AABA
```

Validity check:

\[
4-3>1
\]

```text
1 > 1 = false
```

No shrinking.

Update answer:

```text
maxLength = max(3, 4) = 4
```

One B can be replaced with A.

Current best window:

```text
AABA → AAAA
```

## Iteration 5 – right = 4

Current character = B.

Frequency after adding:

```text
A = 3
B = 2
```

Maximum frequency = 3.

Window length:

\[
4-0+1=5
\]

Current window:

```text
AABAB
```

Validity check:

\[
5-3>1
\]

```text
2 > 1 = true
```

The window is invalid. Enter the while loop.

### First execution of while loop

Current left index = 0.

Outgoing character = A.

Decrease its frequency:

```text
A = 2
B = 2
```

Move left:

```text
left = 1
```

Recalculate window length:

\[
4-1+1=4
\]

New window:

```text
ABAB
```

Check again:

\[
4-3>1
\]

```text
1 > 1 = false
```

Stop the while loop.

Update answer:

```text
maxLength = max(4, 4) = 4
```

**Important:** `maxFrequency` remains 3 even though the actual maximum frequency in `"ABAB"` is 2. This is intentional.

## Iteration 6 – right = 5

Current character = B.

Frequency after adding:

```text
A = 2
B = 3
```

Maximum frequency = 3.

Window length:

\[
5-1+1=5
\]

Current window:

```text
ABABB
```

Validity check:

\[
5-3>1
\]

```text
2 > 1 = true
```

Enter while loop.

### First execution

Outgoing character = A at index 1.

Decrease its frequency:

```text
A = 1
B = 3
```

Move left:

```text
left = 2
```

Recalculate length:

\[
5-2+1=4
\]

New window:

```text
BABB
```

Check again:

\[
4-3>1
\]

```text
1 > 1 = false
```

Stop.

Update answer:

```text
maxLength = max(4, 4) = 4
```

## Iteration 7 – right = 6

Current character = A.

Frequency after adding:

```text
A = 2
B = 3
```

Maximum frequency = 3.

Window length:

\[
6-2+1=5
\]

Current window:

```text
BABBA
```

Validity check:

\[
5-3>1
\]

```text
2 > 1 = true
```

Enter while loop.

### First execution

Outgoing character = B at index 2.

Decrease its frequency:

```text
A = 2
B = 2
```

Move left:

```text
left = 3
```

Recalculate length:

\[
6-3+1=4
\]

New window:

```text
ABBA
```

Check again:

\[
4-3>1
\]

```text
1 > 1 = false
```

Stop.

Update answer:

```text
maxLength = max(4, 4) = 4
```

### Complete dry-run summary

| Right | Character | Window before shrinking | Window length | maxFrequency | Calculated replacements | Shrinking? | maxLength |
|---:|---|---|---:|---:|---:|---|---:|
| 0 | A | A | 1 | 1 | 0 | No | 1 |
| 1 | A | AA | 2 | 2 | 0 | No | 2 |
| 2 | B | AAB | 3 | 2 | 1 | No | 3 |
| 3 | A | AABA | 4 | 3 | 1 | No | 4 |
| 4 | B | AABAB | 5 | 3 | 2 | Yes | 4 |
| 5 | B | ABABB | 5 | 3 | 2 | Yes | 4 |
| 6 | A | BABBA | 5 | 3 | 2 | Yes | 4 |

### Final output

```text
4
```

---

# 8. Detailed Explanation of the While Loop

This is the most important part of the problem.

```java
while (windowLength - maxFrequency > k) {

    frequency[s.charAt(left) - 'A']--;

    left++;

    windowLength = right - left + 1;
}
```

### Purpose

The while loop shrinks the current window when the number of required replacements exceeds the allowed replacements.

### Statement 1

```java
while (windowLength - maxFrequency > k)
```

Checks whether the current window requires too many replacements.

Example:

```text
Window = AABAB
Window Length = 5
Maximum Frequency = 3
k = 1
```

Calculation:

```text
5 - 3 = 2
```

Since 2 > 1, the window is invalid.

### Statement 2

```java
frequency[s.charAt(left) - 'A']--;
```

Removes the leftmost character from the frequency count.

If the leftmost character is A:

```text
A frequency decreases by 1.
```

### Statement 3

```java
left++;
```

Moves the starting pointer one position forward.

This removes the leftmost character from the current window.

### Statement 4

```java
windowLength = right - left + 1;
```

Recalculates the window length after shrinking.

### Why while instead of if?

Because removing one character might not be enough.

Example:

```text
Window Length = 6
Maximum Frequency = 3
k = 2
```

Initially:

```text
6 - 3 = 3
```

Invalid.

After removing one character:

```text
5 - 3 = 2
```

Now valid.

But consider a different case:

```text
Window Length = 7
Maximum Frequency = 3
k = 1
```

After one removal:

```text
6 - 3 = 3
```

Still invalid.

After another removal:

```text
5 - 3 = 2
```

Still invalid.

After another removal:

```text
4 - 3 = 1
```

Now valid.

Therefore, `while` is needed to repeat shrinking until the condition becomes false.

---

# 9. Important Optimization – Why We Don't Decrease maxFrequency

This was one of the most important concepts in your learning process.

In the optimized approach:

```java
maxFrequency = Math.max(
    maxFrequency,
    frequency[s.charAt(right) - 'A']
);
```

We update `maxFrequency` only when expanding the window.

We do not recalculate it after removing a character.

### Why?

Consider the window:

```text
AABAB
```

Frequency:

```text
A = 3
B = 2
```

Therefore:

```text
maxFrequency = 3
```

After shrinking, the window becomes:

```text
ABAB
```

Actual frequencies:

```text
A = 2
B = 2
```

But the stored maximum frequency remains 3.

This is called a **stale maximum frequency**.

### Is this incorrect?

No.

This optimization is safe for finding the maximum window length.

The algorithm may temporarily retain a window that would not be valid according to its exact current frequencies. However, such a window cannot produce a new maximum length that was not already achievable by an earlier genuinely valid window.

Therefore, the final maximum length remains correct.

**Remember:**

- Exact-window approach: Recalculate the current maximum frequency after shrinking if needed.
- Optimized approach: Do not decrease the historical maximum frequency.

Do not mix these two approaches.

---

# 10. Mistakes I Made While Coding

This section records the mistakes made during implementation and how they were corrected.

## Mistake 1 – Incorrect while condition

My first attempt:

```java
while (r >= k)
```

### Why was it wrong?

This only compares window length with `k`.

But the number of replacements depends on how frequently the most common character appears.

### Correction

```java
while (windowLength - maxFrequency > k)
```

### Learning

Always calculate replacements using:

```text
Window Length - Maximum Frequency
```

---

## Mistake 2 – Confusing maxFrequency with maxLength

My attempt:

```java
maxLength = Math.max(maxLength, maxFrequency);
```

### Why was it wrong?

`maxFrequency` represents the number of occurrences of the most frequent character.

It does not represent the length of the window.

Example:

```text
Window = AAB
Window Length = 3
Maximum Frequency = 2
```

The answer for this window is 3, not 2.

### Correction

```java
maxLength = Math.max(maxLength, windowLength);
```

### Learning

Always distinguish:

- `maxFrequency`: Most frequent character count.
- `maxLength`: Longest valid window length.

---

## Mistake 3 – Updating the answer inside the while loop

My attempt:

```java
while (windowLength - maxFrequency > k) {
    frequency[s.charAt(left) - 'A']--;
    left++;

    maxLength = Math.max(maxLength, maxFrequency);
}
```

### Why was it wrong?

The answer should be considered after the window has finished shrinking.

Also, `maxFrequency` is not the current window length.

### Correction

Move the answer update outside the while loop:

```java
maxLength = Math.max(maxLength, windowLength);
```

---

## Mistake 4 – Incorrectly updating maxFrequency while shrinking

My attempt:

```java
maxFrequency = Math.max(
    maxFrequency,
    frequency[s.charAt(left) - 'A']
);
```

### Why was it wrong?

This was placed inside the shrinking loop and used the outgoing character after changing its frequency.

More importantly, the optimized approach does not recalculate the maximum frequency during shrinking.

### Correction

Remove the maximum frequency update from inside the while loop.

Update it only when adding a new character.

### Learning

The optimized approach intentionally keeps a historical maximum frequency.

---

## Mistake 5 – Not recalculating window length after shrinking

My attempt initially calculated the window length only before entering the while loop.

### Why was it wrong?

When `left` moves forward, the window becomes smaller.

Therefore, the length must be recalculated.

### Correction

```java
left++;

windowLength = right - left + 1;
```

---

## Mistake 6 – Missing return statement

My initial implementation did not return the answer.

### Correction

```java
return maxLength;
```

### Learning

Since the method return type is `int`, it must return an integer value.

---

# 11. Common Mistakes to Avoid in Interviews

| Mistake | Correct understanding |
|---|---|
| Using `>= k` instead of `> k` | Exactly k replacements are allowed |
| Using `maxFrequency` as the answer | Use `windowLength` |
| Using `if` instead of `while` | Shrink repeatedly until the condition is false |
| Updating answer inside while | Update after shrinking |
| Decreasing stale `maxFrequency` | Do not decrease it in optimized approach |
| Forgetting frequency decrement | Remove the outgoing character from frequency |
| Forgetting to move left | Increment the left pointer |
| Forgetting to recalculate window length | Recalculate after shrinking |
| Confusing substring and subsequence | A substring must be continuous |

---

# 12. Edge Cases

| Input | k | Expected Output | Reason |
|---|---:|---:|---|
| `"A"` | 0 | 1 | Single character |
| `"AAAA"` | 0 | 4 | All characters identical |
| `"ABCDE"` | 0 | 1 | No replacements allowed |
| `"ABCDE"` | 1 | 2 | One replacement can make two adjacent characters equal |
| `"ABAB"` | 2 | 4 | Entire string can become identical |
| `"AABABBA"` | 1 | 4 | Standard example |
| `"AAAAAB"` | 1 | 6 | Replace B with A |
| `"ABBB"` | 0 | 3 | Existing longest repeated substring |

---

# 13. Interview Explanation – How to Explain the Solution

"I would solve this problem using a variable-size sliding window. I maintain two pointers, left and right, to represent the current substring, along with a frequency array of size 26 because the input contains uppercase English letters. As I move the right pointer, I increase the frequency of the current character and update the maximum frequency observed. The number of replacements required to make the current window contain only one character is calculated as window length minus maximum frequency. If this value exceeds k, I shrink the window from the left by decreasing the frequency of the outgoing character and moving the left pointer forward until the condition is satisfied. After that, I update the maximum window length. I use a historical maximum frequency and do not decrease it while shrinking, which allows the optimized sliding-window solution to run in O(n) time with O(1) auxiliary space."

---

# 14. Complexity Analysis

### Time Complexity: O(n)

- The right pointer moves from index 0 to n - 1.
- The left pointer also moves forward at most n times.
- Every character enters the window once and leaves it at most once.
- Frequency array operations take constant time.

Therefore:

\[
O(n+n)=O(n)
\]

### Space Complexity: O(1)

- The frequency array has 26 elements.
- No additional data structure grows with the input size.

Therefore:

\[
O(26)=O(1)
\]

---

# 15. Pattern Recognition – When to Use This Technique

Look for these clues in a problem:

- Find the longest substring.
- The substring must be continuous.
- At most k operations are allowed.
- Characters can be replaced.
- Character frequencies matter.
- The window needs to expand and shrink.

These clues suggest a **Variable Size Sliding Window with Frequency Counting**.

---

# 16. Revision Checklist

### Concepts

- [ ] Understand substring vs subsequence.
- [ ] Understand the frequency array.
- [ ] Understand character-to-index conversion.
- [ ] Understand the replacement formula.
- [ ] Understand the validity condition.
- [ ] Understand variable-size sliding window.
- [ ] Understand stale maximum frequency.

### Implementation

- [ ] Initialize left pointer.
- [ ] Create frequency array.
- [ ] Expand right pointer.
- [ ] Update frequency.
- [ ] Update maxFrequency.
- [ ] Calculate windowLength.
- [ ] Shrink using while.
- [ ] Decrease outgoing character frequency.
- [ ] Move left pointer.
- [ ] Recalculate window length.
- [ ] Update maxLength.
- [ ] Return maxLength.

### Revision Schedule

| Revision | Task | Status |
|---|---|---|
| Attempted | Implement without help | Completed |
| 3-Day Revision | Write optimal code independently | Pending |
| 7-Day Revision | Explain while loop and stale max | Pending |
| 30-Day Revision | Solve without referring to notes | Pending |

---

# 17. Final Quick Revision Notes

```text
Problem: 424. Longest Repeating Character Replacement

Pattern:
Variable Size Sliding Window

Formula:
Replacements = Window Length - Maximum Frequency

Valid:
windowLength - maxFrequency <= k

Invalid:
windowLength - maxFrequency > k

Frequency:
frequency[character - 'A']++

Shrink:
frequency[s.charAt(left) - 'A']--
left++

Window Length:
right - left + 1

Answer:
maxLength = Math.max(maxLength, windowLength)

Time:
O(n)

Space:
O(1)

Important:
Do not decrease maxFrequency inside the shrinking loop
in the optimized approach.
```

**Main learning from this problem:** The most important part was understanding the difference between the current window length, maximum character frequency, and maximum answer length. Once these three values became clear, the sliding-window implementation followed naturally.