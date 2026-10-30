
# LeetCode 151 — Reverse Words in a String

🔗 **Problem:** https://leetcode.com/problems/reverse-words-in-a-string/

**Topic:** Strings  
**Primary Pattern:** Two Pointers  
**Related Patterns:** String Manipulation, Read/Write Pointers  
**Language:** Java

---

## 1. Problem Statement

Given a string `s`, reverse the order of the words.

A word is a sequence of non-space characters.

The output must:
- Contain the words in reverse order.
- Have exactly one space between words.
- Have no leading spaces.
- Have no trailing spaces.

### Examples

| Input | Output |
|---|---|
| `"the sky is blue"` | `"blue is sky the"` |
| `"  hello world  "` | `"world hello"` |
| `"a good   example"` | `"example good a"` |

---

## 2. Pattern Identification

**Primary pattern: Two Pointers**

### Concepts used

1. Character arrays
2. Two-pointer swapping
3. Reverse traversal
4. String immutability
5. Word boundary detection
6. Read/write pointers
7. Space handling

### Important observation

Reversing the entire string reverses both:
- The order of words.
- The characters inside every word.

We need to reverse the characters inside each word again to restore the correct word spelling.

This gives us the main algorithm:

1. Reverse the entire string.
2. Reverse each individual word.
3. Remove extra spaces.

---

## 3. Approach 1 — Character Array + Two Pointers

### Idea

This is my original approach.

First, convert the string into a character array because Java strings are immutable.

Then:

1. Reverse the entire array.
2. Traverse the array.
3. Identify spaces to find word boundaries.
4. Reverse each word separately.
5. Remove extra spaces using `trim()` and `replaceAll()`.

### Java Code

```java
public void reverse(char[] arr, int i, int j) {
    while (i < j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        i++;
        j--;
    }
}

public String reverseWordsTwoPointers(String s) {
    char[] arr = s.toCharArray();

    int i = 0;
    int j = arr.length - 1;

    reverse(arr, i, j);

    int r = 0;

    for (int k = 0; k < arr.length; k++) {
        if (arr[k] == ' ') {
            reverse(arr, r, k - 1);
            r = k + 1;
        }
    }

    reverse(arr, r, j);

    return new String(arr).trim().replaceAll("\\s+", " ");
}
```

### Dry Run

Input:

```text
"the sky is blue"
```

**Step 1: Reverse the entire array**

```text
"eulb si yks eht"
```

**Step 2: Reverse individual words**

| Word before | Word after |
|---|---|
| eulb | blue |
| si | is |
| yks | sky |
| eht | the |

**Step 3: Final output**

```text
"blue is sky the"
```

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(n)`

### Advantages

- Uses the two-pointer swapping technique.
- Does not require splitting the string into words.
- Easy to understand and trace.

### Disadvantages

- Requires a separate space-normalization operation.
- Creates intermediate strings during normalization.

**Learning note:** This is a valid solution and a good implementation of the reverse-entire-string, then reverse-each-word technique.

---

## 4. Approach 2 — split() + String Concatenation

### Idea

1. Remove leading and trailing spaces.
2. Split the string into words.
3. Traverse the word array backward.
4. Concatenate words into a result string.

### Java Code

```java
public String reverseWordsSplitConcat(String s) {
    String[] words = s.trim().split("\\s+");

    String result = "";

    for (int i = words.length - 1; i >= 0; i--) {
        result = result + words[i];

        if (i != 0) {
            result = result + " ";
        }
    }

    return result;
}
```

### Complexity

- Time: `O(n²)` worst case.
- Auxiliary space: `O(n)`.

### Why is the time complexity O(n²)?

Java strings are immutable.

Every concatenation creates a new string and copies the previous result along with the new word.

For example:

```java
result = result + words[i];
```

As the result grows, more characters must be copied repeatedly.

In the worst case, this leads to:

`1 + 2 + 3 + ... + n`

Therefore, the time complexity can become `O(n²)`.

### Advantages

- Simple and beginner-friendly.
- Easy to understand.
- Uses familiar String operations.

### Disadvantages

- Repeated concatenation is inefficient.
- Creates multiple intermediate String objects.

**Learning note:** Avoid repeated String concatenation inside loops when building large strings.

---

## 5. Approach 3 — split() + StringBuilder

### Idea

This approach improves Approach 2.

Instead of repeatedly concatenating immutable strings, use a mutable `StringBuilder`.

1. Split the string into words.
2. Traverse the array backward.
3. Append each word to StringBuilder.
4. Append one space between words.
5. Convert StringBuilder into a String.

### Java Code

```java
public String reverseWordsStringBuilder(String s) {
    String[] words = s.trim().split("\\s+");

    StringBuilder sb = new StringBuilder();

    for (int i = words.length - 1; i >= 0; i--) {
        sb.append(words[i]);

        if (i > 0) {
            sb.append(" ");
        }
    }

    return sb.toString();
}
```

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(n)`

