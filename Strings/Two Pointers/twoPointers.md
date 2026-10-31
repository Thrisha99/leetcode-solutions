
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

# 557. Reverse Words in a String III

| Property | Details |
|---|---|
| LeetCode Number | 557 |
| Problem Name | Reverse Words in a String III |
| Difficulty | Easy |
| Topic | Strings |
| Pattern | Two Pointers |
| Sub-pattern | In-place Reversal |
| Language | Java |
| Time Complexity | O(n) |
| Auxiliary Space | O(n) |

**Problem Link:** https://leetcode.com/problems/reverse-words-in-a-string-iii/

---

# 1. Problem Description

Given a string `s`, reverse the characters of each word
in the sentence while preserving the original order
of words and spaces.

A word is defined as a sequence of non-space characters.

### Examples

**Example 1**

Input:
```text
s = "Let's code"
```

Output:
```text
"s'teL edoc"
```

Explanation:

- "Let's" becomes "s'teL".
- "code" becomes "edoc".
- The order of words remains unchanged.

**Example 2**

Input:
```text
s = "God Ding"
```

Output:
```text
"doG gniD"
```

**Example 3**

Input:
```text
s = "Hello"
```

Output:
```text
"olleH"
```

---

# 2. Understand the Problem

The important requirement is that we must reverse each
individual word, NOT the entire sentence.

Consider:

```text
Input:  "Let's code in Java"

Output: "s'teL edoc ni avaJ"
```

Observe:

1. Word order remains the same.
2. Characters inside each word are reversed.
3. Spaces remain in their original positions.

---

# 3. Important Concepts

## A. String Immutability

In Java, Strings are immutable.

This means we cannot directly modify individual
characters inside an existing String.

For example:

```java
String s = "hello";
```

We cannot assign a new character using:

```java
s.charAt(0) = 'H';
```

Instead, we use a mutable character array:

```java
char[] ch = s.toCharArray();
```

Now we can modify individual characters.

## B. Two Pointers

Two pointers are used to reverse a word.

- Left pointer starts at the beginning.
- Right pointer starts at the end.
- Swap the characters.
- Move left forward.
- Move right backward.

Example:

```text
Original:  h e l l o

Step 1:    o e l l h

Step 2:    o l l e h

Result:    o l l e h
```

## C. Word Boundaries

A space indicates the end of the current word.

When we encounter a space:

- The word starts at `start`.
- The word ends at `i - 1`.
- Reverse the characters between these indexes.
- Update `start = i + 1`.

The final word needs separate handling because
there is no space after it.

---

# 4. Approach 1: Brute Force

## Technique

String splitting + StringBuilder.

## Idea

1. Split the sentence into individual words.
2. Traverse each word.
3. Reverse each word using StringBuilder.
4. Join the reversed words with spaces.
5. Return the result.

## Code

```java
class Solution {
    public String reverseWords(String s) {

        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            StringBuilder word =
                new StringBuilder(words[i]);

            result.append(word.reverse());

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}
```

## Dry Run

Input:

```text
"Let's code"
```

Step 1: Split the sentence.

```text
words[0] = "Let's"
words[1] = "code"
```

Step 2: Reverse each word.

```text
"Let's" → "s'teL"
"code"  → "edoc"
```

Step 3: Join the reversed words.

```text
"s'teL edoc"
```

Output:

```text
"s'teL edoc"
```

## Complexity Analysis

Time Complexity: O(n)

- Splitting takes O(n).
- Reversing all words takes O(n) total.
- Building the result takes O(n).

Auxiliary Space: O(n)

- Split creates an array of words.
- StringBuilder stores the result.

---

# 5. Approach 2: Two Pointers + Helper Method

## Technique

Character Array + Two Pointers.

## Idea

Instead of splitting the sentence, traverse the
character array directly.

Whenever a space is encountered, reverse the word
before that space.

Use a helper method to perform the reversal.

### Step-by-step algorithm

1. Convert the String into a character array.
2. Initialize `start = 0`.
3. Traverse the array using `i`.
4. If a space is found, reverse from `start` to `i - 1`.
5. Update `start = i + 1`.
6. After the loop, reverse the last word.
7. Convert the character array back into a String.

## Code

```java
class Solution {

    public void reverse(char[] ch, int i, int j) {

        while (i < j) {

            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;

            i++;
            j--;
        }
    }

    public String reverseWords(String s) {

        char[] ch = s.toCharArray();

        int start = 0;

        for (int i = 0; i < ch.length; i++) {

            if (ch[i] == ' ') {

                reverse(ch, start, i - 1);

                start = i + 1;
            }
        }

        // Reverse the last word
        reverse(ch, start, ch.length - 1);

        return new String(ch);
    }
}
```

