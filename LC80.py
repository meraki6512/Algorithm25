# Top Interview 150
# 80. Remove Duplicates from Sorted Array II

class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        
        # sets = set(nums)
        # d = dict.fromkeys(sets, 0)

        # k = 0
        # for i in nums:
            # if dict.get(i) < 2: k++; dict[i]+=1; nums[k]=i
        
        # return k

        sets = set(nums)
        d = dict.fromkeys(sets, 0)

        k = 0
        for i in nums:
            if d.get(i) < 2:
                d[i] += 1
                nums[k] = i
                k += 1
        
        return k

    # other method? 
    # counter
    # check i-1, i-2 elements
