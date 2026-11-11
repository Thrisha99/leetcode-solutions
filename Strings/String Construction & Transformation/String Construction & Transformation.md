# 1108. Defanging an IP Address

## Problem Information

| Attribute | Details |
|---|---|
| LeetCode Number | 1108 |
| Problem Name | Defanging an IP Address |
| Difficulty | Easy |
| Topic | Strings |
| Pattern | String Manipulation |
| Language | Java |
| Main Concept | StringBuilder |
| Problem Link | https://leetcode.com/problems/defanging-an-ip-address/ |

---

## 1. Problem Statement

Given a valid IPv4 address, return a defanged version of that IP address.

A defanged IP address replaces every occurrence of the dot character (`.`) with `[.]`.

### Example 1

**Input:**
```text
address = "1.1.1.1"
```

**Output:**
```text
"1[.]1[.]1[.]1"
```

### Example 2

**Input:**
```text
address = "255.100.50.0"
```

**Output:**
```text
"255[.]100[.]50[.]0"
```

---

## 2. Key Observation

Only the dot (`.`) character needs to be replaced.

All other characters must remain unchanged.

Our logic:

1. Traverse the original string.
2. Check each character.
3. If the character is `.`, append `[.]`.
4. Otherwise, append the original character.
5. Return the modified string.

---

## 3. Approach 1 – Brute Force / String Concatenation

### Logic

We can traverse the string and build a new string using the `+` operator.

- If the current character is `.`, concatenate `[.]`.
- Otherwise, concatenate the current character.

### Code

```java
class Solution {
    public String defangIPaddr(String address) {

        String result = "";

        for (int i = 0; i < address.length(); i++) {

            if (address.charAt(i) == '.') {
                result += "[.]";
            } else {
                result += address.charAt(i);
            }
        }

        return result;
    }
}
```

### Complexity

- Time: O(n²) in the worst case due to repeated String creation and copying.
- Auxiliary Space: O(n) for the resulting string.

**Limitation:** Java Strings are immutable. Repeated concatenation creates intermediate String objects.

---

## 4. Approach 2 – StringBuilder + For Loop

### Logic

Instead of repeatedly creating new Strings, use StringBuilder.

StringBuilder is mutable, so we can append characters and replacement strings efficiently.

### Code – My Solution

```java
class Solution {
    public String defangIPaddr(String address) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < address.length(); i++) {

            if (address.charAt(i) == '.') {
                sb.append("[.]");
            } else {
                sb.append(address.charAt(i));
            }
        }

        return sb.toString();
    }
}
```

### Step-by-Step Explanation

**Step 1: Create StringBuilder**

```java
StringBuilder sb = new StringBuilder();
```

Creates an empty mutable string.

Initially:

```text
sb = ""
```

**Step 2: Traverse the input**

```java
for (int i = 0; i < address.length(); i++)
```

Visits every character using index `i`.

**Step 3: Check for a dot**

```java
if (address.charAt(i) == '.')
```

Checks whether the current character is a dot.

**Step 4: Replace the dot**

```java
sb.append("[.]");
```

Appends `[.]` instead of the original dot.

**Step 5: Preserve other characters**

```java
else {
    sb.append(address.charAt(i));
}
```

Appends the original character when it is not a dot.

**Step 6: Return the result**

```java
return sb.toString();
```

Converts StringBuilder into a String.

---

## 5. Dry Run

### Input

```java
address = "1.1.1.1";
```

Initially:

```java
StringBuilder sb = new StringBuilder();
```

Current value:

```text
sb = ""
```

### Iteration Table

| i | address.charAt(i) | Condition | Operation | sb |
|---|---|---|---|---|
| 0 | '1' | false | Append '1' | 1 |
| 1 | '.' | true | Append '[.]' | 1[.] |
| 2 | '1' | false | Append '1' | 1[.]1 |
| 3 | '.' | true | Append '[.]' | 1[.]1[.] |
| 4 | '1' | false | Append '1' | 1[.]1[.]1 |
| 5 | '.' | true | Append '[.]' | 1[.]1[.]1[.] |
| 6 | '1' | false | Append '1' | 1[.]1[.]1[.]1 |

### Final Output

```text
"1[.]1[.]1[.]1"
```

---

## 6. Approach 3 – Built-in replace() Method

Java provides a built-in method to replace all occurrences of a character sequence.

### Code

```java
class Solution {
    public String defangIPaddr(String address) {

        return address.replace(".", "[.]");
    }
}
```

### Explanation

```java
address.replace(".", "[.]");
```

- First argument: sequence to find.
- Second argument: replacement sequence.
- Replaces all occurrences of the specified sequence.

### Complexity

- Time: O(n)
- Auxiliary Space: O(n) for the resulting String.

---

## 7. Complexity Comparison

Let n represent the length of the input string.

| Approach | Time Complexity | Auxiliary Space |
|---|---|---|
| String concatenation | O(n²) | O(n) |
| StringBuilder | O(n) | O(n) |
| replace() | O(n) | O(n) |

### Best Approach

StringBuilder is a good choice for understanding manual string manipulation.

The built-in replace() method is the shortest and cleanest solution for this particular problem.

---

## 8. Java Concepts Learned

| Concept | Purpose |
|---|---|
| String | Stores the original IP address |
| length() | Gets the length of the string |
| charAt(i) | Accesses a character by index |
| StringBuilder | Builds a modified string |
| append() | Adds characters or strings |
| toString() | Converts StringBuilder to String |
| replace() | Replaces all matching occurrences |

