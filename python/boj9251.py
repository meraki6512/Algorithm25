# BOJ 9251 (https://www.acmicpc.net/problem/9251)
# LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 
# 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
# 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
# 입력 # 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
# 출력 # 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.
# 예제 입력 1
# ACAYKP
# CAPCAK
# 예제 출력 1 
# 4
# ------------------------------------------------------------
# Greedy? # 2-pointer? # ...DP?
# Start with the simple case
# ACAYKP
# CAPCAK
# str1 * str2 -> Onm / 0.1s ... 
# Substring / Subsequence

class Solution:

    def LCS(str1, str2):

        L1 = len(str1)+1
        L2 = len(str2)+1

        dp = [[0 for _ in range(L2)] for _ in range(L1)] ### 2차원 배열 생성 시 주의

        for i in range(1, L1):
            for j in range(1, L2):
                dp[i][j] = dp[i-1][j-1] + 1 if str1[i-1] == str2[j-1] else max(dp[i-1][j], dp[i][j-1])
                
        # O(nm)
        return dp[L1-1][L2-1] # LCS 길이
    
str1 = input().strip()
str2 = input().strip()
ans = Solution.LCS(str1, str2)
print(ans)

