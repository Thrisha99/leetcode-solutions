
# Patterns:
Two pointers, 
frequency arrays,
character mapping,
hashing.



# JAVA STRINGS — COMPLETE REVISION NOTES
## Core Java + DSA + Placement Interview Preparation

**Level:** Beginner to Interview-Ready  
**Language:** Java  
**Purpose:** Concept revision, coding practice, debugging, and placement interviews

---

# 1. WHAT IS A STRING?

A String is a sequence of characters used to represent text.

```java
String name = "Thrisha";
String word = "Java";
String empty = "";
```

Visual representation:

```text
String: "JAVA"

Index:    0     1     2     3
        +-----+-----+-----+-----+
Value:  |  J  |  A  |  V  |  A  |
        +-----+-----+-----+-----+

Length = 4
First character = J
Last character  = A
Last index      = length - 1 = 3
```

## Golden rules

- Indexing starts at `0`.
- The last valid index is `length - 1`.
- Strings are immutable.
- Use `s.length()` to find a String's length.
- Use `s.charAt(i)` to access a character.
- Use `equals()` to compare String contents.

```java
String s = "Hello";

System.out.println(s.length());   // 5
System.out.println(s.charAt(0));  // H
System.out.println(s.charAt(4));  // o
```

---

# 2. STRING VS CHARACTER ARRAY

| Feature | String | Character array |
|---|---|---|
| Declaration | `String s = "Java";` | `char[] a = {'J','a','v','a'};` |
| Length | `s.length()` | `a.length` |
| Access character | `s.charAt(i)` | `a[i]` |
| Change one character directly | No | Yes |
| Immutable? | Yes | No |
| Convert from one to another | `toCharArray()` | `new String(a)` |

Remember:

```java
String s = "Java";
char[] a = {'J', 'a', 'v', 'a'};

System.out.println(s.length()); // 4
System.out.println(a.length);   // 4
```

**Common interview mistake:** `length()` is a method for Strings, whereas `length` is a field for arrays.

---

# 3. CREATING STRINGS

## Method 1: String literal

```java
String s1 = "Java";
String s2 = "Java";
```

Java reuses the same pooled String object for equal literals when applicable.

Conceptual memory diagram:

```text
s1 ──────┐
         ├──────> String Pool
s2 ──────┘          "Java"
```

Therefore:

```java
System.out.println(s1 == s2);      // true
System.out.println(s1.equals(s2)); // true
```

## Method 2: Using `new`

```java
String s1 = new String("Java");
String s2 = new String("Java");
```

Each explicit `new String(...)` creates a distinct String object.

```text
s1 ─────────> String object 1: "Java"

s2 ─────────> String object 2: "Java"
```

Therefore:

```java
System.out.println(s1 == s2);      // false
System.out.println(s1.equals(s2)); // true
```

The literal `"Java"` may also exist in the String pool.

## String Pool

The String Pool is an area of JVM-managed String storage used to reuse interned Strings.

```java
String a = "code";
String b = "code";
String c = new String("code");
```

Conceptually:

```text
String Pool
   "code"
    ↑  ↑
    a  b

Heap String object
    "code"
      ↑
      c
```

The pool diagram is conceptual: exact memory layout is JVM-implementation-dependent.

## `intern()`

`intern()` returns the canonical pooled representation of a String.

```java
String a = new String("Java");
String b = a.intern();
String c = "Java";

System.out.println(b == c); // true
```

Use `intern()` to understand pooling, not as a default requirement in ordinary application code.

---

# 4. `==` VS `equals()`

This is one of the most important Java interview concepts.

| Operator/method | What it checks |
|---|---|
| `==` | Whether references point to the same object |
| `equals()` | Whether String contents are equal |
| `equalsIgnoreCase()` | Whether contents match ignoring letter case |

Example:

```java
String a = "Java";
String b = new String("Java");

System.out.println(a == b);      // false
System.out.println(a.equals(b)); // true
```

Visual explanation:

```text
a ─────> Object 1: "Java"

b ─────> Object 2: "Java"

Same object?     NO  → a == b is false
Same contents?   YES → a.equals(b) is true
```

More examples:

```java
String x = "java";
String y = "JAVA";

System.out.println(x.equals(y));           // false
System.out.println(x.equalsIgnoreCase(y)); // true
```

**Placement rule:** When comparing the actual text entered by a user, `equals()` is usually what you want.

---

# 5. STRING IMMUTABILITY

## What does immutable mean?

Once a String object is created, its character contents cannot be changed.