---

## 9. Common Mistakes

1. Replacing only the first dot instead of every dot.
2. Using `==` to compare two String objects instead of `.equals()`.
3. Forgetting to append non-dot characters.
4. Using repeated String concatenation inside a loop for larger strings.
5. Forgetting to return the modified String.

---

## 10. Edge Cases

| Input | Expected Output |
|---|---|
| "1.1.1.1" | "1[.]1[.]1[.]1" |
| "255.100.50.0" | "255[.]100[.]50[.]0" |
| "0.0.0.0" | "0[.]0[.]0[.]0" |

---

## 11. Interview Explanation

"I traverse the given IP address character by character using a for loop. Whenever I encounter a dot, I append `[.]` to a StringBuilder. For all other characters, I append the original character. Finally, I convert the StringBuilder into a String and return it.

This takes O(n) time and O(n) auxiliary space."

---

## 12. Revision Checklist

- [ ] Understand String immutability.
- [ ] Understand StringBuilder.
- [ ] Practice charAt().
- [ ] Understand append().
- [ ] Understand toString().
- [ ] Implement the solution without looking at notes.
- [ ] Compare String concatenation with StringBuilder.
- [ ] Practice the built-in replace() method.

---

## 13. Git Information

**File Name:**
```text
1108-Defanging-an-IP-Address.java
1108-Defanging-an-IP-Address.md
```

**Git Commands:**

```bash
git status

git add Strings/1108-Defanging-an-IP-Address.java

git add Strings/1108-Defanging-an-IP-Address.md

git commit -m "Add LeetCode 1108 Defanging an IP Address"

git push origin main
```

---

## 14. Final Takeaway

This problem teaches basic String traversal and modification.

Remember:

- Use `charAt(i)` to access individual characters.
- Use `StringBuilder` to construct modified strings.
- Use `append()` to add characters or replacement sequences.
- Use `toString()` to obtain the final String.

**Pattern:** String Traversal + Conditional Replacement.

# LeetCode 415 - Add Strings

## 1. Problem Information

- **Problem:** Add Strings
- **LeetCode Number:** 415
- **Link:** https://leetcode.com/problems/add-strings/
- **Difficulty:** Medium
- **Topic:** Strings
- **Pattern:** Two Pointers + Simulation + Carry
- **Language:** Java

---

## 2. Problem Statement

Given two non-negative integers represented as strings, return their sum as a string.

You must not convert the input strings directly into integer types.

### Example 1

Input:
```java
num1 = "11"
num2 = "123"
```

Output:
```java
"134"
```

### Example 2

Input:
```java
num1 = "456"
num2 = "77"
```

Output:
```java
"533"
```

### Example 3

Input:
```java
num1 = "0"
num2 = "0"
```

Output:
```java
"0"
```

---

## 3. Prerequisite Concepts

### A. Character to Integer Conversion

```java
int digit = num1.charAt(i) - '0';
```

Why subtract '0'?

Characters representing digits have corresponding numeric character codes.

Example:

```java
'7' - '0' = 7
```

This converts the character '7' into the integer 7.

### B. Modulo Operator (%)

Returns the remainder after division.

```java
15 % 10 = 5
```

Used to extract the last digit.

### C. Integer Division (/)

```java
15 / 10 = 1
```

Used to calculate carry.

### D. StringBuilder

```java
StringBuilder sb = new StringBuilder();
```

Used to construct the result efficiently.

Important methods:

| Method | Purpose |
|---|---|
| append() | Adds a digit |
| reverse() | Reverses the characters |
| toString() | Converts StringBuilder into String |

---

## 4. Understanding Addition with Carry

Consider:

```text
  8
+ 7
----
 15
```

We need to separate 15 into:

- Current digit = 5
- Carry = 1

Java:

```java
int digit = sum % 10;
carry = sum / 10;
```

Result:

```text
digit = 15 % 10 = 5
carry = 15 / 10 = 1
```

The carry moves to the next position on the left.

---

## 5. Approach 1: Brute Force

### Idea

Perform digit-by-digit addition but construct the result using String concatenation.

```java
result = digit + result;
```

Each new digit is added to the beginning.

### Drawback

Java Strings are immutable.

Every concatenation creates a new String, copying existing characters.

Repeated prepending can take O(L^2) time.

### Complexity

- Time: O(L^2)
- Output Space: O(L)
- Auxiliary Space excluding output: O(1)

L = max(m, n).

---

## 6. Approach 2: Optimal - StringBuilder + Two Pointers

### Main Idea

Start adding from the rightmost digits because ordinary arithmetic addition starts from the units position.

Use:
- Two pointers to traverse both strings.
- Carry to store overflow.
- StringBuilder to construct the result.
- Reverse to restore the correct digit order.

### Step 1: Initialize pointers

```java
int i = num1.length() - 1;
int j = num2.length() - 1;
```

Both pointers start at the last character.

### Step 2: Initialize carry

```java
int carry = 0;
```

Initially, there is no carry.

### Step 3: Create StringBuilder

```java
StringBuilder sb = new StringBuilder();
```

It stores the result in reverse order.

### Step 4: Traverse while digits remain

```java
while (i >= 0 || j >= 0)
```

Why OR?

Because the two strings may have different lengths.

We continue until both pointers move beyond their first characters.

### Step 5: Handle missing digits

```java
int d1 = 0;
int d2 = 0;

if (i >= 0) {
    d1 = num1.charAt(i) - '0';
}

if (j >= 0) {
    d2 = num2.charAt(j) - '0';
}
```

