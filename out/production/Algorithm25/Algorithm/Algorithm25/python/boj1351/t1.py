# 1351. 무한 수열 -> 메모리 초과 (N: 10^12, 128MB 제한)
# https://www.acmicpc.net/problem/1351

import sys
input = sys.stdin.readline
N, P, Q = map(int, input().split())

dp = [-1] * (N+1)

# 재귀적으로 Ai의 값을 계산한다 
# dp에 저장을 하면서 재귀를 안 해도 될 부분은 안한다.
def A(i):

    if i == 0:
        return 1
    
    pi = i//P
    if dp[pi] == -1:
        dp[pi] = A(i//P)

    qi = i//Q
    if dp[qi] == -1:
        dp[qi] = A(i//Q)
    
    return dp[pi] + dp[qi] 

print(A(N))