```java
String s = "cat";
s.concat("s");

System.out.println(s); // cat
```

Why? `concat()` returns a String containing the combined text. The original object remains unchanged.

Correct approach:

```java
String s = "cat";
s = s.concat("s");

System.out.println(s); // cats
```

## Visual diagram

```text
BEFORE concat()

s ─────────> "cat"


s.concat("s")

s ─────────> "cat"       Original unchanged

             "cats"      New result returned


AFTER s = s.concat("s")

s ─────────> "cats"
```

The variable `s` can refer to another String, even though the original String object is immutable.

## Important examples

| Code | Output | Reason |
|---|---|---|
| `s.replace('a','o');` | Original String | Return value ignored |
| `s = s.replace('a','o');` | Updated variable value | Returned String assigned |
| `s.toUpperCase();` | Original String | Return value ignored |
| `s = s.toUpperCase();` | Uppercase value | Returned String assigned |
| `s.concat("!");` | Original String | Return value ignored |
| `s = s.concat("!");` | Combined String | Returned String assigned |

Example:

```java
String s = "map";

s.replace('m', 'c');

System.out.println(s); // map
```

But:

```java
String s = "map";

s = s.replace('m', 'c');

System.out.println(s); // cap
```

## Why are Strings immutable?

Key benefits include:

- Safe sharing of String objects.
- Predictable behavior when Strings are used as keys in collections.
- Support for String Pool reuse.
- Easier reasoning about values across different parts of a program.
- Security benefits when text is used in sensitive operations, although immutability alone does not guarantee security.

---

# 6. STRING INDEXING AND BOUNDARIES

Consider:

```java
String s = "CODE";
```

```text
Character:   C     O     D     E
Index:       0     1     2     3
             |     |     |     |
Valid range: 0  ----------------  length - 1

s.length() = 4
Last index = 3
```

| Expression | Result |
|---|---|
| `s.charAt(0)` | `'C'` |
| `s.charAt(1)` | `'O'` |
| `s.charAt(3)` | `'E'` |
| `s.charAt(4)` | `StringIndexOutOfBoundsException` |
| `s.charAt(-1)` | `StringIndexOutOfBoundsException` |

## Golden boundary rule

```text
Valid index: 0 <= index < s.length()
```

The condition uses `<`, not `<=`.

## Loop boundary flowchart

```text
             START
               |
        Initialize i = 0
               |
       Is i < s.length()?
          /          \
        YES           NO
         |             |
    charAt(i)         STOP
         |
       Process
         |
       i = i + 1
         |
         └─────────────> Check condition again
```

## Forward traversal

```java
String s = "code";

for (int i = 0; i < s.length(); i++) {
    System.out.println(s.charAt(i));
}
```

Output:

```text
c
o
d
e
```

## Why `i <= s.length()` is dangerous

```java
String s = "cat";

for (int i = 0; i <= s.length(); i++) {
    System.out.println(s.charAt(i));
}
```

Dry run:

| `i` | Condition `i <= 3` | Access | Result |
|---:|---|---|---|
| 0 | True | `charAt(0)` | `c` |
| 1 | True | `charAt(1)` | `a` |
| 2 | True | `charAt(2)` | `t` |
| 3 | True | `charAt(3)` | Exception |

The program prints `c`, `a`, and `t` before throwing an exception. It does not finish normally.

---

# 7. STRING TRAVERSAL PATTERNS

## Pattern A: Forward loop

```java
for (int i = 0; i < s.length(); i++) {
    System.out.print(s.charAt(i));
}
```

Memory trick: Start at `0`, increase `i`, stop before `length()`.

## Pattern B: Reverse loop

```java
for (int i = s.length() - 1; i >= 0; i--) {
    System.out.print(s.charAt(i));
}
```

Example:

```text
String = "JAVA"

i = 3 → A
i = 2 → V
i = 1 → A
i = 0 → J
i = -1 → STOP

Output: AVAJ
```

Memory trick: Start at the last valid index, decrease `i`, stop before `-1`.

## Pattern C: Skip the last character

```java
for (int i = 0; i < s.length() - 1; i++) {
    System.out.print(s.charAt(i));
}
```

For `"piano"`, output is `pian`.

## Pattern D: Access the next character

```java
for (int i = 0; i < s.length(); i++) {
    System.out.print(s.charAt(i + 1));
}
```

For `"hi"`:

```text
i = 0 → charAt(1) → prints i
i = 1 → charAt(2) → exception
```

To safely access the next character, use:

