
# LeetCode 56 — Merge Intervals

- Difficulty: Medium
- Pattern: Arrays, Sorting, Intervals
- Status: Solved
- Approach: Sort + Merge

## Problem

Merge all overlapping intervals and return the resulting
non-overlapping intervals.

## Example

Input:
[[1,3],[2,6],[8,10],[15,18]]

Output:
[[1,6],[8,10],[15,18]]

## Approach

1. Sort intervals by starting value.
2. Initialize current with the first interval.
3. Compare each next interval with current.
4. If nextStart <= currentEnd:
   - The intervals overlap.
   - Update currentEnd using max(currentEnd, nextEnd).
5. Otherwise:
   - Add current to result.
   - Set current to the next interval.
6. Add current after the loop.
7. Convert the result list to int[][].

## Important Conditions

Overlap:
nextStart <= currentEnd

Merge:
currentEnd = max(currentEnd, nextEnd)

## Dry Run

Input: [[1,3],[2,6],[8,10]]

1. Start with current = [1,3].
2. [2,6] overlaps; current becomes [1,6].
3. [8,10] does not overlap; save [1,6].
4. Add final current [8,10].

Output: [[1,6],[8,10]]

## Complexity

Time: O(n log n)
Space: O(n) for the result list.

## Revision Reminders

- Sort by start, not end.
- Do not shrink current when an interval is contained.
- Save current when there is no overlap.
- Add the final current after the loop.



# LeetCode 435 — Non-overlapping Intervals

- Difficulty: Medium
- Pattern: Greedy, Sorting, Intervals
- Status: Solved
- Approach: Greedy by End Time

## Problem

Find the minimum number of intervals to remove so that
the remaining intervals do not overlap.

## Example

Input:
[[1,3],[2,4],[3,5]]

Output:
1

Remove [2,4] and keep [1,3] and [3,5].

## Approach

1. Sort intervals by ending value.
2. Keep the first interval.
3. Track the end of the last kept interval.
4. For each remaining interval:
   - If nextStart < end, remove the current interval.
   - Otherwise, keep it and update end.
5. Return the removal count.

## Important Condition

Overlap:
nextStart < end

No overlap:
nextStart >= end

Intervals that touch at an endpoint can both be kept.

## Dry Run

Input:
[[1,3],[2,4],[3,5],[1,2]]

After sorting by end:
[[1,2],[1,3],[2,4],[3,5]]

1. Keep [1,2]; end = 2; count = 0.
2. [1,3] overlaps; remove it; count = 1.
3. [2,4] starts at end = 2; keep it; end = 4.
4. [3,5] overlaps; remove it; count = 2.

Output: 2

## Why Sort by End Time?

Keeping the interval that ends earliest leaves more
room for future intervals.

## Complexity

Time: O(n log n)
Auxiliary space: typically O(log n) for sorting.

## Revision Reminders

- Sort by end, not start.
- Remove overlapping intervals; do not merge them.
- Do not update end when removing an interval.
- Use < rather than <= because touching endpoints
  are allowed.



# LeetCode 57 — Insert Interval

## 1. Problem Summary

Given a sorted array of non-overlapping intervals and a new interval, insert the new interval and merge any overlapping intervals.

**Goal:** Return the updated intervals in sorted order without overlaps.

## 2. Key Idea

Use three phases:

1. Add all existing intervals that end before the new interval starts.
2. Merge all existing intervals that overlap with the new interval.
3. Add the merged interval, then append all remaining intervals.

**Important:** Existing intervals are already sorted and non-overlapping. No sorting is required.


## 4. Understand the Variables

| Variable | Meaning |
|---|---|
| `intervals` | Original sorted intervals |
| `newInterval` | Interval to insert |
| `current` | Interval being expanded during merging |
| `i` | Index of the next unprocessed existing interval |
| `n` | Number of existing intervals |
| `result` | Final list of intervals |

**Java reference detail:** `current = newInterval` does not create a copy. Both variables refer to the same array. Updating `current[0]` or `current[1]` also updates `newInterval`.

## 5. The Three Phases

### Phase 1 — Intervals before the new interval

Condition:

`intervals[i][1] < current[0]`

If the existing interval ends before the current interval starts, add it directly to `result` and increment `i`.

Example: `[1,2]` comes before `[4,8]`, because `2 < 4`.

### Phase 2 — Merge overlapping intervals

Condition:

`intervals[i][0] <= current[1]`

For every overlap:

- Update the start using the smaller start.
- Update the end using the larger end.
- Increment `i`.