If one number has no remaining digit, use zero.

This prevents StringIndexOutOfBoundsException.

### Step 6: Calculate sum

```java
int sum = d1 + d2 + carry;
```

### Step 7: Extract digit

```java
int digit = sum % 10;
```

### Step 8: Calculate carry

```java
carry = sum / 10;
```

### Step 9: Append digit

```java
sb.append(digit);
```

### Step 10: Move pointers

```java
i--;
j--;
```

### Step 11: Handle remaining carry

```java
if (carry != 0) {
    sb.append(carry);
}
```

### Step 12: Reverse and return

```java
return sb.reverse().toString();
```

---

## 7. Detailed Dry Run

Input:

```java
num1 = "123"
num2 = "45"
```

Initial variables:

```text
i = 2
j = 1
carry = 0
sb = ""
```

### Iteration 1

Current positions:

```text
num1 = 1 2 3
           ^
           i = 2

num2 = 4 5
         ^
         j = 1
```

Extract digits:

```text
d1 = 3
d2 = 5
carry = 0
```

Calculate:

```text
sum = 3 + 5 + 0 = 8

digit = 8 % 10 = 8

carry = 8 / 10 = 0
```

Append:

```text
sb = "8"
```

Move pointers:

```text
i = 1
j = 0
```

### Iteration 2

Extract:

```text
d1 = 2
d2 = 4
carry = 0
```

Calculate:

```text
sum = 2 + 4 + 0 = 6

digit = 6 % 10 = 6

carry = 6 / 10 = 0
```

Append:

```text
sb = "86"
```

Move pointers:

```text
i = 0
j = -1
```

### Iteration 3

Extract:

```text
d1 = 1
d2 = 0
carry = 0
```

Why d2 = 0?

Because j = -1, so num2 has no remaining digits.

Calculate:

```text
sum = 1 + 0 + 0 = 1

digit = 1 % 10 = 1

carry = 1 / 10 = 0
```

Append:

```text
sb = "861"
```

Move pointers:

```text
i = -1
j = -2
```

Loop terminates.

Final carry:

```text
carry = 0
```

No additional digit is required.

Reverse:

```text
"861" -> "168"
```

Final output:

```java
"168"
```

---

## 8. Dry Run: Carry Propagation

Input:

```java
num1 = "999"
num2 = "1"
```

| Iteration | d1 | d2 | Carry in | Sum | Digit | Carry out |
|---|---:|---:|---:|---:|---:|---:|
| 1 | 9 | 1 | 0 | 10 | 0 | 1 |
| 2 | 9 | 0 | 1 | 10 | 0 | 1 |
| 3 | 9 | 0 | 1 | 10 | 0 | 1 |

StringBuilder:

```text
After iteration 1: "0"
After iteration 2: "00"
After iteration 3: "000"
After final carry: "0001"
```

Reverse:

```text
"1000"
```

Final output:

```java
"1000"
```

---

## 9. Alternative Implementation: Carry in While Condition

Instead of handling carry separately after the loop:

```java
if (carry != 0) {
    sb.append(carry);
}
```

We can include carry in the while condition:

```java
while (i >= 0 || j >= 0 || carry > 0)
```

Then:

```java
int sum = carry;

if (i >= 0) {
    sum += num1.charAt(i) - '0';
    i--;
}

if (j >= 0) {
    sum += num2.charAt(j) - '0';
    j--;
}
```

The loop continues as long as:
- num1 has digits, OR
- num2 has digits, OR
- carry remains.

Both implementations are correct.

---

## 10. Complexity Analysis

Let:

- m = num1.length()
- n = num2.length()
- L = max(m, n)

| Approach | Time Complexity | Output Space |
|---|---|---|
| String concatenation | O(L²) | O(L) |
| StringBuilder | O(L) | O(L) |

### Auxiliary Space

The algorithm uses a constant number of variables:

```text
i, j, carry, d1, d2, sum, digit
```

Therefore:

- Auxiliary working space excluding output: O(1)
- Output buffer: O(L)
- Returned String: O(L)

The StringBuilder is used to construct the output, so it is included in output storage rather than constant auxiliary space.

---

## 11. Common Mistakes

1. Using `i != 0` instead of `i >= 0`.
2. Using `&&` instead of `||` in the loop condition.
3. Accessing `charAt(-1)` when a pointer becomes invalid.
4. Forgetting to handle unequal string lengths.
5. Forgetting to include carry in the next addition.
6. Forgetting the final carry.
7. Forgetting to reverse StringBuilder.
8. Writing `sb.reverse().toString();` without using the returned String.
9. Converting the entire number into int or long, which can overflow.

---

## 12. Edge Cases

| Input 1 | Input 2 | Expected |
|---|---|---|
| "0" | "0" | "0" |
| "9" | "9" | "18" |
| "99" | "1" | "100" |
| "123" | "45" | "168" |
| "456" | "77" | "533" |
| "999" | "999" | "1998" |
| "1" | "9999" | "10000" |

---

## 13. Interview Explanation

"I use two pointers starting from the rightmost positions of both strings. At each step, I extract the corresponding digits, add them with the previous carry, and calculate the result digit using modulo 10. I calculate the next carry using integer division by 10. If one string has no remaining digits, I use zero. I append each result digit to a StringBuilder and reverse it at the end. Finally, I handle any remaining carry."

---

## 14. Key Takeaways