```java
for (int i = 0; i < s.length() - 1; i++) {
    System.out.println(s.charAt(i + 1));
}
```

## Traversal summary table

| Task | Loop condition | Starting index |
|---|---|---:|
| Visit every character forward | `i < s.length()` | `0` |
| Visit every character backward | `i >= 0` | `s.length() - 1` |
| Stop before the last character | `i < s.length() - 1` | `0` |
| Access next character `i + 1` safely | `i < s.length() - 1` | `0` |
| Visit every second character | `i < s.length(); i += 2` | `0` |

---

# 8. IMPORTANT STRING METHODS

Assume:

```java
String s = "Java Programming";
```

| Method | Purpose | Example |
|---|---|---|
| `length()` | Number of UTF-16 code units | `s.length()` |
| `charAt(i)` | Character at index | `s.charAt(0)` |
| `substring(a,b)` | Extract from `a` to `b-1` | `s.substring(0,4)` |
| `substring(a)` | Extract from `a` to end | `s.substring(5)` |
| `equals(t)` | Compare contents | `s.equals(t)` |
| `equalsIgnoreCase(t)` | Compare ignoring case | `s.equalsIgnoreCase(t)` |
| `contains(t)` | Check for a sequence | `s.contains("Java")` |
| `indexOf(t)` | First matching index | `s.indexOf("a")` |
| `lastIndexOf(t)` | Last matching index | `s.lastIndexOf("a")` |
| `startsWith(t)` | Check beginning | `s.startsWith("Java")` |
| `endsWith(t)` | Check ending | `s.endsWith("ing")` |
| `toCharArray()` | Convert to `char[]` | `s.toCharArray()` |
| `toUpperCase()` | Convert to uppercase | `s.toUpperCase()` |
| `toLowerCase()` | Convert to lowercase | `s.toLowerCase()` |
| `trim()` | Remove leading/trailing characters with code point ≤ U+0020 | `s.trim()` |
| `strip()` | Remove leading/trailing Unicode whitespace as defined by Java | `s.strip()` |
| `replace(a,b)` | Replace literal characters/sequences | `s.replace('a','o')` |
| `replaceAll(regex,r)` | Replace regex matches | `s.replaceAll("[0-9]", "")` |
| `split(regex)` | Split using a regular expression | `s.split(" ")` |
| `concat(t)` | Concatenate Strings | `s.concat("!")` |
| `isEmpty()` | Check whether length is zero | `"".isEmpty()` |
| `isBlank()` | Check whether empty or whitespace-only | `"   ".isBlank()` |
| `compareTo(t)` | Lexicographic comparison | `"abc".compareTo("abd")` |
| `repeat(n)` | Repeat the String | `"ha".repeat(3)` |

Note: `strip()`, `isBlank()`, and `repeat()` were introduced in Java 11. Your Java 21 setup supports them.

## Methods that return an index

```java
String s = "banana";

System.out.println(s.indexOf('a'));     // 1
System.out.println(s.lastIndexOf('a')); // 5
System.out.println(s.indexOf('z'));     // -1
```

If a searched character or substring is not found, `indexOf()` returns `-1`.

## `substring(start, end)`

**Start is inclusive; end is exclusive.**

```java
String s = "COMPUTER";

System.out.println(s.substring(0, 3)); // COM
System.out.println(s.substring(3, 6)); // PUT
System.out.println(s.substring(3));    // PUTER
```

Diagram:

```text
String:   C   O   M   P   U   T   E   R
Index:    0   1   2   3   4   5   6   7

substring(2, 5)

          [       M   P   U       )
          start               end

Result: MPU
```

For `substring(start, end)`, valid bounds require `0 <= start <= end <= s.length()`. Invalid bounds cause `StringIndexOutOfBoundsException`.

## `replace()` vs `replaceAll()`

```java
String s = "a1b2";

System.out.println(s.replace("1", "X"));    // aXb2
System.out.println(s.replaceAll("[0-9]", "")); // ab
```

- `replace()` performs literal replacement.
- `replaceAll()` treats its first argument as a regular expression.

## `split()`

```java
String s = "Java,Python,SQL";
String[] parts = s.split(",");

for (String part : parts) {
    System.out.println(part);
}
```

Output:

```text
Java
Python
SQL
```

Remember: `split()` takes a regex. For example, to split on a literal dot, use `s.split("\\.")`.

---

# 9. STRING CONCATENATION

Concatenation means joining Strings.

```java
String a = "Core";
String b = "Java";

String result = a + " " + b;

System.out.println(result); // Core Java
```

