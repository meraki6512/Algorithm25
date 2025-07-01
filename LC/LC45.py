# Top Interview 150
# 45. Jump Game II

class Solution:
    def jump(self, nums: List[int]) -> int:
        
        # prev = len(num) - 1 (from the back)
        # loop (while prev > 0):
            # loop (for idx=prev, 0, -1) (find the max step to reach last one)
                # prev - idx >= nums[idx] ? prev = idx 
            # cnt += 1
        
        cnt = 0
        prev = len(nums)-1

        while prev > 0:

            tmp = prev
            for i in range(tmp-1, -1, -1):
                if tmp-i <= nums[i]:
                    prev = i
            
            cnt += 1
        
        return cnt