### Why is StringBuilder better?

| String concatenation | StringBuilder |
|---|---|
| Strings are immutable | Mutable sequence |
| Repeated copying | Efficient appending |
| Can take O(n²) time | O(n) total construction |
| Creates intermediate strings | Avoids repeated intermediate results |

### Advantages

- Easy to implement.
- Efficient string construction.
- Handles multiple spaces using `trim()` and `split("\\s+")`.

### Disadvantages

- Creates an array of words.
- Requires additional memory for the array and StringBuilder.

**Learning note:** StringBuilder is generally preferable to repeated String concatenation when constructing strings inside loops.

---

## 6. Approach 4 — Character Array + Read/Write Pointers

### Recommended Approach

This approach combines:
- Two-pointer reversal.
- Character-array manipulation.
- Read/write pointer technique.
- Space normalization during traversal.

### Idea

Instead of creating a word array or calling `trim()` and `replaceAll()`, process everything using the original character array.

### Step 1: Reverse the entire array

Example:

```text
Input:   "  the sky   is blue  "

After:   "  eulb si   yks eht  "
```

### Step 2: Initialize two pointers

```java
int read = 0;
int write = 0;
```

**Read pointer:**
- Reads characters from the array.
- Skips unnecessary spaces.
- Identifies word boundaries.

**Write pointer:**
- Places valid characters at the beginning of the array.
- Removes extra spaces.
- Maintains exactly one space between words.

### Step 3: Skip extra spaces

```java
while (read < arr.length && arr[read] == ' ') {
    read++;
}
```

This moves the read pointer forward until it finds a valid character or reaches the end.

### Step 4: Copy the current word

```java
while (read < arr.length && arr[read] != ' ') {
    arr[write] = arr[read];

    write++;
    read++;
}
```

The current word is copied into the beginning of the array.

### Step 5: Reverse the copied word

```java
reverse(arr, start, write - 1);
```

This restores the original spelling of the word.

### Step 6: Add one space between words

```java
if (write > 0) {
    arr[write++] = ' ';
}
```

This ensures that words are separated by exactly one space.

### Step 7: Return only the valid portion

```java
return new String(arr, 0, write);
```

The first `write` positions contain the final answer.

### Complexity

- Time: `O(n)`
- Auxiliary space: `O(n)`

### Advantages

- No `split()` operation.
- No regular expressions.
- No separate `trim()` operation.
- Handles leading, trailing, and multiple spaces during traversal.
- Uses one character array and two traversal pointers.

**Learning note:** This is the recommended implementation for understanding read/write pointer optimization.

---

## 7. Complete Optimal Java Code

```java
class Solution {

    public void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();

        reverse(arr, 0, arr.length - 1);

        int read = 0;
        int write = 0;

        while (read < arr.length) {

            while (read < arr.length && arr[read] == ' ') {
                read++;
            }

            if (read == arr.length) {
                break;
            }

            if (write > 0) {
                arr[write++] = ' ';
            }

            int start = write;

            while (read < arr.length && arr[read] != ' ') {
                arr[write] = arr[read];

                write++;
                read++;
            }

            reverse(arr, start, write - 1);
        }

        return new String(arr, 0, write);
    }
}
```

