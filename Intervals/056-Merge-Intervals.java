
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/*
 * Problem: 56. Merge Intervals
 * Difficulty: Medium
 * Pattern: Arrays, Sorting, Intervals
 *
 * Approach:
 * 1. Sort intervals by starting value.
 * 2. Track the current merged interval.
 * 3. If the next interval overlaps, extend the current end.
 * 4. Otherwise, save current and start a new interval.
 * 5. Add the final current interval.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n) for the result list.
 */

import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();//[]
        int[] current = intervals[0];//1,3  1,6
        for (int i = 1; i < intervals.length; i++) {//2,6  8,10
            if (intervals[i][0] <= current[1]) {//2<=3 right , 8<=6 false
                current[1] = Math.max(current[1], intervals[i][1]);
                //max=max(3,6)=6
            } else {
                result.add(current);//1,6
                current = intervals[i];//8,10
            }
        }
        result.add(current);//15,18
        return result.toArray(new int[result.size()][]);
    }
}