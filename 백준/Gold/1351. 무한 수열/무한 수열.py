# 1351. 무한 수열 -> Dict에 저장
# https://www.acmicpc.net/problem/1351

import sys
input = sys.stdin.readline
N, P, Q = map(int, input().split())

sA = dict()
# 재귀적으로 Ai의 값을 계산한다 
# 저장을 하면서 재귀를 안 해도 될 부분은 안한다
# 근데 배열일 땐 메모리가 안되니 Dict으로 관리한다

def A(i):

    if i == 0:
        return 1
    elif i == 1:
        return 2
    
    pi = i//P
    if pi not in sA.keys():
        sA[pi] = A(i//P)

    qi = i//Q
    if qi not in sA.keys():
        sA[qi] = A(i//Q)
    
    return sA[pi] + sA[qi] 

print(A(N))