## Detailed Dry Run

Input:

```text
"Let's code"
```

Character indexes:

```text
 L  e  t  '  s     c  o  d  e
 0  1  2  3  4  5  6  7  8  9
```

Initial values:

```text
start = 0
i = 0
```

### Iteration 1–5

The loop traverses the first word.

At index 5, it finds a space.

```text
start = 0
i = 5
```

Reverse:

```java
reverse(ch, start, i - 1);
```

Equivalent to:

```java
reverse(ch, 0, 4);
```

Result:

```text
s'teL code
```

Update:

```text
start = 6
```

### Iteration 6–9

The loop traverses the second word.

No additional space is found.

After the loop:

```java
reverse(ch, start, ch.length - 1);
```

Equivalent to:

```java
reverse(ch, 6, 9);
```

Result:

```text
s'teL edoc
```

Final output:

```text
"s'teL edoc"
```

## Complexity Analysis

Time Complexity: O(n)

- One traversal identifies word boundaries.
- Each character is swapped at most once during reversal.

Auxiliary Space: O(n)

- `toCharArray()` creates a new character array.

---

# 6. Approach 3: Optimal Two Pointers

## Technique

Two Pointers + Inline Reversal.

## Idea

We can perform the reversal directly inside the
main traversal without a separate helper method.

The important condition is:

```java
if (i == ch.length || ch[i] == ' ')
```

This checks whether:

- We encountered a space, OR
- We reached the end of the array.

This allows us to handle the last word inside the
same loop.

## Code

```java
class Solution {
    public String reverseWords(String s) {

        char[] ch = s.toCharArray();

        int start = 0;

        for (int i = 0; i <= ch.length; i++) {

            if (i == ch.length || ch[i] == ' ') {

                int left = start;
                int right = i - 1;

                while (left < right) {

                    char temp = ch[left];
                    ch[left] = ch[right];
                    ch[right] = temp;

                    left++;
                    right--;
                }

                start = i + 1;
            }
        }

        return new String(ch);
    }
}
```

## Why is this optimal?

- No splitting is required.
- No separate StringBuilder is required.
- Every character is processed a constant number of times.
- Word reversal is performed using two pointers.
- The final word is handled within the same loop.

Time Complexity: O(n)

Auxiliary Space: O(n)

---

# 7. Approach Comparison

| Approach | Technique | Time | Auxiliary Space |
|---|---|---|---|
| Brute Force | Split + StringBuilder | O(n) | O(n) |
| Two Pointers | char[] + Helper | O(n) | O(n) |
| Optimal | char[] + Inline Reversal | O(n) | O(n) |

**Recommended approach:** Approach 2 or Approach 3.

Both achieve optimal linear time complexity.

Approach 2 is easier to understand and maintain.
Approach 3 avoids the helper method and handles
the final word within the loop.

---

# 8. Important Java Methods

| Method | Purpose |
|---|---|
| `s.toCharArray()` | Converts String into character array |
| `s.length()` | Returns String length |
| `ch.length` | Returns character array length |
| `ch[i]` | Accesses a character by index |
| `new String(ch)` | Converts character array into String |
| `StringBuilder.reverse()` | Reverses a StringBuilder |

---

# 9. Common Mistakes

1. Reversing the entire sentence instead of individual words.
2. Forgetting to reverse the final word after the loop.
3. Passing `i` instead of `i - 1` as the ending index.
4. Updating `start` incorrectly after finding a space.
5. Using `s.length()` as an array index instead of `s.length() - 1`.
6. Forgetting that Java Strings are immutable.
7. Confusing the order of words with the order of characters.

---

# 10. Edge Cases

| Input | Expected Output |
|---|---|
| `"Let's code"` | `"s'teL edoc"` |
| `"God Ding"` | `"doG gniD"` |
| `"Hello"` | `"olleH"` |
| `"a"` | `"a"` |
| `"ab cd ef"` | `"ba dc fe"` |

---

# 11. Interview Explanation

"I solve this problem using the two-pointer technique.

Since Java Strings are immutable, I first convert
the input String into a character array.

I traverse the array and identify each word using
spaces as delimiters.

Whenever I encounter a space, I reverse the
characters of the previous word using two pointers.

I move the starting index to the beginning of the
next word.

After traversal, I reverse the final word separately.

Finally, I convert the character array back into
a String.

The solution takes O(n) time and O(n) auxiliary space."

---

# 12. Important Complexity Concept

Why is the auxiliary space O(n), not O(1)?

