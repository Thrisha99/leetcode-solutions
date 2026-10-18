/*
 * Problem: Insert Interval
 * LeetCode Number: 57
 * LeetCode Link: https://leetcode.com/problems/insert-interval/
 *
 * Topic: Intervals
 * Pattern: Greedy / Interval Merging
 * Difficulty: Medium
 *
 * Problem Details:
 * Given a sorted array of non-overlapping intervals and a new interval,
 * insert the new interval into the existing intervals and merge all
 * overlapping intervals. Return the resulting array of non-overlapping
 * intervals sorted by their start times.
 *
 * Approach:
 * 1. Add all intervals that end before the new interval starts.
 * 2. Merge all overlapping intervals by updating the minimum start
 *    and maximum end of the new interval.
 * 3. Add the final merged interval to the result.
 * 4. Add all remaining intervals.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n), including the result.
 *
 * Basic Concepts:
 * - 2D arrays: Each interval is represented as [start, end].
 * - ArrayList<int[]>: Stores the resulting intervals dynamically.
 * - Math.min(): Finds the smaller start value.
 * - Math.max(): Finds the larger end value.
 * - toArray(): Converts the list into an int[][] array.
 *
 * Important:
 * - Existing intervals are already sorted and non-overlapping.
 * - Intervals sharing an endpoint are considered overlapping.
 * - current references newInterval; merging modifies newInterval.
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n=intervals.length;
        List<int[]> res=new ArrayList<>();
        int i=0;
        int curr[]=newInterval;
        while(i<n && intervals[i][1]<curr[0]){
            res.add(intervals[i]);
            i++;
        }
        while(i<n && intervals[i][0]<=curr[1]){
            curr[0]=Math.min(curr[0],intervals[i][0]);
            curr[1]=Math.max(curr[1],intervals[i][1]);
            i++;
        }
        res.add(curr);
        while(i<n){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
}










//*Brute Force Approach */




// import java.util.*;

// class Solution {
//     public int[][] insert(int[][] intervals, int[] newInterval) {

//         // Step 1: Add the new interval
//         int n = intervals.length;
//         int[][] all = new int[n + 1][2];

//         for (int i = 0; i < n; i++) {
//             all[i] = intervals[i];
//         }

//         all[n] = newInterval;

//         // Step 2: Sort by starting value
//         Arrays.sort(all, (a, b) ->
//             Integer.compare(a[0], b[0])
//         );

//         // Step 3: Merge overlapping intervals
//         List<int[]> result = new ArrayList<>();

//         int[] current = all[0];

//         for (int i = 1; i < all.length; i++) {
//             int[] next = all[i];

//             if (next[0] <= current[1]) {
//                 current[1] = Math.max(current[1], next[1]);
//             } else {
//                 result.add(current);
//                 current = next;
//             }
//         }

//         // Step 4: Add the last interval
//         result.add(current);

//         return result.toArray(new int[result.size()][]);
//     }
// }
