
# LC 76 - Minimum Window Substring

## 1. Problem Details

- LeetCode Number: 76
- Difficulty: Hard
- Topic: Strings
- Pattern: Sliding Window + HashMap
- Language: Java

### Problem Statement

Given two strings `s` and `t`, find the minimum-length
substring of `s` that contains every character in `t`,
including duplicate characters.

Return an empty string if no valid substring exists.

### Example

Input:
s = "ADOBECODEBANC"
t = "ABC"

Output:
"BANC"

Explanation:
The substring "BANC" contains A, B and C.
It is the smallest valid substring.

---

## 2. Main Concept

We use the Sliding Window technique with two pointers:

- `left`: Starting position of the current window.
- `right`: Ending position of the current window.

The window expands using `right` and shrinks using `left`.

### Main Logic

1. Store the required frequency of every character in `t`.
2. Move `right` to expand the window.
3. Track how many required characters are still missing.
4. When all required characters are found, move `left` forward.
5. Update the minimum window whenever a smaller valid window is found.
6. Return the smallest valid substring.

### Visual Representation

s = "ADOBECODEBANC"

First valid window:

[ADOBEC]ODEBANC

After shrinking:

A[DOBEC]ODEBANC

Later valid window:

ADOBECODE[BANC]

Final answer:

"BANC"

---

## 3. HashMap Frequency Concept

The HashMap stores the frequency of characters still needed
relative to the current window.

Initially:

t = "ABC"

| Character | Frequency |
|-----------|-----------|
| A         | 1         |
| B         | 1         |
| C         | 1         |

### Meaning of Map Values

| Map Value | Meaning |
|-----------|---------|
| Positive  | More occurrences are required |
| Zero      | Exactly enough occurrences are present |
| Negative  | Extra occurrences are present |

Example:

If A has frequency 1:

- Adding A changes it to 0.
- Adding another A changes it to -1.
- Removing an A increases its frequency by 1.

Important:
The map does not store the original frequency after the
window starts moving. It tracks the remaining requirement.

---

## 4. Variable Explanation

| Variable | Purpose |
|----------|---------|
| s | Main string |
| t | Target string |
| map | Required character frequencies |
| left | Starting index of current window |
| right | Ending index of current window |
| ch | Character added from the right |
| leftChar | Character removed from the left |
| required | Number of required character occurrences still missing |
| start | Starting index of the best window |
| minLength | Length of the smallest valid window |

### Initial Values

For:

s = "ADOBECODEBANC"
t = "ABC"

| Variable | Initial Value |
|----------|---------------|
| left | 0 |
| start | 0 |
| minLength | Integer.MAX_VALUE |
| required | 3 |

---

## 5. Important Conditions Explained

### Condition 1: Length Check

```java
if (s.length() < t.length()) {
    return "";
}
```

If s is shorter than t, it cannot contain all required
characters.

Example:

s = "AB"
t = "ABC"

Since 2 < 3, return "".

### Condition 2: Check Whether Character Is Required

```java
if (map.getOrDefault(ch, 0) > 0) {
    required--;
}
```

Checks whether the current character is still needed.

- Positive value: Required character found. Decrease required.
- Zero: Already have enough.
- Negative: Already have extra occurrences.

### Condition 3: Update Frequency When Adding a Character

```java
map.put(ch, map.getOrDefault(ch, 0) - 1);
```

Every character entering the window decreases its frequency.

This also handles characters that are not in t.
Their default frequency is 0, so they become -1.

### Condition 4: Check Whether Window Is Valid

```java
while (required == 0)
```

When required becomes zero, the current window contains
all required character occurrences.

Now we try to shrink the window.

### Condition 5: Update Minimum Window

```java
if (right - left + 1 < minLength) {
    minLength = right - left + 1;
    start = left;
}
```

Window length:

right - left + 1

If the current window is smaller than the previously
recorded window, update the answer.

### Condition 6: Remove Leftmost Character

```java
char leftChar = s.charAt(left);

map.put(leftChar, map.get(leftChar) + 1);
```

We remove the leftmost character from the current window.

Its frequency increases because we need to account for
the character that has been removed.

### Condition 7: Check Whether Window Became Invalid

```java
if (map.get(leftChar) > 0) {
    required++;
}
```

If the frequency becomes positive, we now need another
occurrence of that character.

Therefore, the window is invalid.

### Condition 8: Move Left Pointer

```java
left++;
```

Move the starting position forward by one.

---

