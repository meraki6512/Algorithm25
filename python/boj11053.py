# 11053. 가장 긴 증가하는 부분 수열 (LIS)
# https://www.acmicpc.net/problem/11053
# 1s, 256MB

# Ai 1000
# A의 크기 N 1000

import sys
input = sys.stdin.readline
N = int(input())
A = list(map(int, input().split()))

# 뒤에서부터 순회하자 (N)
# 앞의 값들과 비교해 현재 값 업데이트 : dp의 최댓값 vs dp++? (N)

dp = [1] * N

# 10 20 10 30 20 50
#  1  1  1  1  1  1
#  1  2  1  1  1  1
#  1  2  1  1  1  1
#  1  2  1  3  1  1
#  1  2  1  3  2  1
#  1  2  1  3  2  4

for i in range(1, N):                       # 기준값 i
    for j in range(i):                      # i 앞의 값들에 대해서

        if A[j] < A[i]:                     # 증가하면
            dp[i] = max(dp[i], dp[j]+1)     # dp[i] 업데이트

print(max(dp))


# self-feedback
# N^2에서 시간복잡도를 줄이려고 시도를 해보는 과정이 오래걸림
# 근데 그냥 다른 아이디어를 못 떠올리겠다..
# 찾아보니 거의 대부분 같은 방법으로 풀었음 .. 흠..
# -> LIS 2번 문제가 이 부분.
# -> boj 12015 풀면서 한 번 더 생각해보기!