- Two pointers can traverse strings from right to left.
- Character subtraction converts numeric characters into integer digits.
- Modulo 10 extracts the current digit.
- Division by 10 calculates carry.
- Unequal lengths require conditional digit extraction.
- StringBuilder avoids repeated String concatenation.
- Reverse restores the correct order.
- Carry must never be lost.

---

## 15. Revision Checklist

- [ ] Explain why we start from the right.
- [ ] Explain charAt(i) - '0'.
- [ ] Understand % 10 and / 10.
- [ ] Explain why the loop uses OR.
- [ ] Handle unequal string lengths.
- [ ] Understand carry propagation.
- [ ] Explain why StringBuilder is used.
- [ ] Explain why reverse() is necessary.
- [ ] Dry-run 999 + 1.
- [ ] Explain the complexity without confusing output and auxiliary space.
- [ ] Implement without looking at notes.


# 443. String Compression

## 1. Problem Information

| Attribute | Details |
|---|---|
| LeetCode Number | 443 |
| Problem Name | String Compression |
| Difficulty | Medium |
| Topic | Strings |
| Pattern | Two Pointers |
| Sub-pattern | In-place Array Manipulation |
| Language | Java |
| Main Concepts | Consecutive Frequency Counting, Read/Write Pointers |
| Problem Link | https://leetcode.com/problems/string-compression/ |

---

# 2. Problem Statement

Given an array of characters `chars`, compress it using the following algorithm:

1. Begin with an empty string `s`.
2. For each group of consecutive repeating characters in `chars`:
   - If the group's length is 1, append the character to `s`.
   - Otherwise, append the character followed by the group's length.
3. Modify the original character array in-place instead of returning a new array.
4. Return the new length of the compressed array.

If a group's count is greater than 9, each digit must be written separately.

### Important Constraints

- The original array must be modified in-place.
- The compressed characters must occupy the first `k` positions.
- Return the value of `k`.
- Auxiliary space must be O(1) for the optimal solution.

---

# 3. Examples

## Example 1

**Input:**

```java
chars = ['a','a','b','b','c','c','c'];
```

Consecutive groups:

```text
aa | bb | ccc
```

Compressed representation:

```text
a2 | b2 | c3
```

**Modified array:**

```java
['a','2','b','2','c','3']
```

**Output:**

```text
6
```

## Example 2

**Input:**

```java
chars = ['a'];
```

Only one character exists.

No compression is required.

**Output:**

```text
1
```

## Example 3

**Input:**

```java
chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b'];
```

Here:

- a occurs once.
- b occurs 12 consecutive times.

Compressed representation:

```text
a | b12
```

**Modified array:**

```java
['a','b','1','2']
```

**Output:**

```text
4
```

## Example 4

**Input:**

```java
chars = ['a','a','b','a','a'];
```

Important: Only consecutive characters are grouped.

Groups:

```text
aa | b | aa
```

Compressed representation:

```text
a2ba2
```

**Output:**

```text
5
```

---

# 4. Key Observation

The most important word in this problem is **consecutive**.

Consider:

```text
['a','a','b','a','a']
```

There are four occurrences of `a`, but they are not consecutive.

Therefore, we cannot combine them into one group.

Correct compression:

```text
['a','2','b','a','2']
```

Incorrect compression:

```text
['a','4','b']
```

The task is to count consecutive occurrences, not total character frequency.

This is a Run-Length Encoding (RLE) problem.

---

# 5. Approach 1 – Brute Force Using StringBuilder

## Idea

Instead of modifying the original array directly, create a separate StringBuilder to store the compressed result.

After processing all groups, copy the result back into the original array.

## Algorithm

1. Create an empty StringBuilder.
2. Initialize `i = 0`.
3. Traverse the original array.
4. Store the current character.
5. Count its consecutive occurrences.
6. Append the character.
7. If count > 1, append the count.
8. Copy the StringBuilder contents into the original array.
9. Return the StringBuilder length.

## Java Code

```java
class Solution {
    public int compress(char[] chars) {

        StringBuilder sb = new StringBuilder();

        int i = 0;
        int n = chars.length;

        while (i < n) {

            char current = chars[i];
            int count = 0;

            while (i < n && chars[i] == current) {
                count++;
                i++;
            }

            sb.append(current);

            if (count > 1) {
                sb.append(count);
            }
        }

        for (int j = 0; j < sb.length(); j++) {
            chars[j] = sb.charAt(j);
        }

        return sb.length();
    }
}
```

## Dry Run

Input:

```java
chars = ['a','a','b','b','c','c','c'];
```

Initially:

```text
i = 0
sb = ""
```

| Iteration | i at start | Character | Count | Action | StringBuilder |
|---|---:|---|---:|---|---|
| 1 | 0 | a | 2 | Append a2 | a2 |
| 2 | 2 | b | 2 | Append b2 | a2b2 |
| 3 | 4 | c | 3 | Append c3 | a2b2c3 |

Final StringBuilder:

```text
a2b2c3
```

Copy into the original array:

```java
['a','2','b','2','c','3']
```

Return:

```text
6
```

## Complexity

Let n be the original array length.

- Time Complexity: O(n)
- Auxiliary Space: O(n)

The StringBuilder stores the complete compressed result separately.

**Limitation:** This approach does not satisfy the O(1) auxiliary-space requirement.

---

# 6. Approach 2 – Better: In-place Two Pointers

## Idea

Instead of creating a separate StringBuilder, modify the original character array directly.

We use two pointers:

1. Read pointer (`i`)
2. Write pointer (`write`)

### Read Pointer

Responsible for:

- Reading original characters.
- Identifying consecutive groups.
- Counting character occurrences.

### Write Pointer

Responsible for:

- Writing the compressed character.
- Writing the count digits.
- Maintaining the compressed array length.

## Visual Representation

```text
Original array:

[a, a, b, b, c, c, c]
 ^
 i = 0

write = 0
```

The read pointer identifies groups, while the write pointer stores the compressed representation.

## Algorithm

1. Initialize `i = 0` and `write = 0`.
2. Traverse while `i < chars.length`.
3. Store the current character.
4. Initialize count to zero.
5. Count all consecutive occurrences.
6. Write the current character at `chars[write]`.
7. Increment write.
8. If count > 1:
   - Convert count into a String.
   - Write every digit into the original array.
9. Return write.

## Java Code

```java
class Solution {
    public int compress(char[] chars) {

        int i = 0;
        int write = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }

            chars[write] = current;
            write++;

            if (count > 1) {

                String num = String.valueOf(count);

                for (int j = 0; j < num.length(); j++) {
                    chars[write] = num.charAt(j);
                    write++;
                }
            }
        }

        return write;
    }
}
```

## Detailed Explanation

### Step 1: Initialize pointers

```java
int i = 0;
int write = 0;
```

- `i` reads the input.
- `write` tracks the next position to write.

### Step 2: Outer loop

```java
while (i < chars.length)
```

Continues until all original characters are processed.

### Step 3: Store current character

```java
char current = chars[i];
```

Stores the character whose consecutive group we are processing.

### Step 4: Count occurrences

```java
int count = 0;

while (i < chars.length && chars[i] == current) {
    count++;
    i++;
}
```

The inner loop:

- Checks the current character.
- Increases count.
- Moves the read pointer forward.

When a different character is found, the group ends.

### Step 5: Write the character

```java
chars[write] = current;
write++;
```

Every group must contain its character.

Even if the character occurs only once, we must write it.

### Step 6: Write the count

```java
if (count > 1) {
    String num = String.valueOf(count);

    for (int j = 0; j < num.length(); j++) {
        chars[write] = num.charAt(j);
        write++;
    }
}
```

We append the count only when it is greater than 1.

For example:

```java
count = 12;
```

Converting to String:

```text
"12"
```

We write the two characters separately:

```text
'1'
'2'
```

### Step 7: Return compressed length

```java
return write;
```

The write pointer indicates how many characters have been written.

---

# 7. Complete Dry Run – Two Pointer Approach

Input:

```java
chars = ['a','a','b','b','c','c','c'];
```

Initial state:

```text
i = 0
write = 0
```

## Iteration 1 – Character 'a'

Current values:

```text
i = 0
write = 0
current = 'a'
count = 0
```

Inner loop:

| i | chars[i] | Condition | Count |
|---:|---|---|---:|
| 0 | a | a == a | 1 |
| 1 | a | a == a | 2 |
| 2 | b | b != a | Stop |

After counting:

```text
i = 2
count = 2
```

Write character:

```java
chars[write] = 'a';
write++;
```

Array:

```text
[a, a, b, b, c, c, c]
 ^
 write = 1
```

Write count:

```java
chars[write] = '2';
write++;
```

Array:

```text
[a, 2, b, b, c, c, c]
    ^
 write = 2
```

## Iteration 2 – Character 'b'

Current values:

```text
i = 2
write = 2
current = 'b'
count = 0
```

Inner loop:

| i | chars[i] | Condition | Count |
|---:|---|---|---:|
| 2 | b | b == b | 1 |
| 3 | b | b == b | 2 |
| 4 | c | c != b | Stop |

After counting:

```text
i = 4
count = 2
```

Write character:

```text
[a, 2, b, b, c, c, c]
       ^
     write = 3
```

Write count:

```text
[a, 2, b, 2, c, c, c]
          ^
        write = 4
```

## Iteration 3 – Character 'c'

Current values:

```text
i = 4
write = 4
current = 'c'
count = 0
```

Inner loop:

| i | chars[i] | Condition | Count |
|---:|---|---|---:|
| 4 | c | c == c | 1 |
| 5 | c | c == c | 2 |
| 6 | c | c == c | 3 |
| 7 | Out of bounds | Stop | 3 |

After counting:

```text
i = 7
count = 3
```

Write character:

```text
[a, 2, b, 2, c, c, c]
             ^
           write = 5
```

Write count:

```text
[a, 2, b, 2, c, 3, c]
                ^
              write = 6
```

## Final State

```text
Original array:

['a','2','b','2','c','3','c']
```

Valid compressed portion:

```text
['a','2','b','2','c','3']
```

Return:

```text
6
```

Note: The array's physical length remains unchanged. Only the first `write` positions are relevant.

---

# 8. Approach 3 – Optimal: Two Pointers + Mathematical Digit Extraction

## Why improve Approach 2?

Approach 2 uses:

```java
String num = String.valueOf(count);
```

This creates a temporary String for the count.

For the strict O(1) auxiliary-space implementation, we can extract each digit mathematically and write it directly into the original array.

## Idea

Suppose:

```java
count = 12;
```

We need to write:

```text
'1', '2'
```

First, identify the largest power of 10 that fits into the count.

For 12:

```text
divisor = 10
```

Then:

```java
digit = count / divisor;
```

First digit:

```text
12 / 10 = 1
```

Remove the processed digit:

```java
count = count % divisor;
```