# 6. COMPLETE DRY RUN

## Given Input

s = "ADOBECODEBANC"
t = "ABC"

String indexes:

| Index | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |
|-------|---|---|---|---|---|---|---|---|---|---|----|----|----|
| Char  | A | D | O | B | E | C | O | D | E | B | A | N | C |

Initial HashMap:

| Character | Frequency |
|-----------|-----------|
| A | 1 |
| B | 1 |
| C | 1 |

Initial values:

left = 0
start = 0
minLength = Integer.MAX_VALUE
required = 3

---

## PART A: Right Pointer Expansion

### Iteration 1: right = 0

Current character = A

Condition:

map.getOrDefault('A', 0) > 0

1 > 0 = TRUE

required = 3 - 1 = 2

Map update:

A = 1 - 1 = 0

Map:
A = 0, B = 1, C = 1

While condition:

required == 0
2 == 0 = FALSE

Current window = "A"

left = 0
right = 0
required = 2

### Iteration 2: right = 1

Current character = D

Condition:

map.getOrDefault('D', 0) > 0

0 > 0 = FALSE

required remains 2.

Map update:

D = 0 - 1 = -1

While condition:

2 == 0 = FALSE

Current window = "AD"

### Iteration 3: right = 2

Current character = O

Condition:

map.getOrDefault('O', 0) > 0

0 > 0 = FALSE

required remains 2.

Map update:

O = 0 - 1 = -1

While condition:

2 == 0 = FALSE

Current window = "ADO"

### Iteration 4: right = 3

Current character = B

Condition:

map.getOrDefault('B', 0) > 0

1 > 0 = TRUE

required = 2 - 1 = 1

Map update:

B = 1 - 1 = 0

While condition:

1 == 0 = FALSE

Current window = "ADOB"

### Iteration 5: right = 4

Current character = E

Condition:

map.getOrDefault('E', 0) > 0

0 > 0 = FALSE

required remains 1.

Map update:

E = 0 - 1 = -1

While condition:

1 == 0 = FALSE

Current window = "ADOBE"

### Iteration 6: right = 5

Current character = C

Condition:

map.getOrDefault('C', 0) > 0

1 > 0 = TRUE

required = 1 - 1 = 0

Map update:

C = 1 - 1 = 0

While condition:

0 == 0 = TRUE

FIRST VALID WINDOW FOUND.

Current window = "ADOBEC"

---

## PART B: First Shrinking Phase

Current values:

left = 0
right = 5
required = 0

### While Iteration 1

Calculate window length:

right - left + 1

5 - 0 + 1 = 6

Condition:

6 < Integer.MAX_VALUE = TRUE

Update:

minLength = 6
start = 0

Remove leftmost character:

leftChar = s.charAt(0) = A

Map update:

A = 0 + 1 = 1

Condition:

map.get('A') > 0

1 > 0 = TRUE

required = 0 + 1 = 1

Move left:

left = 1

While condition:

1 == 0 = FALSE

STOP SHRINKING.

Current window after removing A would be "DOBEC",
but it is invalid because A is missing.

Best window = "ADOBEC"

---

## PART C: Continue Right Pointer Expansion

### Iteration 7: right = 6

Current character = O

Condition:

map.getOrDefault('O', 0) > 0

-1 > 0 = FALSE

required remains 1.

Map update:

O = -1 - 1 = -2

While condition:

1 == 0 = FALSE

Current window = "DOBECO"

Why?

left = 1
right = 6

Characters from index 1 through index 6:

D O B E C O

Therefore, current window = "DOBECO".

### Iteration 8: right = 7

Current character = D

Condition:

-1 > 0 = FALSE

required remains 1.

Map update:

D = -1 - 1 = -2

While condition:

1 == 0 = FALSE

Current window = "DOBECOD"

### Iteration 9: right = 8

Current character = E

Condition:

-1 > 0 = FALSE

required remains 1.

Map update:

E = -1 - 1 = -2

While condition:

1 == 0 = FALSE

Current window = "DOBECODE"

### Iteration 10: right = 9

Current character = B

Condition:

map.getOrDefault('B', 0) > 0

0 > 0 = FALSE

required remains 1.

Map update:

B = 0 - 1 = -1

While condition:

1 == 0 = FALSE

Current window = "DOBECODEB"

### Iteration 11: right = 10

Current character = A

Condition:

map.getOrDefault('A', 0) > 0

1 > 0 = TRUE

required = 1 - 1 = 0

Map update:

