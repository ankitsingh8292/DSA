class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        
        // Count frequencies of each absolute difference
        int[] count = new int[100001];
        long totalOps = (long) k1 + k2;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Greedily reduce the largest differences
        for (int i = maxDiff; i > 0 && totalOps > 0; i--) {
            if (count[i] == 0) continue;
            
            long reduceCount = Math.min(totalOps, count[i]);
            count[i] -= reduceCount;
            count[i - 1] += (int) reduceCount;
            totalOps -= reduceCount;
        }
        
        // Calculate the final minimum sum of squared differences
        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += (long) count[i] * i * i;
            }
        }
        
        return result;
    }
}