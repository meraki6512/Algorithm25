class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        
        # sorted array!

        f = 0 
        r = len(nums) - 1
        mid = (f+r)//2

        while f < r:

            mid = (f+r)//2
            mn = nums[mid]

            if mn == target:
                return mid
            
            elif mn > target:
                r = mid - 1

            elif mn < target:
                f = mid + 1

        # always ended up f==r ?
        
        ans = r + 1 if target > nums[r] else r
        
        return ans if ans > 0 else 0