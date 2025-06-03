# 12015. 가장 긴 증가하는 부분 수열 3 (LIS)
# https://www.acmicpc.net/problem/12738

# 3s, 512MB
# Ai -10^9, 10^9
# N 10^6

import sys
input = sys.stdin.readline
N = int(input())
A = list(map(int, input().split()))

import bisect
lis = [A[0]]

for i in range(1, N):

    if lis[-1] < A[i]:                          # lis 수열의 마지막 값보다 현재 값이 크면
        lis.append(A[i])                        # lis 수열에 추가 가능 (증가하므로)

    else:                                       # 현재가 더 작으면
        idx = bisect.bisect_left(lis, A[i])     # 이진 탐색: 수열에서 A[i]보다 크거나 같은 원소의 인덱스
        lis[idx] = A[i]                         # lis의 해당 idx를 A[i]로 update
    
print(len(lis))


# self-feedback
# LIS 2와의 차이를 모르겠다..
# 범위가 늘어나도 이진탐색으로 하면 logN의 탐색이기에 변함없지 않나..?
