# Top Interview 150
# 189. Rotate Array

class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        
        # Follow up:
        # - 3 diff ways
        # - in-place with O(1) extra space 

        # brainstorming
        
        # 1) use another array
        # 2) 1) + use minus index 
        # 3) 1) + use mod
        # 4) use string slicing method
        # 5) use rotate of deque

        # implements

        # 1) use another array
        k_arr = nums[-k:]
        n = len(nums)-k
        nums[k:] = nums[0:n]
        nums[0:k] = k_arr

        # 2) 1) + use minus index 
        new_arr = [None for i in range(len(nums))]

        for i in range(len(nums)):
            new_arr[i] = nums[i-k]

        nums[:] = new_arr[:]

        # 3) 1) + use mod
        new_arr = [None for i in range(len(nums))]
        
        for i in range(len(nums)):
            new_arr[(i+k)%len(nums)] = nums[i]

        nums[:] = new_arr[:]

        # 4) use string slicing method
        l = len(nums)
        nums[:] = nums[-(k%l):] + nums[:-(k%l)]

        # 5) use rotate of deque
        from collections import deque
        dnums = deque(nums)
        dnums.rotate(k)
        nums[:] = list(dnums)
        
