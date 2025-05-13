class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        
        start = 0
        ans = []

        for i, n in enumerate(nums):

            ns = nums[start]

            if nums[i-1] + 1 < nums[i]:
                result = str(ns) if ns == nums[i-1] else str(ns)+"->"+str(nums[i-1])
                ans.append(result)
                start = i
    
        if start < len(nums):
            ns = nums[start]
            result = str(ns) if start == len(nums)-1 else str(ns)+"->"+str(nums[-1])
            ans.append(result)

        return ans
        
