# 1351. 무한 수열 -> Dict에 저장
# https://www.acmicpc.net/problem/1351

import sys
input = sys.stdin.readline
N, P, Q = map(int, input().split())

d = dict()
# 재귀적으로 Ai의 값을 계산한다 
# 저장을 하면서 재귀를 안 해도 될 부분은 안한다
# 근데 배열일 땐 메모리가 안되니 Dict(Hash)으로 관리한다

def A(i):

    if i == 0:
        return 1
    
    pi = i//P
    if pi not in d:
        d[pi] = A(i//P)

    qi = i//Q
    if qi not in d:
        d[qi] = A(i//Q)
    
    return d[pi] + d[qi] 

print(A(N))


# self-feedback
# 재귀 -> 시간복잡도를 고려하니, DP -> 공간복잡도를 고려하니, Hash
# 처음부터 바로 떠올리긴 어려워도 이런 과정이 빠르게 진행되면 좋을 듯하다.