Now:

```text
12 % 10 = 2
```

Reduce divisor:

```text
divisor = 1
```

Second digit:

```text
2 / 1 = 2
```

This allows us to write digits without converting the count into a String.

## Java Code

```java
class Solution {
    public int compress(char[] chars) {

        int i = 0;
        int write = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }

            chars[write] = current;
            write++;

            if (count > 1) {

                int divisor = 1;

                while (count / divisor >= 10) {
                    divisor *= 10;
                }

                while (divisor > 0) {

                    int digit = count / divisor;

                    chars[write] = (char) ('0' + digit);
                    write++;

                    count %= divisor;
                    divisor /= 10;
                }
            }
        }

        return write;
    }
}
```

## Detailed explanation of mathematical digit extraction

Consider:

```java
count = 123;
```

Find the divisor:

```text
divisor = 1
123 / 1 = 123 → >= 10 → divisor = 10

123 / 10 = 12 → >= 10 → divisor = 100

123 / 100 = 1 → < 10 → stop
```

Now extract digits:

| Iteration | Count | Divisor | Digit = count/divisor | Remaining count |
|---:|---:|---:|---:|---:|
| 1 | 123 | 100 | 1 | 23 |
| 2 | 23 | 10 | 2 | 3 |
| 3 | 3 | 1 | 3 | 0 |

The digits are written in the correct order:

```text
'1', '2', '3'
```

This avoids creating an additional String or character array.

---

# 9. Complexity Comparison

Let `n` be the length of the original character array.

| Approach | Time Complexity | Auxiliary Space |
|---|---|---|
| Brute Force – StringBuilder | O(n) | O(n) |
| Better – Two pointers + String conversion | O(n) | O(log n) temporary space |
| Optimal – Two pointers + Math | O(n) | O(1) |

### Why is the optimal approach O(n) time?

- Every input character is read once.
- Every compressed character is written once.
- The digit extraction processes only the digits of each count.
- The total number of written digits is bounded by the input size.

Therefore, the total time complexity is O(n).

### Why is auxiliary space O(1)?

We use only a fixed number of variables:

```java
int i;
int write;
int count;
int divisor;
int digit;
char current;
```

No extra arrays, collections, StringBuilder, or temporary strings are created.

The original input array is modified directly.

---

# 10. Important Edge Cases

| Input | Compressed portion | Return |
|---|---|---:|
| `['a']` | `['a']` | 1 |
| `['a','a']` | `['a','2']` | 2 |
| `['a','b','c']` | `['a','b','c']` | 3 |
| `['a','a','a','a']` | `['a','4']` | 2 |
| `['a','a','b','b','b']` | `['a','2','b','3']` | 4 |
| 12 consecutive `b` characters | `['b','1','2']` | 3 |
| `['a','a','b','a','a']` | `['a','2','b','a','2']` | 5 |

---

# 11. Common Mistakes

### Mistake 1: Counting all occurrences instead of consecutive occurrences

Incorrect:

```text
a a b a a → a4b
```

Correct:

```text
a a | b | a a → a2ba2
```

### Mistake 2: Writing count 1

Incorrect:

```text
a1
```

Correct:

```text
a
```

### Mistake 3: Forgetting multi-digit counts

For count = 12, write:

```text
'1', '2'
```

Not a single character representing 12.

### Mistake 4: Returning original array length

Incorrect:

```java
return chars.length;
```

Correct:

```java
return write;
```

### Mistake 5: Using an additional array

The optimal solution must modify the original array.

### Mistake 6: Incorrect pointer updates

The read pointer must advance throughout the current group.

The write pointer must advance after every character or count digit written.

---

# 12. Interview Explanation

"I use two pointers to perform in-place compression.

The read pointer identifies consecutive groups of identical characters and calculates their frequency. The write pointer stores the compressed representation directly into the original character array.

For every group, I first write the character. If the count is greater than one, I write its digits individually. I use mathematical digit extraction to avoid creating additional data structures.

Finally, I return the write pointer, which represents the compressed length.

The solution takes O(n) time and O(1) auxiliary space."

---

# 13. Important Java Concepts

| Concept | Application |
|---|---|
| `char[]` | Mutable character array |
| `chars[i]` | Access and modify array positions |
| Nested while loops | Count consecutive characters |
| Two pointers | Read and write independently |
| `String.valueOf()` | Convert count to String in Approach 2 |
| `% 10` | Extract remainder/digit |
| `/ 10` | Integer division |
| Character arithmetic | Convert a digit into a character |
| In-place modification | Avoid additional result array |

---

# 14. Pattern Recognition

Recognize this problem when you see:

- Consecutive repeating elements.
- In-place modification.
- Requirement to reduce or compress array contents.
- Separate reading and writing operations.
- A requirement to return the new valid length.

**Pattern: Two Pointers + In-place Array Manipulation + Run-Length Encoding.**

---

# 15. Revision Checklist

- [ ] Understand consecutive frequency counting.
- [ ] Understand why non-consecutive characters form separate groups.
- [ ] Understand read pointer `i`.
- [ ] Understand write pointer `write`.
- [ ] Understand why count 1 is not written.
- [ ] Handle counts greater than 9.
- [ ] Understand mathematical digit extraction.
- [ ] Understand why we return `write`.
- [ ] Implement the optimal solution independently.
- [ ] Explain O(n) time and O(1) auxiliary space.

---

# 16. Git Information

## File Names

```text
443-String-Compression.java
443-String-Compression.md
```