Other forms:

```java
String result1 = a.concat(b);
String result2 = a + b;
String result3 = a + 10; // Core10
```

The `+` operator can join a String with other values by converting the other operand to text.

## Common trap

```java
String s = "A";
s + "B";

System.out.println(s); // A
```

The concatenated result is ignored. Assign it to a variable to retain it.

## Performance note

Repeated concatenation inside a loop can create many intermediate String objects.

```java
String result = "";

for (int i = 0; i < 1000; i++) {
    result = result + i;
}
```

For repeated construction, prefer `StringBuilder`.

---

# 10. STRINGBUILDER

`StringBuilder` is a mutable sequence of characters. It is useful when you need to modify or build text repeatedly.

```java
StringBuilder sb = new StringBuilder("Java");

sb.append(" DSA");
System.out.println(sb); // Java DSA

sb.insert(4, " Core");
System.out.println(sb); // Java Core DSA

sb.reverse();
System.out.println(sb); // ASD eroC avaJ
```

## String vs StringBuilder

| Feature | String | StringBuilder |
|---|---|---|
| Mutability | Immutable | Mutable |
| Change content | Returns new String | Modifies builder |
| Common use | Fixed text | Repeated text changes |
| Append | `s = s + "x"` | `sb.append("x")` |
| Character update | Not directly | `sb.setCharAt(i, ch)` |
| Reverse | No direct method | `sb.reverse()` |

## Useful methods

| Method | Example |
|---|---|
| `append()` | `sb.append("abc")` |
| `insert()` | `sb.insert(0, "Hi ")` |
| `delete()` | `sb.delete(1, 3)` |
| `deleteCharAt()` | `sb.deleteCharAt(0)` |
| `setCharAt()` | `sb.setCharAt(0, 'J')` |
| `reverse()` | `sb.reverse()` |
| `length()` | `sb.length()` |
| `toString()` | `sb.toString()` |

## Reverse a String

```java
String s = "hello";

String reversed = new StringBuilder(s).reverse().toString();

System.out.println(reversed); // olleh
```

`StringBuilder` is not synchronized. `StringBuffer` provides synchronized methods and is useful in certain multithreaded scenarios, although synchronization alone does not make every compound operation automatically safe.

---

# 11. CONVERSIONS

## String to character array

```java
String s = "Java";
char[] arr = s.toCharArray();

System.out.println(arr[0]); // J
```

Visual:

```text
String "Java"
     |
  toCharArray()
     |
     v
['J', 'a', 'v', 'a']
```

The resulting array is mutable:

```java
arr[0] = 'L';

System.out.println(arr); // Lava
System.out.println(s);   // Java
```

Changing the array does not change the original String.

## Character array to String

```java
char[] arr = {'C', 'o', 'd', 'e'};
String s = new String(arr);

System.out.println(s); // Code
```

## Number to String

```java
int n = 123;

String s1 = String.valueOf(n);
String s2 = Integer.toString(n);
```

## String to number

```java
String s = "123";

int n = Integer.parseInt(s);
double d = Double.parseDouble("12.5");
```

Invalid numeric text can cause `NumberFormatException`.

---

# 12. STRINGS AND LOOPS — DRY-RUN METHOD

Whenever you see a String loop in DSA, follow this process:

```text
          Read the String
                 |
          Find s.length()
                 |
        Write valid indices
                 |
       Check initial value of i
                 |
        Evaluate condition
                 |
       Evaluate charAt(index)
                 |
       Record printed character
                 |
       Update i
                 |
        Check condition again
```

Example:

```java
String s = "desk";

for (int i = 1; i < s.length(); i += 2) {
    System.out.print(s.charAt(i));
}
```

Dry run:

| `i` | Condition | Character | Output so far |
|---:|---|---|---|
| 1 | True | `e` | e |
| 3 | True | `k` | ek |
| 5 | False | Not accessed | ek |

Final output: `ek`

**Interview tip:** Never guess a loop's output. Write the index, condition, character, and output after every iteration.

---

# 13. IMPORTANT STRING CODING PATTERNS FOR DSA

## Pattern 1: Count characters

```java
String s = "banana";
int count = 0;

for (int i = 0; i < s.length(); i++) {
    if (s.charAt(i) == 'a') {
        count++;
    }
}

System.out.println(count); // 3
```

Flow:

```text
Start count = 0
      |
Visit each character
      |
Is character 'a'?
   /       \
 Yes        No
  |          |
count++    No change
   \        /
   Next index
      |
    Finish
```

