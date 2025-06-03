# 12015. 가장 긴 증가하는 부분 수열 2 (LIS)
# https://www.acmicpc.net/problem/12015

# 1s, 512MB
# Ai 10^6
# N 10^6

# 시간복잡도를 줄이려면?
# N번 순회는 필수인데, 비교하면서 탐색하는게 1~logN 정도여야 함

# .!
# 개수를 세지 말고, 수열 자체를 만든다
# 작아지는 값이 나오면 그 값의 수열 속 위치를 bs로 찾아 크거나 같은 값을 대체한다

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
# 결국 생각해내지 못하고 검색의 도움을 받음
# 수열 자체를 만든다는 생각을 왜 못했을까..
# 작아지면 해당 데이터를 대체한다는 아이디어는 떠올렸는데 이진 탐색으로 이어지지 못함
# velog 정리하기
# 나중에 다시 풀어보기