A = 1 - 1 = 0

While condition:

0 == 0 = TRUE

SECOND VALID WINDOW FOUND.

Current window = "DOBECODEBA"

---

## PART D: Second Shrinking Phase

Initial values:

left = 1
right = 10
required = 0
minLength = 6
start = 0

### While Iteration 1

Current window length:

10 - 1 + 1 = 10

Condition:

10 < 6 = FALSE

Do not update minimum.

Remove D:

D = -2 + 1 = -1

Condition:

-1 > 0 = FALSE

required remains 0.

left = 2

### While Iteration 2

Current window = "OBECODEBA"

Length:

10 - 2 + 1 = 9

Condition:

9 < 6 = FALSE

Remove O:

O = -2 + 1 = -1

Condition:

-1 > 0 = FALSE

required remains 0.

left = 3

### While Iteration 3

Current window = "BECODEBA"

Length:

10 - 3 + 1 = 8

Condition:

8 < 6 = FALSE

Remove B:

B = -1 + 1 = 0

Condition:

0 > 0 = FALSE

required remains 0.

left = 4

### While Iteration 4

Current window = "ECODEBA"

Length:

10 - 4 + 1 = 7

Condition:

7 < 6 = FALSE

Remove E:

E = -2 + 1 = -1

Condition:

-1 > 0 = FALSE

required remains 0.

left = 5

### While Iteration 5

Current window = "CODEBA"

Length:

10 - 5 + 1 = 6

Condition:

6 < 6 = FALSE

Remove C:

C = 0 + 1 = 1

Condition:

1 > 0 = TRUE

required = 0 + 1 = 1

left = 6

While condition:

1 == 0 = FALSE

STOP SHRINKING.

The window became invalid because C was removed.

Best window remains "ADOBEC".

---

## PART E: Final Right Pointer Expansion

### Iteration 12: right = 11

Current character = N

Condition:

map.getOrDefault('N', 0) > 0

0 > 0 = FALSE

required remains 1.

Map update:

N = 0 - 1 = -1

While condition:

1 == 0 = FALSE

Current window = "ODEBANC"

### Iteration 13: right = 12

Current character = C

Condition:

map.getOrDefault('C', 0) > 0

1 > 0 = TRUE

required = 1 - 1 = 0

Map update:

C = 1 - 1 = 0

While condition:

0 == 0 = TRUE

THIRD VALID WINDOW FOUND.

Current window = "ODEBANC"

---

## PART F: Final Shrinking Phase

Initial values:

left = 6
right = 12
required = 0
minLength = 6
start = 0

### While Iteration 1

Current window = "ODEBANC"

Length:

12 - 6 + 1 = 7

Condition:

7 < 6 = FALSE

Remove O:

O = -1 + 1 = 0

Condition:

0 > 0 = FALSE

required remains 0.

left = 7

### While Iteration 2

Current window = "DEBANC"

Length:

12 - 7 + 1 = 6

Condition:

6 < 6 = FALSE

Remove D:

D = -1 + 1 = 0

Condition:

0 > 0 = FALSE

required remains 0.

left = 8

### While Iteration 3

Current window = "EBANC"

Length:

12 - 8 + 1 = 5

Condition:

5 < 6 = TRUE

Update:

minLength = 5
start = 8

Remove E:

E = -1 + 1 = 0

Condition:

0 > 0 = FALSE

required remains 0.

left = 9

### While Iteration 4

Current window = "BANC"

Length:

12 - 9 + 1 = 4

Condition:

4 < 5 = TRUE

Update:

minLength = 4
start = 9

Remove B:

B = 0 + 1 = 1

Condition:

1 > 0 = TRUE

required = 0 + 1 = 1

left = 10

While condition:

1 == 0 = FALSE

STOP SHRINKING.

Final best window = "BANC".

---

## 7. Complete Iteration Summary

| right | Character | required after adding | Shrinking | Best window |
|-------|-----------|-----------------------|-----------|-------------|
| 0 | A | 2 | No | — |
| 1 | D | 2 | No | — |
| 2 | O | 2 | No | — |
| 3 | B | 1 | No | — |
| 4 | E | 1 | No | — |
| 5 | C | 0 | Yes | ADOBEC |
| 6 | O | 1 | No | ADOBEC |
| 7 | D | 1 | No | ADOBEC |
| 8 | E | 1 | No | ADOBEC |
| 9 | B | 1 | No | ADOBEC |
| 10 | A | 0 | Yes | ADOBEC |
| 11 | N | 1 | No | ADOBEC |
| 12 | C | 0 | Yes | BANC |