Formulas:

- `current[0] = min(current start, existing start)`
- `current[1] = max(current end, existing end)`

Example:

`current = [4,8]`, existing interval = `[3,5]`

Merged interval = `[3,8]`.

If the next interval is `[8,10]`, merge again to get `[3,10]`. Sharing an endpoint counts as overlap.

### Phase 3 — Add remaining intervals

Once an existing interval starts after the current interval ends, Phase 2 stops.

Add `current` to `result`, then append every remaining existing interval directly. Since the input is sorted and non-overlapping, these intervals need no further merging.

## 6. Dry Run 1 — One Overlap

**Input:**

`intervals = [[1,3], [6,9]]`

`newInterval = [2,5]`

Initial values: `current = [2,5]`, `i = 0`, `n = 2`.

| Step | `i` | Action | `current` | `result` |
|---|---:|---|---|---|
| Phase 1 | 0 | `3 < 2` is false | `[2,5]` | `[]` |
| Phase 2 | 0 | Merge `[1,3]` | `[1,5]` | `[]` |
| Phase 2 | 1 | `6 <= 5` is false | `[1,5]` | `[]` |
| Add current | 1 | Add `[1,5]` | `[1,5]` | `[[1,5]]` |
| Phase 3 | 1 | Add `[6,9]` | `[1,5]` | `[[1,5],[6,9]]` |

**Output:** `[[1,5], [6,9]]`

## 7. Dry Run 2 — Multiple Overlaps

**Input:**

`intervals = [[1,2], [3,5], [6,7], [8,10], [12,16]]`

`newInterval = [4,8]`

Initial values: `current = [4,8]`, `i = 0`, `n = 5`.

| Step | `i` | Action | `current` | `result` |
|---|---:|---|---|---|
| Phase 1 | 0 | Add `[1,2]`; `2 < 4` | `[4,8]` | `[[1,2]]` |
| Phase 1 | 1 | `5 < 4` is false | `[4,8]` | `[[1,2]]` |
| Phase 2 | 1 | Merge `[3,5]` | `[3,8]` | `[[1,2]]` |
| Phase 2 | 2 | Merge `[6,7]`; endpoints unchanged | `[3,8]` | `[[1,2]]` |
| Phase 2 | 3 | Merge `[8,10]` | `[3,10]` | `[[1,2]]` |
| Phase 2 | 4 | `12 <= 10` is false | `[3,10]` | `[[1,2]]` |
| Add current | 4 | Add `[3,10]` | `[3,10]` | `[[1,2],[3,10]]` |
| Phase 3 | 4 | Add `[12,16]` | `[3,10]` | `[[1,2],[3,10],[12,16]]` |

**Output:** `[[1,2], [3,10], [12,16]]`

## 8. Common Mistakes

1. **Using `<=` in Phase 1:** Incorrect for this approach. Use `<` so an interval sharing an endpoint with the current interval is not added separately.
2. **Using `<` in Phase 2:** This misses intervals that overlap at a shared endpoint. Use `<=`.
3. **Forgetting to increment `i`:** Can cause an infinite loop.
4. **Adding `current` too early:** A later interval may still overlap and expand it.
5. **Re-sorting the intervals:** Unnecessary because the input is already sorted.
6. **Assuming `current` is a copy:** It references `newInterval`; mutations affect the same array.
7. **Forgetting Phase 3:** Remaining intervals must be appended after adding the merged interval.

## 9. Complexity Analysis

Let `n` be the number of existing intervals.

- **Time complexity:** `O(n)` — each existing interval is processed at most once.
- **Auxiliary/output space:** `O(n)` for the result list. The method does not sort or create copies of the individual intervals.

## 10. Quick Revision

Remember:

- **Before:** Existing end `<` current start → add existing interval.
- **Overlap:** Existing start `<=` current end → expand `current`.
- **Finish:** Add `current`, then append the remaining intervals.
- **Index:** `i` always points to the next unprocessed existing interval.
- **No sorting:** The input is already sorted.

## 11. Related Problem

**LeetCode 56 — Merge Intervals**

Both problems use overlap merging. LC 57 differs because the intervals are already sorted and non-overlapping, and only one new interval needs to be inserted.

## 12. Revision Checklist

- [ ] Explain the three phases without looking at notes.
- [ ] Dry-run an example with no overlap.
- [ ] Dry-run an example with multiple overlaps.
- [ ] Explain why Phase 1 uses `<` and Phase 2 uses `<=`.
- [ ] Re-code the solution from memory.
- [ ] State the time and space complexity.
