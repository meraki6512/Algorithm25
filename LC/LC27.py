# Top Interview 150
# 27. Remove Element

class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        
        # p = k = 0
        # L = nums.length
        # while p < L:
            # if p != val: nums[k] = val; k++
            # p++
        
        #return k

        p = k = 0
        L = len(nums)

        for p in range(L):
            if nums[p] != val: 
                nums[k] = nums[p]
                k += 1
        
        return k