---

## 8. Final Variable Values

| Variable | Final Value |
|----------|-------------|
| left | 10 |
| right | 12 |
| required | 1 |
| start | 9 |
| minLength | 4 |

The for loop terminates because:

right < s.length()

13 < 13 = FALSE

---

## 9. Return Statement

```java
if (minLength == Integer.MAX_VALUE) {
    return "";
}

return s.substring(start, start + minLength);
```

Condition:

4 == Integer.MAX_VALUE = FALSE

Return:

s.substring(9, 9 + 4)

s.substring(9, 13)

Java includes the starting index and excludes the ending index.

Indexes selected:

| Index | 9 | 10 | 11 | 12 |
|-------|---|----|----|----|
| Char | B | A | N | C |

Final output:

"BANC"

---

## 10. Important Edge Cases

### Case 1: s is shorter than t

Input:
s = "AB"
t = "ABC"

Output:
""

Reason:
A valid window is impossible.

### Case 2: Both strings are equal

Input:
s = "a"
t = "a"

Output:
"a"

Reason:
The entire string is a valid window.

### Case 3: Duplicate characters

Input:
s = "AAABBC"
t = "AABC"

Output:
"AABBC"

Reason:
The answer must contain two A's, one B, and one C.

### Case 4: No valid window

Input:
s = "ADOBEC"
t = "XYZ"

Output:
""

Reason:
The target characters do not exist in s.

### Case 5: Repeated target characters

Input:
s = "ABBAAC"
t = "AABC"

Output:
"BBAAC"

Reason:
The answer must contain two A's, one B, and one C.

---

## 11. Common Mistakes

1. Building the frequency map using s instead of t.
2. Using the wrong length condition (`s.length() > t.length()`).
3. Ignoring duplicate characters in t.
4. Forgetting to decrease `required` only when a needed character is added.
5. Shrinking before recording the current valid window.
6. Forgetting to increase the frequency when removing the left character.
7. Forgetting to increase `required` when a needed character is lost.
8. Returning the wrong substring indexes.
9. Converting strings to lowercase even though the problem is case-sensitive.

---

## 12. Time and Space Complexity

Let:
- n = length of s
- m = length of t
- k = number of distinct characters in the map

### Time Complexity: O(n + m)

- Building the HashMap: O(m)
- Right pointer traversal: O(n)
- Left pointer traversal: O(n)

Each pointer moves forward at most n times.

Total: O(n + m).

### Space Complexity: O(k)

The HashMap stores the distinct characters from t and
any characters encountered in the current window.

Since the map may also contain characters from s,
the general bound is O(min(n, alphabet size) + distinct(t)),
which is O(n + m) without a fixed alphabet assumption.

---

## 13. Interview Explanation

"I used the Sliding Window technique with a HashMap to
find the minimum substring containing all characters
of the target string, including duplicates.

First, I stored the frequency of every character in t
inside a HashMap.

Then I used two pointers, left and right, to maintain
a window in s.

I expanded the window using the right pointer and
updated the frequency map. I also maintained a required
counter to track how many target character occurrences
were still missing.

Whenever required became zero, the current window was
valid. I then updated the minimum window and moved the
left pointer forward to find a smaller valid window.

If removing a character made its required frequency
positive, I increased required and stopped shrinking.

Finally, I returned the smallest valid substring.

The overall time complexity is O(n + m), because both
pointers traverse the string at most once."

---

## 14. Revision Checklist

- [ ] Understand Sliding Window.
- [ ] Understand HashMap frequency counting.
- [ ] Understand getOrDefault().
- [ ] Understand why map values can become negative.
- [ ] Understand the required counter.
- [ ] Understand when to expand the window.
- [ ] Understand when to shrink the window.
- [ ] Understand the minimum length update.
- [ ] Dry-run the complete example independently.
- [ ] Practice duplicate-character test cases.
- [ ] Explain the solution in an interview.




# LeetCode 76 – Minimum Window Substring
**Difficulty:** Hard  
**Pattern:** Sliding Window + HashMap  
**Language:** Java

## 1. Problem Example

```java
s = "ADOBECODEBANC"
t = "ABC"
```

**Objective:** Find the smallest continuous substring of `s` that contains all characters of `t`.

Expected output:

```text
"BANC"
```

The answer must contain:
- A – at least 1 occurrence
- B – at least 1 occurrence
- C – at least 1 occurrence

