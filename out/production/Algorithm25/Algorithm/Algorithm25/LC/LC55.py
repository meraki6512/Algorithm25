# Top Interview 150
# 55. Jump Game

class Solution:
    def canJump(self, nums: List[int]) -> bool:

        # n = len(nums)
        # que = [0]

        # while len(que) > 0:

        #     c = que.pop()
            
        #     if c >= n-1:
        #         return True

        #     s = nums[c]

        #     for i in range(1, s+1):
        #         if c + i >= n-1:
        #             return True
        #         else: 
        #             que.append(c + i)

        # return False     

        # -> time out
        # anth way?.. '0' is the point.
        # loop nums
        # save each_idx + each_val
        # if it is 0: max(each_idx + each_val) <= 0_idx: False
        
        max_ = 0
      
        for i, s in enumerate(nums):
            
            if i+s >= len(nums)-1: 
                return True

            max_ = max(max_, i+s)

            if not s and max_ <= i:
                return False
            