Because:

```java
char[] ch = s.toCharArray();
```

creates a separate character array of size n.

Although the reversal operation itself uses only
a constant number of variables, the character array
requires O(n) additional memory.

The returned String also requires O(n) output storage.

**Remember:**

Java String immutability makes the character array
a practical requirement for this solution.

---

# 13. Key Takeaways

- Understand String immutability in Java.
- Understand character-array manipulation.
- Identify word boundaries using spaces.
- Use two pointers to reverse individual words.
- Remember to handle the final word.
- Avoid unnecessary splitting and repeated concatenation.
- Distinguish output space from auxiliary space.

**Main DSA Pattern:**
Two Pointers – Opposite Direction.

**Core Logic:**
Find the word boundaries, then reverse each word
independently using two pointers.

---

# 14. Revision Checklist

- [ ] Explain the problem without looking at notes.
- [ ] Implement the brute-force approach.
- [ ] Implement the helper-method approach.
- [ ] Implement inline two-pointer reversal.
- [ ] Dry-run the algorithm manually.
- [ ] Explain why the final word needs handling.
- [ ] Explain O(n) time complexity.
- [ ] Explain O(n) auxiliary space.
- [ ] Explain the solution in an interview.

**Final takeaway:**

Whenever a problem asks you to reverse individual
words while preserving their order, think about
word boundaries + two-pointer reversal.
```

## Git commands

Run these from your repository root:

```bash
git add Strings/0557-Reverse-Words-in-a-String-III.java Strings/0557-Reverse-Words-in-a-String-III.md

git commit -m "Add LeetCode 557 Reverse Words in a String III"

git push
```

**Final recommendation:** Keep Approach 2 as your primary interview solution. It is easier to explain, uses a reusable helper method, and has the same optimal time and auxiliary-space complexity as Approach 3.




# 680. Valid Palindrome II

| Property | Details |
|---|---|
| LeetCode Number | 680 |
| Problem Name | Valid Palindrome II |
| Difficulty | Easy |
| Topic | Strings |
| Pattern | Two Pointers |
| Sub-pattern | Greedy |
| Language | Java |
| Optimal Time | O(n) |
| Optimal Auxiliary Space | O(1) |

**Problem Link:** https://leetcode.com/problems/valid-palindrome-ii/

---

# 1. Problem Description

Given a string `s`, return true if the string can become
a palindrome after deleting at most one character.

A palindrome is a string that reads the same forward
and backward.

Important:
- Zero deletions are allowed.
- One deletion is allowed.
- More than one deletion is not allowed.

## Examples

### Example 1

Input:
```text
s = "aba"
```

Output:
```text
true
```

Explanation:

The string is already a palindrome.
No deletion is required.

### Example 2

Input:
```text
s = "abca"
```

Output:
```text
true
```

Explanation:

Delete character 'c'.

Remaining string:
```text
aba
```

It is a palindrome.

### Example 3

Input:
```text
s = "abc"
```

Output:
```text
false
```

Explanation:

Deleting one character cannot make the remaining
string a palindrome.

---

# 2. Important Concepts

## A. Palindrome

A palindrome reads the same forward and backward.

Examples:
- madam
- level
- racecar
- 121

## B. Two Pointers

Two pointers start at opposite ends of the string.

- Left pointer starts at index 0.
- Right pointer starts at the last index.
- Compare both characters.
- Move both pointers inward if they match.

## C. Greedy Technique

A greedy approach makes a decision at the first mismatch.

In this problem, when two characters do not match,
we have exactly two choices:

1. Ignore the left character.
2. Ignore the right character.

We check both possibilities.

If either remaining substring is a palindrome,
the answer is true.

## D. Helper Method

A helper method checks whether a selected portion
of a string is a palindrome.

Method signature:

```java
boolean isPalindrome(String s, int l, int r)
```

Parameters:
- `s`: Original string.
- `l`: Starting index.
- `r`: Ending index.

The helper method does not delete another character.
It only checks the selected substring.

---

# 3. Approach 1: Brute Force

## Technique

Try deleting every character individually.

## Idea

1. Check whether the original string is a palindrome.
2. If it is not, try skipping the first character.
3. Check whether the remaining characters form a palindrome.
4. Repeat for every possible character.
5. If any deletion produces a palindrome, return true.
6. Otherwise, return false.

## Code

```java
class Solution {

