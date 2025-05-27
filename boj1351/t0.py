# 1351. 무한 수열 -> 시간 초과
# https://www.acmicpc.net/problem/1351

import sys
input = sys.stdin.readline
N, P, Q = map(int, input().split())

# 재귀적으로 Ai의 값을 계산한다 
def A(i):

    if i == 0:
        return 1

    return A(i//P) + A(i//Q)

print(A(N))