## Pattern 2: Reverse a String manually

```java
String s = "code";
StringBuilder result = new StringBuilder();

for (int i = s.length() - 1; i >= 0; i--) {
    result.append(s.charAt(i));
}

System.out.println(result); // edoc
```

## Pattern 3: Palindrome check

A palindrome reads the same forward and backward.

Examples: `"madam"`, `"level"`, `"racecar"`.

```text
"madam"

Left pointer → m a d a m ← Right pointer
               |       |
               Compare
               |       |
               m == m
             a == a
               d == d

All matching → Palindrome
```

```java
boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

This checks exact characters, including case and spaces. Normalize the input first if the problem asks to ignore case or punctuation.

Time complexity: `O(n)`  
Auxiliary space: `O(1)`

## Pattern 4: Two pointers

Used for palindrome checks, reversing arrays of characters, and comparing positions from opposite ends.

```text
String:  a b c b a
         ↑       ↑
        left    right

Compare → move both pointers inward
```

Use two pointers when the problem asks about matching, reversing, or comparing characters from both ends.

## Pattern 5: Frequency counting

For strings containing only lowercase English letters, a 26-element array can store frequencies.

```java
String s = "banana";
int[] freq = new int[26];

for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
    freq[ch - 'a']++;
}
```

Why `ch - 'a'`?

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
...
'z' - 'a' = 25
```

This approach assumes every character is between `'a'` and `'z'`. For uppercase letters, Unicode text, or a wider character set, use an appropriate mapping or a frequency map.

## Pattern 6: Anagram check

Two Strings are anagrams if they contain the same characters with the same frequencies, regardless of order.

Example: `"listen"` and `"silent"`.

```text
listen  → e:1, i:1, l:1, n:1, s:1, t:1
silent  → e:1, i:1, l:1, n:1, s:1, t:1

Same frequency counts → Anagrams
```

Possible methods:

1. Sort both Strings and compare.
2. Count character frequencies.

Sorting method: typically `O(n log n)` time.  
Frequency counting: typically `O(n)` time for a fixed-size alphabet.

## Pattern 7: Sliding window

Useful for substring problems involving longest, shortest, or fixed-length windows.

Example: Find the longest substring without repeating characters.

```text
String: a b c a b b

Window: [a b c]
         ↑     ↑
        left  right

Next character repeats?
Move left until the window is valid.
Continue expanding right.
Track the best length.
```

Typical flow:

```text
Initialize window and answer
            |
      Expand right
            |
      Window valid?
       /         \
     Yes          No
      |            |
Update answer   Shrink left
      |            |
      └──────┬─────┘
             |
        Continue
```

Sliding-window problems may require a set, frequency array, or map to track the current window.

## Pattern 8: Substrings

A substring is a contiguous sequence of characters.

For `"abcd"`:

```text
Valid substrings include:
"a", "b", "c", "d"
"ab", "bc", "cd"
"abc", "bcd"
"abcd"
```

`"ac"` is not a substring because the characters are not contiguous.

Substring problems often involve nested loops, two pointers, or sliding windows.

---

# 14. TIME AND SPACE COMPLEXITY

Let `n` be the String length.

| Operation or approach | Typical complexity |
|---|---|
| `length()` | `O(1)` |
| `charAt(i)` | `O(1)` |
| Traverse all characters | `O(n)` |
| Build reverse with `StringBuilder` | `O(n)` |
| Two-pointer palindrome check | `O(n)` time, `O(1)` auxiliary space |
| Frequency count with fixed 26-letter array | `O(n)` time, `O(1)` auxiliary space |
| Sort characters | `O(n log n)` time |
| Compare two equal-length Strings | Up to `O(n)` |
| Create a substring | Do not assume constant cost; modern Java generally copies the selected characters |
| Repeated immutable concatenation in a loop | Can take `O(n²)` total time in common patterns |
| StringBuilder append loop | Typically `O(n)` total for appending a total of `n` characters |

Actual complexity can depend on the operation, input sizes, character representation, and JVM implementation.

---

# 15. COMMON ERRORS AND HOW TO FIX THEM

