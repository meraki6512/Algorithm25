class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:

        # O(n) ... 중복되는 곱셈을 어떻게 제거할까?
        #     * 구간합 알고리즘: O(1)... sum[j] = sum[j-1] + arr[j]
        
        # start -> end
        # end -> start

        answer = []
        prefix = [nums[0]]
        suffix = [nums[-1]]

        for i in range(1, len(nums)-1):
            prefix.append(nums[i] * prefix[i-1]) 
        
        for i in range(1, len(nums)-1):
            suffix.append(nums[len(nums)-1-i] * suffix[i-1])

        print(prefix, suffix)
        #[1, 2, 6] [4, 12, 24]
        # suffix[-1]
        # prefix[0] * suffix[-2]
        # prefix[1] * suffix[-3]
        # prefix[2]

        answer.append(suffix[-1])
        for i in range(len(nums)-2):
            answer.append(prefix[i] * suffix[-2-i])
        answer.append(prefix[-1])

        return answer
