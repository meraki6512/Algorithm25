# Top Interview 150
# 88. Merge Sorted Array

class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        
        # p1, p2 
        
        # while p1 < m and p2 < n
        # if p1 <= p2: result.append(p1) p1++
        # else: result.append(p2) p2++

        # if p1 != m: result.append(the rest of num1)
        # if p2 != n: result.append(the rest of num2)

        p1 = p2 = 0
        result = list()

        while (p1 < m and p2 < n):
            
            if (nums1[p1] <= nums2[p2]):
                result.append(nums1[p1])
                p1 += 1
            else:
                result.append(nums2[p2])
                p2 += 1

        while p1 < m:    
            result.append(nums1[p1])
            p1 +=1

        while p2 < n:    
            result.append(nums2[p2])
            p2 +=1

        for i in range(len(result)):
            nums1[i] = result[i]
