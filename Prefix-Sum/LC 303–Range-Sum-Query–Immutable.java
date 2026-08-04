/* 
## Problem Description

Given an integer array `nums`, design a data structure that can efficiently answer multiple range sum queries.

For each query, return the sum of the elements between indices `left` and `right` (inclusive).

Implement the following:

- `NumArray(int[] nums)`  
  Initializes the object with the integer array.

- `sumRange(int left, int right)`  
  Returns the sum of the elements from index `left` to index `right` (inclusive).

---

### Example

Input

nums = [-2, 0, 3, -5, 2, -1]

Queries

sumRange(0,2)

sumRange(2,5)

sumRange(0,5)

Output

1

-1

-3

Explanation

sumRange(0,2)

= -2 + 0 + 3

= 1

-------------------

sumRange(2,5)

= 3 + (-5) + 2 + (-1)

= -1

-------------------

sumRange(0,5)

= -2 + 0 + 3 + (-5) + 2 + (-1)

= -3

---

### Constraints

- 1 ≤ nums.length ≤ 10⁴
- -10⁵ ≤ nums[i] ≤ 10⁵
- 0 ≤ left ≤ right < nums.length
- There can be many calls to `sumRange()`, so an efficient solution is expected.

*/


class NumArray {
    int[] p;
    public NumArray(int[] nums) {
        p=new int[nums.length];
        p[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            p[i]=p[i-1]+nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left==0){
            return p[right];
        }
        return p[right]-p[left-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */