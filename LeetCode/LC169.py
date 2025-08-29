# Top Interview 150
# 169. Majority Element

class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        
        # sets = set()
        # d = dict()
        # for i in nums:
            # if i in sets:
                # d[i] += 1
            # else:
                # sets.add(i)
                # d[i] = 1
        # ans = 0
        # for i in enumerate(d):
            # if d.value > (len(nums)/2):
                # ans = d.key
                # break
        
        
        sets = set()
        d = dict()

        for i in nums:
            if i in sets:
                d[i] += 1
            else:
                sets.add(i)
                d[i] = 1

        for key in dict.fromkeys(d):
            if d[key] > (len(nums)/2):
                break
        
        return key
        
        
