class Solution:
    def climbStairs(self, n: int) -> int:
        
        # n[2] + n[3] = n[4]

        num = [1, 2, 3]

        if n < 4:
            return num[n-1]

        for i in range(3, n):
            num.append(num[i-1] + num[i-2])

        return num[n-1]

