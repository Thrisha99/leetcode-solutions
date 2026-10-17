
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