---

## 8. Understanding Space Complexity

This is an important interview concept.

### Why can't these Java solutions use O(1) auxiliary space?

Java strings are immutable.

For the character-array approaches:

```java
char[] arr = s.toCharArray();
```

This creates a new array containing all `n` characters.

Therefore, the array requires `O(n)` additional memory.

For the StringBuilder approach:

```java
StringBuilder sb = new StringBuilder();
```

The StringBuilder needs storage proportional to the output size.

Therefore, it also requires `O(n)` additional memory.

### Output space vs Auxiliary space

These are different concepts.

**Output space:**

Memory required to store the returned string.

**Auxiliary space:**

Additional working memory used by the algorithm, excluding the returned output.

For the read/write approach:

| Memory component | Space |
|---|---|
| Character array | O(n) |
| Read pointer | O(1) |
| Write pointer | O(1) |
| Temporary swap variable | O(1) |
| Returned String | O(n) output |

Even if we exclude the returned String, the character array still requires `O(n)` auxiliary space.

**Conclusion:** The read/write algorithm uses O(1) extra scalar variables, but the complete Java implementation uses O(n) auxiliary space because of `toCharArray()`.

---

## 9. Complete Complexity Comparison

| Approach | Time | Auxiliary Space |
|---|---|---|
| Character array + two pointers | O(n) | O(n) |
| split() + String concatenation | O(n²) worst case | O(n) |
| split() + StringBuilder | O(n) | O(n) |
| Character array + read/write pointers | O(n) | O(n) |

### Which one should I use?

| Purpose | Recommended approach |
|---|---|
| Understand two-pointer swapping | Approach 1 |
| Learn String concatenation limitations | Approach 2 |
| Learn StringBuilder | Approach 3 |
| Learn read/write pointer optimization | Approach 4 |
| Recommended LeetCode submission | Approach 4 |

---

## 10. Common Mistakes

1. Reversing the entire string but forgetting to reverse individual words.
2. Reversing words using incorrect starting and ending indices.
3. Forgetting to process the final word after the traversal loop.
4. Not handling leading spaces.
5. Not handling trailing spaces.
6. Leaving multiple spaces between words.
7. Using `String` concatenation repeatedly inside a loop.
8. Assuming StringBuilder provides O(1) auxiliary space.
9. Confusing output space with auxiliary space.
10. Forgetting that Java String objects are immutable.

---

## 11. Interview Explanation

"I use a two-pointer approach. First, I reverse the entire character array, which reverses the order of words and their characters. Then I use read and write pointers to skip unnecessary spaces and compact the valid characters. Whenever I finish copying a word, I reverse that word individually to restore its original spelling. Finally, I return only the valid portion of the array.

The time complexity is O(n), and the auxiliary space complexity is O(n) because Java's toCharArray() creates a copy of the input."

---

## 12. Revision Checklist

### Basic understanding
- [ ] Explain why reversing the entire string changes word order.
- [ ] Explain why individual words need to be reversed again.
- [ ] Understand why Java strings are immutable.
- [ ] Explain the purpose of the reverse helper method.

### Two-pointer technique
- [ ] Identify starting and ending indices of each word.
- [ ] Understand how the pointers move.
- [ ] Trace the entire reversal process.

### Read/write pointer technique
- [ ] Explain the read pointer.
- [ ] Explain the write pointer.
- [ ] Understand how extra spaces are removed.
- [ ] Explain why the array can be modified safely.

### Complexity
- [ ] Explain why String concatenation can take O(n²).
- [ ] Explain why StringBuilder takes O(n) time.
- [ ] Explain why the character array takes O(n) auxiliary space.
- [ ] Distinguish output space from auxiliary space.

### Practice
- [ ] Implement Approach 1 without looking at notes.
- [ ] Implement Approach 3 using StringBuilder.
- [ ] Implement Approach 4 from memory.
- [ ] Explain the optimal approach in an interview.

---

