# 2294. 동전 2
# n가지 종류의 동전을 사용해 합이 k가 되도록 하는 동전의 최소 개수는?

# 동전 1(2293)과 다른 점: 경우의 수가 아닌 개수.

# 1초, 128MB
# n 100  k 10000  가치 100000
# 가치가 같은 동전이 여러 번 주어질 수도 있음 # ? 
# 불가능 시 -1

import sys
input = sys.stdin.readline
n, k = map(int, input().split())
temp = [int(input()) for _ in range(n)]


INF = 1000000 + 1
coins = sorted(list(set(temp))) # asc, 중복 없이
dp = [INF] * (k+1) # 동전의 개수에 대한 dp
dp[0] = 0

for coin in coins: # 현재 coin # n 
    for i in range(coin, k+1): # 현재 k # i-coin (<= k)
        dp[i] = min(dp[i], dp[i-coin] + 1)

ans = -1 if dp[k] == INF else dp[k]
print(ans)





# self-feedback
# dp 계산 시 greedy로 접근하면 안되고, 미리 계산해둔 dp값을 활용해야함
# -> 점화식 세울 때 주의하기