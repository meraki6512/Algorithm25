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
        
        # i-1와 i를 비교하지 말고
        # i와 i+1을 비교하면 
        # for loop가 끝나고 한 번 더 비교할 필요가 없을 것 같음
        # 추후 다시 풀어보기