---

## 2. Initial Setup

### Length condition

```java
if (s.length() < t.length()) {
    return "";
}
```

| Expression | Value | Condition |
|---|---:|---|
| `s.length()` | 13 | |
| `t.length()` | 3 | |
| `13 < 3` | | FALSE |

**Explanation:** If `s` is shorter than `t`, it cannot contain all required characters.

### Build the HashMap

```java
Map<Character, Integer> map = new HashMap<>();

for (int i = 0; i < t.length(); i++) {
    char ch = t.charAt(i);
    map.put(ch, map.getOrDefault(ch, 0) + 1);
}
```

We count the frequency of characters in `t` because `t` defines what the answer must contain.

| i | `t.charAt(i)` | `getOrDefault(ch, 0)` | New frequency |
|---:|---|---:|---|
| 0 | A | 0 | A = 1 |
| 1 | B | 0 | B = 1 |
| 2 | C | 0 | C = 1 |

**HashMap after the loop**

| Key | Value |
|---|---:|
| A | 1 |
| B | 1 |
| C | 1 |

### Initialize variables

```java
int left = 0;
int start = 0;
int minLength = Integer.MAX_VALUE;
int required = t.length();
```

| Variable | Initial value | Meaning |
|---|---:|---|
| `left` | 0 | Starting index of current window |
| `start` | 0 | Starting index of best window |
| `minLength` | 2,147,483,647 | No valid window found yet |
| `required` | 3 | Total target characters still needed |

### String with indexes

```text
Index:  0 1 2 3 4 5 6 7 8 9 10 11 12
String: A D O B E C O D E B A  N  C
```

---

## 3. Understand HashMap Frequency Values

The HashMap stores how many more occurrences of each character are required relative to the current window.

| Map value | Meaning |
|---:|---|
| Positive (1, 2, ...) | Still need that many occurrences |
| 0 | Have exactly enough occurrences |
| Negative (-1, -2, ...) | Have extra occurrences |

**When adding a character:** Decrease its map frequency.

**When removing a character:** Increase its map frequency.

Example for A:

| Operation | Map value | Meaning |
|---|---:|---|
| Initially | 1 | Need one A |
| Add A | 0 | Have enough A's |
| Add another A | -1 | Have one extra A |
| Remove A | 0 | Have exactly enough A's |

---

## 4. Main Sliding Window Logic

```java
for (int right = 0; right < s.length(); right++) {

    char ch = s.charAt(right);

    if (map.getOrDefault(ch, 0) > 0) {
        required--;
    }

    map.put(ch, map.getOrDefault(ch, 0) - 1);

    while (required == 0) {

        if (right - left + 1 < minLength) {
            minLength = right - left + 1;
            start = left;
        }

        char leftChar = s.charAt(left);

        map.put(leftChar, map.get(leftChar) + 1);

        if (map.get(leftChar) > 0) {
            required++;
        }

        left++;
    }
}
```

### Important conditions

**Condition 1 – Check whether the character is still needed**

```java
if (map.getOrDefault(ch, 0) > 0)
```

- Positive: Character is still required. Decrease `required`.
- Zero: We already have enough.
- Negative: We have extra occurrences.

**Condition 2 – Check whether the current window is valid**

```java
while (required == 0)
```

- TRUE: All required characters are present. Try shrinking.
- FALSE: Window is invalid. Continue expanding with `right`.

**Condition 3 – Check whether removing a character makes the window invalid**

```java
if (map.get(leftChar) > 0)
```

- TRUE: We removed a required occurrence. Increase `required`.
- FALSE: The window still has enough of that character.

---

# 5. Complete Dry Run – Every Right Pointer Iteration

## Iteration 1 – right = 0

Current character:

```java
s.charAt(0) = 'A'
```

**Condition 1:**

```java
map.getOrDefault('A', 0) > 0
```

- Map value of A = 1
- `1 > 0` → TRUE

Update:

```java
required--;
```

`required = 3 - 1 = 2`

**Map update:**

```java
map.put('A', 1 - 1);
```

A = 0

| Character | Value |
|---|---:|
| A | 0 |
| B | 1 |
| C | 1 |

**While condition:**

```java
required == 0
```

`2 == 0` → FALSE

Current window: `"A"`

| Variable | Value |
|---|---:|
| left | 0 |
| right | 0 |
| required | 2 |

No shrinking.

## Iteration 2 – right = 1

Current character:

```java
s.charAt(1) = 'D'
```

