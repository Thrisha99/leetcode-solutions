```java
/*
 * Problem: 152. Maximum Product Subarray
 * Difficulty: Medium
 *
 * Approach: Prefix-Suffix Product Scan
 * 1. Traverse the array from left to right using p.
 * 2. Traverse the array from right to left using s.
 * 3. Reset a product to 1 when it is 0, before processing
 *    the next element.
 * 4. Update max using both p and s at every iteration.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

//Using basic observations:

class Solution {
  public int maxProduct(int[] nums) {
    int p=1;
    int s=1;
    int max=Integer.MIN_VALUE;
    for(int i=0;i<nums.length;i++){
        if(p==0) p=1;
        if(s==0)  s=1;
        p*=nums[i];
        s*=nums[nums.length-i-1];
        max=Math.max(max,Math.max(p,s));
    }
    return max;
  }
}


//Using Kadane's algorithm


// class Solution {
//     public int maxProduct(int[] nums) {
//         int max = nums[0];
//         int min = nums[0];
//         int maxP = nums[0];

//         for (int i = 1; i < nums.length; i++) {
//             int num = nums[i];

//             if (num < 0) {
//                 int temp = max;
//                 max = min;
//                 min = temp;
//             }

//             max = Math.max(num, max * num);
//             min = Math.min(num, min * num);

//             maxP = Math.max(maxP, max);
//         }

//         return maxP;
//     }
// }

// class Solution {
//     public int maxProduct(int[] nums) {
//         int max = nums[0];
//         int min = nums[0];
//         int maxP = nums[0];

//         for (int i = 1; i < nums.length; i++) {
//             int num = nums[i];

//             int tempMax = Math.max(num,
//                     Math.max(max * num, min * num));

//             int tempMin = Math.min(num,
//                     Math.min(max * num, min * num));

//             max = tempMax;
//             min = tempMin;

//             maxP = Math.max(maxP, max);
//         }

//         return maxP;
//     }
// }

//Brute force:

//  int max=nums[0];
//         for(int i=0;i<nums.length;i++){//0
//         int product=nums[i];//-2
//         max=Math.max(max,product);
//             for(int j=i+1;j<nums.length;j++){
                
//                 product*=nums[j];
//                 max=Math.max(max,product);
                
//             }
//         }
//         return max;