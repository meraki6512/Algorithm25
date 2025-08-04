class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        
        d = dict()

        for i, n in enumerate(nums):
            
            # check if n in nums
            if n in d.keys():
                return [i, d[n]] 

            # add ans in nums
            else:
                d[target - n] = i
            
        