## 13. Key Takeaway

This problem combines several important String DSA concepts:

1. Reversal using two pointers.
2. Identifying word boundaries.
3. Java String immutability.
4. Efficient string construction using StringBuilder.
5. Read/write pointers for in-place array compaction.

**Most important learning:** Reversing the entire array and then reversing individual words is a powerful technique. Combining it with read/write pointers allows you to handle extra spaces without separate splitting or regex operations.



# 125. Valid Palindrome

**LeetCode:** https://leetcode.com/problems/valid-palindrome/

**Difficulty:** Easy

**Topic:** Strings

**Pattern:** Two Pointers – Opposite Direction

**Language:** Java

---

## 1. Problem Description

Given a string `s`, determine whether it is a palindrome.

A palindrome is a string that reads the same forward and backward.

For this problem:

- Ignore uppercase and lowercase differences.
- Consider only alphanumeric characters (letters and digits).
- Ignore spaces and punctuation.

Return `true` if the string is a palindrome; otherwise, return `false`.

### Examples

**Example 1**

Input:
```text
"A man, a plan, a canal: Panama"
```

Output:
```text
true
```

Explanation:

After removing spaces and punctuation and ignoring case:

`amanaplanacanalpanama`

It reads the same forward and backward.

**Example 2**

Input:
```text
"race a car"
```

Output:
```text
false
```

Explanation:

After removing spaces:

`raceacar`

It does not read the same forward and backward.

**Example 3**

Input:
```text
" "
```

Output:
```text
true
```

Explanation:

There are no alphanumeric characters to compare.
An empty cleaned string is considered a palindrome.

---

## 2. Important Concepts

### A. Palindrome

A palindrome is a sequence that remains the same when reversed.

Examples:

- madam
- racecar
- level
- 121

### B. Alphanumeric Characters

Alphanumeric characters include:

- A-Z
- a-z
- 0-9

Java method:

`Character.isLetterOrDigit(ch)`

Returns:
- `true` if the character is a letter or digit.
- `false` otherwise.

### C. Case Conversion

Java method:

`Character.toLowerCase(ch)`

Converts an individual character to lowercase.

### D. Two Pointers

Two pointers start at opposite ends of the string:

- `i` starts at index 0.
- `j` starts at the last index.

Move both pointers toward the center after comparing valid characters.

---

## 3. Approach 1: Brute Force

### Idea

1. Convert the string to lowercase.
2. Remove all non-alphanumeric characters.
3. Reverse the cleaned string.
4. Compare the cleaned string with its reversed version.
5. If both are equal, return true.

### Code

```java
class Solution {
    public boolean isPalindrome(String s) {

        s = s.toLowerCase();

        StringBuilder cleaned = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                cleaned.append(ch);
            }
        }

        String original = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        return original.equals(reversed);
    }
}
```

### Dry Run

Input:

`"A man, a plan, a canal: Panama"`

Step 1: Convert to lowercase.

`"a man, a plan, a canal: panama"`

Step 2: Remove spaces and punctuation.

`"amanaplanacanalpanama"`

Step 3: Reverse the cleaned string.

`"amanaplanacanalpanama"`

Step 4: Compare.

Original = Reversed

Therefore, return `true`.

### Complexity Analysis

- Time Complexity: O(n)
- Auxiliary Space: O(n)

Why?

- Traversing the input takes O(n).
- Creating the cleaned string takes O(n) space.
- Reversing and comparing take O(n) time.

---

## 4. Approach 2: Two Pointers

### Idea

Instead of creating another cleaned string, compare characters directly from both ends.

1. Initialize `i = 0`.
2. Initialize `j = s.length() - 1`.
3. Skip non-alphanumeric characters.
4. Compare the left and right characters.
5. If they differ, return false.
6. If they match, move both pointers inward.
7. If all comparisons succeed, return true.

### Code

```java
class Solution {
    public boolean isPalindrome(String s) {

        s = s.toLowerCase();

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
```

### Dry Run

Input:

`"A man, a plan, a canal: Panama"`

