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