## Git Commands

```bash
git status

git add Strings/443-String-Compression.java

git add Strings/443-String-Compression.md

git commit -m "Add LeetCode 443 String Compression"

git push origin main
```

If you are working on another branch, replace `main` with your current branch name.

---

# 17. Final Takeaway

This problem teaches an important in-place manipulation technique.

Remember the responsibilities:

- `i` → Reads and counts.
- `write` → Writes the compressed result.
- `count` → Stores consecutive frequency.
- `divisor` → Helps extract individual count digits.

**Recommended solution:** Approach 3 – Two Pointers + Mathematical Digit Extraction.

Time: O(n)

Auxiliary Space: O(1)

The most important learning outcome is understanding how to read from and write to the same array safely using two pointers.


# LeetCode 8 – String to Integer (atoi)

**Difficulty:** Medium  
**Topics:** String, Simulation  
**Pattern:** String Traversal + Boundary Checking

---

## 1. Problem Statement

Implement the `myAtoi(String s)` function that converts a string into a 32-bit signed integer.

Follow these rules:

1. Ignore leading whitespace.
2. Identify the optional sign (`+` or `-`).
3. Read consecutive digits.
4. Stop at the first non-digit after parsing begins.
5. If no digits are found, return `0`.
6. If the result exceeds the 32-bit signed integer range, clamp it.

### Integer Range

| Constant | Value |
|---|---:|
| Integer.MAX_VALUE | 2147483647 |
| Integer.MIN_VALUE | -2147483648 |

## 2. Examples

| Input | Output |
|---|---:|
| "42" | 42 |
| "   -42" | -42 |
| "4193 with words" | 4193 |
| "words and 987" | 0 |
| "-91283472332" | -2147483648 |
| "2147483648" | 2147483647 |
| "+-12" | 0 |

Important: The algorithm does not search for a number anywhere in the string. It only parses the valid numeric prefix.

---

## 3. Approach 1 – Built-in Parsing (Conceptual)

### Idea

1. Remove leading spaces.
2. Identify the sign.
3. Extract consecutive digits.
4. Convert the extracted number using Integer.parseInt().

### Limitation

`Integer.parseInt()` throws NumberFormatException when the value exceeds the int range.

Therefore, this is not the preferred implementation for this problem.

---

## 4. Approach 2 – Manual Parsing with Positive Accumulator

### Idea

Build the number digit by digit.

Formula:

`result = result * 10 + digit`

Example: 428

| Digit | Calculation | Result |
|---|---|---:|
| 4 | 0 * 10 + 4 | 4 |
| 2 | 4 * 10 + 2 | 42 |
| 8 | 42 * 10 + 8 | 428 |

### Overflow Detection

Before updating the result:

```java
if (result > Integer.MAX_VALUE / 10 ||
    (result == Integer.MAX_VALUE / 10 &&
     digit > (sign == 1 ? 7 : 8))) {
    // Handle overflow
}
```

### Why divide MAX_VALUE by 10?

```text
Integer.MAX_VALUE = 2147483647

2147483647 / 10 = 214748364
```

If the current result is greater than 214748364, appending any digit will cause overflow.

If it equals 214748364, the next digit must be checked.

### Why 7 for positive and 8 for negative?

```text
Positive maximum = 2147483647
Negative minimum = -2147483648
```

The positive boundary ends in 7, while the negative boundary has magnitude ending in 8.

**Important limitation:** A positive `int` accumulator cannot store the magnitude 2147483648. A correct positive-accumulator implementation must handle the negative minimum specially rather than performing that final multiplication.

---

## 5. Approach 3 – Negative Accumulator (Optimal)

### Why use a negative accumulator?

Java integer limits are asymmetric:

```text
Integer.MAX_VALUE =  2147483647
Integer.MIN_VALUE = -2147483648
```

The negative range has one additional value.

A positive accumulator cannot represent 2147483648, but a negative accumulator can represent -2147483648.

Therefore, we build the number negatively.

### Complete Java Code

```java
class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        // Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Identify sign
        int sign = 1;

        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            i++;
        }

        // Set negative boundary
        int limit = (sign == -1)
                ? Integer.MIN_VALUE
                : -Integer.MAX_VALUE;

        int multMin = limit / 10;

        int result = 0;

        while (i < n &&
               s.charAt(i) >= '0' &&
               s.charAt(i) <= '9') {

            int digit = s.charAt(i) - '0';

            // Check multiplication overflow
            if (result < multMin) {
                return sign == -1
                        ? Integer.MIN_VALUE
                        : Integer.MAX_VALUE;
            }

            result *= 10;

            // Check subtraction overflow
            if (result < limit + digit) {
                return sign == -1
                        ? Integer.MIN_VALUE
                        : Integer.MAX_VALUE;
            }

            result -= digit;
            i++;
        }

        return sign * result;
    }
}
```

---

## 6. Detailed Explanation of Important Variables

| Variable | Purpose |
|---|---|
| `n` | Length of input string |
| `i` | Current index |
| `sign` | 1 for positive, -1 for negative |
| `limit` | Allowed negative boundary |
| `multMin` | Minimum safe value before multiplication |
| `digit` | Numeric value of current character |
| `result` | Number constructed negatively |

### Character to digit conversion

```java
int digit = s.charAt(i) - '0';
```

Examples:

```text
'4' - '0' = 4
'8' - '0' = 8
'0' - '0' = 0
```

---

## 7. Negative Accumulator – Overflow Logic

### Step 1