**Condition 1:**

```java
map.getOrDefault('D', 0) > 0
```

D is not in the map.

`0 > 0` → FALSE

`required = 2`

**Map update:**

```java
map.put('D', 0 - 1);
```

D = -1

**While condition:**

`2 == 0` → FALSE

Current window: `"AD"`

| Variable | Value |
|---|---:|
| left | 0 |
| right | 1 |
| required | 2 |

No shrinking.

## Iteration 3 – right = 2

Current character:

```java
s.charAt(2) = 'O'
```

**Condition 1:**

`map.getOrDefault('O', 0) > 0`

`0 > 0` → FALSE

`required = 2`

**Map update:**

O = 0 - 1 = -1

**While condition:**

`2 == 0` → FALSE

Current window: `"ADO"`

| Variable | Value |
|---|---:|
| left | 0 |
| right | 2 |
| required | 2 |

No shrinking.

## Iteration 4 – right = 3

Current character:

```java
s.charAt(3) = 'B'
```

**Condition 1:**

`map.getOrDefault('B', 0) > 0`

`1 > 0` → TRUE

Update:

`required = 2 - 1 = 1`

**Map update:**

B = 1 - 1 = 0

**While condition:**

`1 == 0` → FALSE

Current window: `"ADOB"`

| Variable | Value |
|---|---:|
| left | 0 |
| right | 3 |
| required | 1 |

No shrinking.

## Iteration 5 – right = 4

Current character:

```java
s.charAt(4) = 'E'
```

**Condition 1:**

`map.getOrDefault('E', 0) > 0`

`0 > 0` → FALSE

`required = 1`

**Map update:**

E = 0 - 1 = -1

**While condition:**

`1 == 0` → FALSE

Current window: `"ADOBE"`

No shrinking.

## Iteration 6 – right = 5

Current character:

```java
s.charAt(5) = 'C'
```

**Condition 1:**

`map.getOrDefault('C', 0) > 0`

`1 > 0` → TRUE

Update:

`required = 1 - 1 = 0`

**Map update:**

C = 1 - 1 = 0

Map:

| Character | Value |
|---|---:|
| A | 0 |
| B | 0 |
| C | 0 |
| D | -1 |
| O | -1 |
| E | -1 |

**While condition:**

`0 == 0` → TRUE

**First valid window found:**

```text
ADOBEC
```

### First shrinking phase – right = 5

Initial values:

- left = 0
- right = 5
- required = 0

**While iteration 1**

Check minimum length:

```java
if (right - left + 1 < minLength)
```

`5 - 0 + 1 = 6`

`6 < 2147483647` → TRUE

Update:

```java
minLength = 6;
start = 0;
```

Best window = `"ADOBEC"`

Remove leftmost character:

```java
char leftChar = s.charAt(left);
```

leftChar = A

Map update:

A = 0 + 1 = 1

Check:

```java
if (map.get('A') > 0)
```

`1 > 0` → TRUE

`required = 0 + 1 = 1`

Move left:

`left++` → left = 1

Check while condition:

`1 == 0` → FALSE

Stop shrinking because removing A made the window invalid.

Best window = `"ADOBEC"` (length 6).

---

## Iteration 7 – right = 6

Current character:

```java
s.charAt(6) = 'O'
```

**Condition 1:**

O currently has value -1.

`-1 > 0` → FALSE

`required = 1`

**Map update:**

O = -1 - 1 = -2

**While condition:**

`1 == 0` → FALSE

Current window: `"DOBECO"`

No valid window yet.

## Iteration 8 – right = 7

Current character:

```java
s.charAt(7) = 'D'
```

**Condition 1:**

D currently has value -1.

`-1 > 0` → FALSE

`required = 1`

**Map update:**

D = -1 - 1 = -2

**While condition:**

`1 == 0` → FALSE

Current window: `"DOBECOD"`

## Iteration 9 – right = 8

Current character:

```java
s.charAt(8) = 'E'
```

**Condition 1:**

E currently has value -1.

`-1 > 0` → FALSE

`required = 1`

**Map update:**

E = -1 - 1 = -2

**While condition:**

`1 == 0` → FALSE

Current window: `"DOBECODE"`

## Iteration 10 – right = 9

Current character:

```java
s.charAt(9) = 'B'
```

**Condition 1:**

B currently has value 0.

`0 > 0` → FALSE

We already have enough B characters.

`required = 1`

**Map update:**

B = 0 - 1 = -1

We now have an extra B.

