## 217. Contains Duplicate

### Pattern
Hashing → Frequency / Seen Before

### Key Idea
Check whether an element has already appeared.

### Brute Force
Use nested loops to compare every pair.

Time: O(n²)
Space: O(1)

### Optimal
Use HashSet.

For every element:
1. Check if it already exists in the set.
2. If yes → duplicate found → return true.
3. Otherwise → add it to the set.

Time: O(n) average
Space: O(n)

### Memory Trick
CHECK → SEEN? → TRUE
          ↓
         NO
          ↓
         ADD