```java
int limit = (sign == -1)
        ? Integer.MIN_VALUE
        : -Integer.MAX_VALUE;
```

For negative input:

```text
limit = -2147483648
```

For positive input:

```text
limit = -2147483647
```

We use negative boundaries because the result is accumulated negatively.

### Step 2

```java
int multMin = limit / 10;
```

This calculates the minimum safe value before multiplying by 10.

For negative input:

```text
-2147483648 / 10 = -214748364
```

### Step 3

```java
if (result < multMin)
```

If true, multiplying by 10 would exceed the allowed negative boundary.

### Step 4

```java
result *= 10;
```

Multiply the accumulated negative value by 10.

### Step 5

```java
if (result < limit + digit)
```

We check whether subtracting the next digit would go below the allowed limit.

### Step 6

```java
result -= digit;
```

Append the digit to the negative number.

For example:

```text
result = -42
digit = 8

result = -42 * 10
       = -420

result = -420 - 8
       = -428
```

### Step 7

```java
return sign * result;
```

If the input was positive, the negative accumulated value is converted to positive.

If the input was negative, it remains negative.

---

## 8. Dry Run 1 – Positive Number

Input:

```text
s = "123"
```

Initial state:

```text
sign = 1
limit = -2147483647
result = 0
```

| Digit | Before update | After multiplying | After subtracting digit |
|---|---:|---:|---:|
| 1 | 0 | 0 | -1 |
| 2 | -1 | -10 | -12 |
| 3 | -12 | -120 | -123 |

Final:

```java
return 1 * -123;
```

Output:

```text
123
```

## 9. Dry Run 2 – Negative Number

Input:

```text
s = "-42"
```

Initial:

```text
sign = -1
limit = -2147483648
result = 0
```

| Digit | Before update | After multiplying | After subtracting |
|---|---:|---:|---:|
| 4 | 0 | 0 | -4 |
| 2 | -4 | -40 | -42 |

Final:

```java
return -1 * -42;
```

Output:

```text
-42
```

## 10. Dry Run 3 – Positive Overflow

Input:

```text
"2147483648"
```

Positive limit:

```text
limit = -2147483647
multMin = -214748364
```

The accumulated result reaches:

```text
result = -214748364
```

Next digit:

```text
digit = 8
```

After multiplication:

```text
result = -2147483640
```

Check:

```text
limit + digit
= -2147483647 + 8
= -2147483639
```

Now:

```text
-2147483640 < -2147483639
```

True.

Therefore, positive overflow is detected.

Output:

```text
2147483647
```

## 11. Dry Run 4 – Exact Negative Minimum

Input:

```text
"-2147483648"
```

Negative limit:

```text
limit = -2147483648
```

The algorithm can safely construct:

```text
result = -2147483648
```

No overflow occurs.

Final:

```java
return -1 * -2147483648;
```

Java returns:

```text
-2147483648
```

This works because multiplying Integer.MIN_VALUE by -1 in Java still produces Integer.MIN_VALUE due to two's-complement integer overflow. The desired result is therefore preserved.

---

## 12. Edge Cases

| Input | Expected output | Reason |
|---|---:|---|
| "" | 0 | Empty string |
| "   " | 0 | Only spaces |
| "abc" | 0 | No initial digits |
| "+" | 0 | No digit after sign |
| "-" | 0 | No digit after sign |
| "+-12" | 0 | Invalid second sign |
| "000123" | 123 | Leading zeros |
| "000" | 0 | All zeros |
| "123abc456" | 123 | Stop at first non-digit |
| "2147483648" | 2147483647 | Positive overflow |
| "-2147483649" | -2147483648 | Negative overflow |
| "  +0012" | 12 | Spaces, sign and leading zeros |

---

## 13. Complexity Analysis

Let `n` be the length of the input string.

| Complexity | Value | Explanation |
|---|---|---|
| Time | O(n) | Each character is processed at most once |
| Auxiliary Space | O(1) | Only a fixed number of variables |

**Optimal:** O(n) time and O(1) auxiliary space.

---

## 14. Common Mistakes

1. Skipping spaces anywhere instead of only at the beginning.
2. Accepting multiple signs.
3. Continuing to read digits after encountering a letter.
4. Using `Integer.parseInt()` without overflow handling.
5. Checking overflow after multiplication.
6. Assuming positive and negative integer limits are symmetrical.
7. Trying to store 2147483648 in a positive `int`.
8. Forgetting to return 0 when no digits are found.

---

## 15. Interview Explanation

"I solve this problem using a single-pass string traversal. First, I skip leading spaces and identify the optional sign. Then I process consecutive digits. I use negative accumulation because Java's negative integer range has one additional value compared with its positive range. Before multiplying by 10 and subtracting the next digit, I check both overflow conditions. If overflow is detected, I return the corresponding integer boundary. Otherwise, I apply the sign and return the final result."

---

## 16. Revision Checklist

- [ ] Understand the atoi rules.
- [ ] Understand leading whitespace handling.
- [ ] Understand sign identification.
- [ ] Understand character-to-digit conversion.
- [ ] Understand positive integer overflow.
- [ ] Understand negative integer overflow.
- [ ] Understand why negative accumulation is safer.
- [ ] Dry-run positive and negative inputs.
- [ ] Test both integer boundaries.
- [ ] Solve independently without referring to notes.


**Final takeaway:** The most important concept in this problem is overflow detection before arithmetic operations. Negative accumulation is the cleanest way to support the complete Java `int` range.