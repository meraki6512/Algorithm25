# 1932. 정수 삼각형
# 2s, 128MB

# 크기가 n인 삼각형 (n: 500)
# 경로의 합이 최대가 되는 경로의 합을 구하라

import sys
input = sys.stdin.readline
n = int(input())
tr = [list(map(int, input().split())) for _ in range(n)]

dp = [[0] * n for _ in range(n)]    # 500 * 500
for i in range(n): dp[0][i] = tr[0][0] 

for i in range(1, n):      # 작은 삼각형일 때부터 (윗 줄부터) 한 줄씩 검사
    for j in range(len(tr[i])):

        if j == 0:      # 양 끝 값이면
            dp[i][0] = dp[i-1][0] + tr[i][0] # 비교 없이 선택

        elif j == i:     # 양 끝 값이면
            dp[i][j] = dp[i-1][j-1] + tr[i][j] # 비교 없이 선택

        else:                       # 중간 값이면 큰 값 선택
            dp[i][j] = tr[i][j] + max(dp[i-1][j-1], dp[i-1][j])

print(max(dp[-1]))