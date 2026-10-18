## Arrays

### Array Rotation

| # | Problem | Pattern | Technique | Difficulty |
|---|---|---|---|---|
| 189 | Rotate Array | Array Rotation | Reversal Algorithm | Medium |

### Key Idea

For right rotation by `k`:

1. Reverse the last `k` elements.
2. Reverse the first `n-k` elements.
3. Reverse the entire array.

Time Complexity: `O(n)`

Space Complexity: `O(1)`