**While condition:**

`1 == 0` → FALSE

Current window: `"DOBECODEB"`

## Iteration 11 – right = 10

Current character:

```java
s.charAt(10) = 'A'
```

**Condition 1:**

A currently has value 1.

`1 > 0` → TRUE

Update:

`required = 1 - 1 = 0`

**Map update:**

A = 1 - 1 = 0

Map:

| Character | Value |
|---|---:|
| A | 0 |
| B | -1 |
| C | 0 |
| D | -2 |
| E | -2 |
| O | -2 |

**While condition:**

`0 == 0` → TRUE

**Second valid window found:**

```text
DOBECODEBA
```

### Second shrinking phase – right = 10

Initial values:

- left = 1
- right = 10
- required = 0
- minLength = 6

**While iteration 1**

Current window: `"DOBECODEBA"`

Length:

`10 - 1 + 1 = 10`

`10 < 6` → FALSE

Remove D:

D = -2 + 1 = -1

Check:

`-1 > 0` → FALSE

`required = 0`

Move left:

`left = 2`

**While iteration 2**

Current window: `"OBECODEBA"`

Length:

`10 - 2 + 1 = 9`

`9 < 6` → FALSE

Remove O:

O = -2 + 1 = -1

Check:

`-1 > 0` → FALSE

`required = 0`

Move left:

`left = 3`

**While iteration 3**

Current window: `"BECODEBA"`

Length:

`10 - 3 + 1 = 8`

`8 < 6` → FALSE

Remove B:

B = -1 + 1 = 0

Check:

`0 > 0` → FALSE

`required = 0`

Move left:

`left = 4`

**While iteration 4**

Current window: `"ECODEBA"`

Length:

`10 - 4 + 1 = 7`

`7 < 6` → FALSE

Remove E:

E = -2 + 1 = -1

Check:

`-1 > 0` → FALSE

`required = 0`

Move left:

`left = 5`

**While iteration 5**

Current window: `"CODEBA"`

Length:

`10 - 5 + 1 = 6`

`6 < 6` → FALSE

Remove C:

C = 0 + 1 = 1

Check:

`1 > 0` → TRUE

Update:

`required = 0 + 1 = 1`

Move left:

`left = 6`

**While condition:**

`1 == 0` → FALSE

Stop shrinking because removing C made the window invalid.

Best window is still `"ADOBEC"` (length 6).

---

## Iteration 12 – right = 11

Current character:

```java
s.charAt(11) = 'N'
```

**Condition 1:**

N is not in the map.

`map.getOrDefault('N', 0) = 0`

`0 > 0` → FALSE

`required = 1`

**Map update:**

N = 0 - 1 = -1

**While condition:**

`1 == 0` → FALSE

Current window: `"ODEBANC"`

## Iteration 13 – right = 12

Current character:

```java
s.charAt(12) = 'C'
```

**Condition 1:**

C currently has value 1.

`1 > 0` → TRUE

Update:

`required = 1 - 1 = 0`

**Map update:**

C = 1 - 1 = 0

Map:

| Character | Value |
|---|---:|
| A | 0 |
| B | 0 |
| C | 0 |
| D | -1 |
| E | -1 |
| O | -1 |
| N | -1 |

**While condition:**

`0 == 0` → TRUE

**Third valid window found:**

```text
ODEBANC
```

### Final shrinking phase – right = 12

Starting values:

| Variable | Value |
|---|---:|
| left | 6 |
| right | 12 |
| required | 0 |
| start | 0 |
| minLength | 6 |

**While iteration 1**

Current window: `"ODEBANC"`

Length:

`12 - 6 + 1 = 7`

`7 < 6` → FALSE

Remove O:

O = -1 + 1 = 0

Check:

`0 > 0` → FALSE

`required = 0`

Move left:

`left = 7`

**While iteration 2**

Current window: `"DEBANC"`

Length:

`12 - 7 + 1 = 6`

`6 < 6` → FALSE

Remove D:

D = -1 + 1 = 0

Check:

`0 > 0` → FALSE

`required = 0`

Move left:

`left = 8`

**While iteration 3**

Current window: `"EBANC"`

Length:

`12 - 8 + 1 = 5`

`5 < 6` → TRUE

Update:

```java
minLength = 5;
start = 8;
```

Best window = `"EBANC"`

Remove E:

E = -1 + 1 = 0

Check:

`0 > 0` → FALSE

`required = 0`

Move left:

`left = 9`

**While iteration 4**