    public boolean validPalindrome(String s) {

        if (isPalindrome(s, -1)) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {

            if (isPalindrome(s, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindrome(String s, int skip) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            if (l == skip) {
                l++;
                continue;
            }

            if (r == skip) {
                r--;
                continue;
            }

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}
```

## Dry Run

Input:

```text
s = "abca"
```

First check the original string:

```text
a b c a
```

- First and last characters match.
- Second and third characters do not match.
- Original string is not a palindrome.

Now try deleting each character.

| Deleted character | Remaining string | Palindrome? |
|---|---|---|
| a (index 0) | bca | No |
| b (index 1) | aca | Yes |
| c (index 2) | aba | Yes |
| a (index 3) | abc | No |

At least one deletion works.

Output: `true`.

## Complexity Analysis

Time Complexity: O(n²)

Why?

- We try up to n possible deletions.
- Each palindrome check can take O(n) time.

Therefore:

O(n × n) = O(n²).

Auxiliary Space: O(1)

We only use two pointers and a skip index.
No additional string or array is created.

---

# 4. Approach 2: Optimal Two Pointers + Greedy

## Technique

Two Pointers + Greedy + Helper Method.

## Idea

Instead of trying every possible deletion,
we find the first mismatch.

When characters match:
- Move the left pointer forward.
- Move the right pointer backward.

When characters don't match:
- Try skipping the left character.
- Try skipping the right character.

If either possibility gives a palindrome, return true.

## Step-by-Step Algorithm

Step 1:
Initialize two pointers.

```java
int l = 0;
int r = s.length() - 1;
```

Step 2:
Continue while the pointers haven't crossed.

```java
while (l < r)
```

Step 3:
Compare the characters.

```java
if (s.charAt(l) != s.charAt(r))
```

Step 4:
If they don't match, check both possibilities.

```java
return isPalindrome(s, l + 1, r) ||
       isPalindrome(s, l, r - 1);
```

Step 5:
If characters match, move both pointers.

```java
l++;
r--;
```

Step 6:
If the loop finishes without finding a mismatch,
return true.

## Code

```java
class Solution {

    public boolean validPalindrome(String s) {

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {

                return isPalindrome(s, l + 1, r) ||
                       isPalindrome(s, l, r - 1);
            }

            l++;
            r--;
        }

        return true;
    }

    public boolean isPalindrome(String s, int l, int r) {

        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }
}
```

---

# 5. Detailed Dry Run – "abca"

Input:

```text
s = "abca"
```

Initial values:

```text
l = 0
r = 3
```

### Iteration 1

```text
a b c a
^     ^
l     r
```

Compare:

```java
s.charAt(0) == s.charAt(3)
```

```text
a == a
```

Characters match.

Move both pointers:

```text
l = 1
r = 2
```

### Iteration 2

```text
a b c a
  ^ ^
  l r
```

Compare:

```java
s.charAt(1) == s.charAt(2)
```

```text
b == c
```

Characters don't match.

First mismatch found.

Now we have two choices.

### Choice 1: Skip the left character

```java
isPalindrome(s, l + 1, r)
```

Equivalent to:

```java
isPalindrome(s, 2, 2)
```

Remaining substring:

```text
c
```

Only one character remains.

It is a palindrome.

Result: true.

### Choice 2: Skip the right character

```java
isPalindrome(s, l, r - 1)
```

Equivalent to:

```java
isPalindrome(s, 1, 1)
```

Remaining substring:

```text
b
```

It is also a palindrome.

Result: true.

### Final result

```java
true || true
```

Output:

```text
true
```

Note: Java's `||` operator uses short-circuit evaluation.
Since the first condition returns true, the second
helper call is not executed.

---

# 6. Dry Run – "abc"

Input:

```text
s = "abc"
```

Initial values:

```text
l = 0
r = 2
```

First comparison:

```text
a != c
```

Mismatch found.

### Choice 1: Skip left

Remaining substring:

```text
bc
```

Comparison:

```text
b != c
```

Returns false.

### Choice 2: Skip right

Remaining substring:

```text
ab
```

Comparison:

```text
a != b
```

Returns false.

Final result:

```java
false || false
```

Output:

```text
false
```

---

# 7. Dry Run – "deeee"

Input:

```text
s = "deeee"
```

Initial comparison:

```text
d e e e e
^       ^
```

Characters do not match.

Skip the left character.

Remaining substring:

```text
eeee
```

Now compare:

| Left | Right | Result |
|---|---|---|
| e | e | Match |
| e | e | Match |

All pairs match.

Output:

```text
true
```

---

# 8. Understanding the Helper Method

```java
public boolean isPalindrome(String s, int l, int r)
```

Purpose:

Checks whether the selected substring is a palindrome.

### Step 1

```java
while (l < r)
```

Continue checking while pointers have not met.

### Step 2

```java
if (s.charAt(l) != s.charAt(r)) {
    return false;
}
```

If characters differ, this deletion choice fails.

### Step 3

```java
l++;
r--;
```

If they match, move inward.

### Step 4

```java
return true;
```

If all pairs match, the selected substring is a palindrome.

Important:

The helper method does not allow another deletion.
The one deletion is already represented by its input
indexes.

---

# 9. Approach Comparison

| Property | Brute Force | Optimal |
|---|---|---|
| Technique | Try every deletion | First mismatch |
| Time | O(n²) | O(n) |
| Auxiliary Space | O(1) | O(1) |
| Extra array | No | No |
| Helper method | Yes | Yes |
| Recommended | No | Yes |

## Why is the optimal approach O(n)?

The main traversal takes O(n).

At the first mismatch, at most two additional
palindrome checks are performed.

Each helper check takes at most O(n).

Therefore:

O(n) + O(n) + O(n) = O(3n) = O(n).

## Why is auxiliary space O(1)?

Only a fixed number of variables are used:

- Left pointer
- Right pointer
- Helper method parameters

No extra arrays, collections, or copied substrings
are created.

---

# 10. Important Java Concepts

| Java concept | Purpose |
|---|---|
| `s.length()` | Returns string length |
| `s.charAt(i)` | Accesses a character |
| `l++` | Moves left pointer forward |
| `r--` | Moves right pointer backward |
| `||` | Logical OR |
| Helper method | Checks a selected substring |
| `return false` | Stops when a mismatch is found |
| `return true` | Confirms all comparisons succeeded |

---

# 11. Common Mistakes

1. Returning false immediately when the first mismatch occurs.
2. Checking only the left deletion possibility.
3. Checking only the right deletion possibility.
4. Allowing multiple deletions.
5. Forgetting to move pointers after matching characters.
6. Using a nested while loop without updating pointers.
7. Forgetting the final return true.
8. Confusing the helper method with the main method.

---

# 12. Edge Cases

| Input | Expected | Reason |
|---|---|---|
| `"aba"` | true | Already palindrome |
| `"abca"` | true | One deletion works |
| `"abc"` | false | One deletion insufficient |
| `"deeee"` | true | Delete first character |
| `"cbbcc"` | true | Delete one character |
| `"a"` | true | Single character |
| `"ab"` | true | Delete either character |
| `"abcdef"` | false | One deletion insufficient |

---

# 13. Interview Explanation

"I use the two-pointer technique to solve this problem.

I initialize one pointer at the beginning and another
at the end of the string.

I compare the characters while moving both pointers
towards the centre.

If the characters match, I continue.

If I find a mismatch, I consider two possibilities:
either skip the left character or skip the right
character.

I use a helper method to check whether either
remaining substring is a palindrome.

If at least one possibility succeeds, I return true.
Otherwise, I return false.

The solution takes O(n) time and O(1) auxiliary space."

---

# 14. Key Takeaways

**Main DSA Pattern:**
Two Pointers – Opposite Direction.

**Additional Technique:**
Greedy decision at the first mismatch.

**Core Logic:**

When the first mismatch occurs, try skipping either
the left or right character.

If either remaining substring is a palindrome,
the original string is a valid palindrome after
at most one deletion.

**Best Solution:**
Two Pointers + Helper Method.

**Time Complexity:** O(n)

**Auxiliary Space:** O(1)

---

# 15. Revision Checklist

- [ ] Understand palindrome logic.
- [ ] Understand opposite-direction two pointers.
- [ ] Understand the first mismatch condition.
- [ ] Understand both deletion possibilities.
- [ ] Understand the helper method.
- [ ] Implement brute force independently.
- [ ] Implement the optimal approach independently.
- [ ] Explain why the solution is O(n).
- [ ] Explain why auxiliary space is O(1).
- [ ] Explain the approach in an interview.

**Final takeaway:**

The key to LeetCode 680 is not checking every
possible deletion.

Find the first mismatch and check exactly two
possibilities: skip the left character or skip
the right character.
```

## Git commands

From your repository root directory:

```bash
git add Strings/0680-Valid-Palindrome-II.java Strings/0680-Valid-Palindrome-II.md

git commit -m "Add LeetCode 680 Valid Palindrome II solutions and notes"

git push
```

## Final revision summary

| Approach | Time | Auxiliary space |
|---|---:|---:|
| Brute force | O(n²) | O(1) |
| Two pointers + greedy | O(n) | O(1) |

**Recommended interview solution:** Two Pointers + Greedy.

The most important concept to remember is that the first mismatch gives you exactly two deletion choices. You do not need to explore every possible deletion.