After lowercase conversion:

`"a man, a plan, a canal: panama"`

| Iteration | Left character | Right character | Action |
|---|---|---|---|
| 1 | a | a | Match |
| 2 | m | m | Match |
| 3 | a | a | Match |
| 4 | n | n | Match |
| 5 | a | a | Match |
| 6 | p | p | Match |
| 7 | l | l | Match |
| 8 | a | a | Match |
| 9 | n | n | Match |

Spaces and punctuation are skipped.

All valid character pairs match.

Output: `true`

### Complexity Analysis

- Time Complexity: O(n)
- Auxiliary Space: O(n)

Why is auxiliary space O(n)?

Because:

`s = s.toLowerCase();`

creates a new lowercase String.

The two-pointer traversal itself uses O(1) extra space.

---

## 5. Approach 3: Optimized Two Pointers

### Idea

This is the most space-efficient approach.

Instead of converting the entire string to lowercase, convert only the two characters being compared.

Use:

`Character.toLowerCase(s.charAt(i))`

and

`Character.toLowerCase(s.charAt(j))`

This avoids creating a separate lowercase String.

### Code

```java
class Solution {
    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }

            char left = Character.toLowerCase(s.charAt(i));
            char right = Character.toLowerCase(s.charAt(j));

            if (left != right) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
```

### Complexity Analysis

- Time Complexity: O(n)
- Auxiliary Space: O(1)

Why?

- Each pointer moves through the string at most once.
- No additional string, array, or collection is created.
- Only a fixed number of variables are used.

---

## 6. Approach Comparison

| Approach | Time | Auxiliary Space |
|---|---|---|
| Brute Force | O(n) | O(n) |
| Two Pointers with toLowerCase() | O(n) | O(n) |
| Optimized Two Pointers | O(n) | O(1) |

**Best approach:** Optimized Two Pointers.

It achieves linear time complexity and constant auxiliary space.

---

## 7. Common Mistakes

1. Initializing `j = s.length()` instead of `s.length() - 1`.
2. Forgetting to skip spaces and punctuation.
3. Comparing characters without handling uppercase and lowercase.
4. Returning true immediately after finding one matching pair.
5. Forgetting to return false when a mismatch occurs.
6. Returning false after the loop instead of true.
7. Using `while` instead of `if` for a single character comparison.
8. Forgetting that `toLowerCase()` creates a new String.

---

## 8. Important Java Methods

| Method | Purpose |
|---|---|
| `s.length()` | Returns string length |
| `s.charAt(i)` | Returns character at index i |
| `Character.isLetterOrDigit(ch)` | Checks whether a character is alphanumeric |
| `Character.toLowerCase(ch)` | Converts a character to lowercase |
| `s.toLowerCase()` | Converts the entire string to lowercase |
| `StringBuilder.reverse()` | Reverses a StringBuilder |
| `String.equals()` | Compares string contents |

---

## 9. Interview Explanation

"I use the two-pointer technique to check whether the given string is a palindrome.

I initialize one pointer at the beginning and another at the end.

I skip all non-alphanumeric characters and compare the remaining characters without considering case.

If any pair does not match, I immediately return false.

Otherwise, I move both pointers toward the center.

If all valid character pairs match, I return true.

The optimized solution takes O(n) time and O(1) auxiliary space."

---

## 10. Revision Checklist

- [ ] Understand palindrome logic.
- [ ] Understand alphanumeric characters.
- [ ] Understand `Character.isLetterOrDigit()`.
- [ ] Understand `Character.toLowerCase()`.
- [ ] Understand opposite-direction two pointers.
- [ ] Solve using brute force.
- [ ] Solve using two pointers.
- [ ] Explain why the optimized solution uses O(1) auxiliary space.
- [ ] Explain the solution in an interview.

---

## 11. Key Takeaway

**Pattern:** Two Pointers – Opposite Direction

**Core logic:**

Compare the first and last valid characters, then move both pointers toward the center.

**Remember:**

If any valid pair does not match, return false immediately.

If all pairs match, return true.
```
