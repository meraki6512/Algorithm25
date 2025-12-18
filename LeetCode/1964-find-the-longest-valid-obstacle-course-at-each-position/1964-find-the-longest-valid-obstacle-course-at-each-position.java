class Solution {

    public int[] longestObstacleCourseAtEachPosition(int[] obs) {

        // LIS. greedy + bs
        // if cur >= lis.last:
        //      lis.add(cur);
        //      ans[i] = len(lis);
        // else:
        //      idx = idx of UpperBound (where the smallest num > cur)
        //      lis[idx] = cur;
        //      ans[i] = idx + 1;

        int n = obs.length;
        int ans[] = new int[n];
        ArrayList<Integer> lis = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int cur = obs[i];

            // update available
            if (lis.isEmpty() || lis.getLast() <= cur) {
                lis.add(cur);
                ans[i] = lis.size();
            }

            // else: find idx of the smallest num > cur from LIS
            else {
                int idx = UpperBound(lis, cur);
                lis.set(idx, cur);
                ans[i] = idx + 1;
            }
        }

        return ans;
    }

    private static int UpperBound(ArrayList<Integer> arr, int x) {
        int left = 0;
        int right = arr.size();

        while (left < right) {
            int mid = left + (right - left)/2;
            if (arr.get(mid) <= x) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}