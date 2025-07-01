# Top Interview 150
# 26. Remove Duplicates from Sorted Array

class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        
        # method1 - dict
        # sets = list(set(nums))
        # d = dict.fromkeys(sets, 0)
        # p = k = 0
        # while nums.length:
            # if d.get(p) == 0: nums[k] = p; k++; d[p] += 1
            # p++
        # return k


        # method2 - set
        # sets = set()
        # p = k = 0
        # while nums.length:
            # if nums[p] not in sets: sets.add(nums[p]); update nums[k]; k++
            # p++
        # return k

        sets = set()
        k = 0

        for i in nums:

            if i not in sets:
                sets.add(i)
                nums[k] = i

                k += 1

        return k