Current window: `"BANC"`

Length:

`12 - 9 + 1 = 4`

`4 < 5` → TRUE

Update:

```java
minLength = 4;
start = 9;
```

Best window = `"BANC"`

Remove B:

B = 0 + 1 = 1

Check:

`1 > 0` → TRUE

Update:

`required = 0 + 1 = 1`

Move left:

`left = 10`

**While condition:**

`1 == 0` → FALSE

Stop shrinking because removing B made the window invalid.

Best window = `"BANC"` (length 4).

---

# 6. Final State After All 13 Iterations

The `for` loop checks:

```java
right < s.length()
```

`13 < 13` → FALSE

The loop terminates.

### Final variable values

| Variable | Final value | Explanation |
|---|---:|---|
| `left` | 10 | Next character to remove |
| `right` | 12 | Last index of s |
| `required` | 1 | Current window is invalid |
| `start` | 9 | Starting index of best window |
| `minLength` | 4 | Smallest valid window length |

### Final HashMap

| Character | Frequency |
|---|---:|
| A | 0 |
| B | 1 |
| C | 0 |
| D | 0 |
| E | 0 |
| O | 0 |
| N | -1 |

**Important:** The final map represents the frequency still needed relative to the current window, not the frequency of the original string.

---

# 7. Return Statement

```java
if (minLength == Integer.MAX_VALUE) {
    return "";
}

return s.substring(start, start + minLength);
```

First condition:

`4 == 2147483647` → FALSE

Execute:

```java
return s.substring(9, 9 + 4);
```

```java
return s.substring(9, 13);
```

Java `substring(start, end)` includes the starting index and excludes the ending index.

Original string:

```text
Index:  0 1 2 3 4 5 6 7 8 9 10 11 12
String: A D O B E C O D E B A  N  C
                              ↑
                          start = 9
```

Characters at indexes 9, 10, 11, 12:

```text
B A N C
```

**Final output:**

```text
"BANC"
```

- Contains A, B, and C.
- Length = 4.
- No shorter valid window exists.

---

# 8. Summary of Every Right-Pointer Iteration

| right | Character added | required after adding | Shrinking? | Best window |
|---:|:---:|---:|---|---|
| 0 | A | 2 | No | — |
| 1 | D | 2 | No | — |
| 2 | O | 2 | No | — |
| 3 | B | 1 | No | — |
| 4 | E | 1 | No | — |
| 5 | C | 0 | Yes | ADOBEC (6) |
| 6 | O | 1 | No | ADOBEC (6) |
| 7 | D | 1 | No | ADOBEC (6) |
| 8 | E | 1 | No | ADOBEC (6) |
| 9 | B | 1 | No | ADOBEC (6) |
| 10 | A | 0 | Yes | ADOBEC (6) |
| 11 | N | 1 | No | ADOBEC (6) |
| 12 | C | 0 | Yes | BANC (4) |

---

# 9. Three Most Important Conditions – Interview Revision

### Condition 1

```java
if (map.getOrDefault(ch, 0) > 0)
```

**Question:** Do we still need this character?

- Positive → Yes, decrease `required`.
- Zero or negative → No, we already have enough.

### Condition 2

```java
while (required == 0)
```

**Question:** Does our current window contain all required characters?

- TRUE → Try shrinking from the left.
- FALSE → Continue expanding with the right pointer.

### Condition 3

```java
if (map.get(leftChar) > 0)
```

**Question:** Did removing the leftmost character make us lose a required character?

- TRUE → The window becomes invalid; increase `required`.
- FALSE → We still have enough of that character.

---

# 10. Complexity Analysis

| Complexity | Value | Reason |
|---|---|---|
| Time | O(n + m) | Each pointer traverses the string at most once; map initialization processes t |
| Space | O(k) | HashMap stores distinct characters, where k is the number of distinct characters |

Here:
- n = length of s
- m = length of t
- k = number of distinct characters stored in the map

---

# 11. Key Takeaway

**Sliding Window = Expand + Validate + Shrink + Update Minimum**

1. Build the frequency map from `t`.
2. Expand the window using `right`.
3. Decrease `required` when a needed character is found.
4. When `required == 0`, the window is valid.
5. Record the current minimum window.
6. Shrink from `left`.
7. If removing a character makes the window invalid, stop shrinking.
8. Continue expanding until the string ends.
9. Return the smallest valid substring.

**Remember:** The goal is not to find the first valid window. The goal is to find the smallest valid window.