| Mistake | Why it is wrong | Correct approach |
|---|---|---|
| `s.length` | String length is a method | `s.length()` |
| `arr.length()` | Array length is a field | `arr.length` |
| `i <= s.length()` | Allows an invalid final index | `i < s.length()` |
| `s.charAt(s.length())` | Last valid index is `length - 1` | `s.charAt(s.length() - 1)` when nonempty |
| `s1 == s2` for content comparison | Checks reference identity | `s1.equals(s2)` |
| Ignoring `replace()` result | Original String remains unchanged | `s = s.replace(...)` |
| `substring(0, s.length() - 1)` to get the whole String | Excludes the final character | `substring(0, s.length())` or just `s` |
| `i >= 0` with `i++` in a reverse loop | The loop may never terminate | Use `i--` |
| Calling `charAt(0)` on an empty String | No valid index exists | Check `!s.isEmpty()` first |
| Using `split(".")` for a literal dot | `.` means any character in regex | `split("\\.")` |

---

# 16. INTERVIEW QUESTIONS TO REVISE

## Beginner

1. What is a String in Java?
2. Why are Strings immutable?
3. What is the difference between a String and a character array?
4. What is the difference between `==` and `equals()`?
5. How do you find the length of a String?
6. How do you access the last character safely?
7. What does `substring(start, end)` return?
8. What happens if `charAt()` receives an invalid index?

## Intermediate

9. Explain the String Pool.
10. What does `intern()` do?
11. What is the difference between `String`, `StringBuilder`, and `StringBuffer`?
12. Why can repeated String concatenation in a loop be inefficient?
13. What is the difference between `replace()` and `replaceAll()`?
14. How would you reverse a String?
15. How would you check whether a String is a palindrome?
16. How would you check whether two Strings are anagrams?
17. How would you count the frequency of each character?
18. When would you use a sliding window for a String problem?

## Output-based questions

Always practise predicting the output of:

```java
String s = "dog";
s.toUpperCase();
System.out.println(s);
```

```java
String s = "dog";
s = s.toUpperCase();
System.out.println(s);
```

```java
String s = "code";
for (int i = 0; i < s.length() - 1; i++) {
    System.out.print(s.charAt(i));
}
```

```java
String s = "hi";
for (int i = 0; i < s.length(); i++) {
    System.out.print(s.charAt(i + 1));
}
```

Try to solve these without running the code. Pay particular attention to return values and loop boundaries.

---

# 17. QUICK REVISION CHEAT SHEET

```text
STRING LENGTH
s.length()

CHARACTER ACCESS
s.charAt(i)

FIRST CHARACTER
s.charAt(0)                 // only if nonempty

LAST CHARACTER
s.charAt(s.length() - 1)    // only if nonempty

FORWARD LOOP
for (int i = 0; i < s.length(); i++)

REVERSE LOOP
for (int i = s.length() - 1; i >= 0; i--)

CONTENT COMPARISON
s1.equals(s2)

CASE-INSENSITIVE COMPARISON
s1.equalsIgnoreCase(s2)

SUBSTRING
s.substring(start, end)    // start included, end excluded

CONVERT TO CHARACTER ARRAY
s.toCharArray()

MUTABLE TEXT BUILDING
StringBuilder sb = new StringBuilder();
sb.append(...);

REVERSE USING BUILDER
new StringBuilder(s).reverse().toString()

IMMUTABILITY RULE
s.replace(...)             // return value ignored
s = s.replace(...)         // result assigned
```

---

# 18. YOUR STRING LEARNING ROADMAP

Follow this progression before attempting advanced String problems.

```text
STRING FUNDAMENTALS
        |
        v
Indexing + length + charAt
        |
        v
Forward and reverse loops
        |
        v
String methods + substring
        |
        v
Immutability + String Pool
        |
        v
StringBuilder + conversions
        |
        v
Basic coding problems
        |
        v
Two pointers + palindrome
        |
        v
Frequency counting + anagrams
        |
        v
Sliding window + substrings
        |
        v
Advanced String algorithms
```

## Revision checklist

- [ ] I can explain String immutability with an example.
- [ ] I know `==` versus `equals()`.
- [ ] I can trace forward and reverse loops.
- [ ] I can identify inclusive and exclusive boundaries.
- [ ] I can predict when `charAt()` throws an exception.
- [ ] I understand `substring(start, end)`.
- [ ] I know the String Pool and the purpose of `intern()`.
- [ ] I can use common String methods correctly.
- [ ] I can use `StringBuilder` to construct or reverse text.
- [ ] I can solve a basic palindrome problem.
- [ ] I understand frequency counting and anagram checks.
- [ ] I can identify when a sliding window is appropriate.

**Final rule for placements:** Before coding any String problem, identify the character constraints, valid index range, whether the input can be empty, whether case matters, and whether the problem asks for a character, a substring, or a modified String. Those decisions prevent many beginner errors.

