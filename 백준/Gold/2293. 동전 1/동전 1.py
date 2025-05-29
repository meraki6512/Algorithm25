# input
n, k = map(int, input().split()) # 100, 10000

# n 개의 동전의 가치 100,000 보다 작거나 같은 자연수
coin_values = [int(input()) for _ in range(n)]

# solution
# 3. dp
# k원을 만드는 경우의 수 dp: dp[i] = dp[i] + dp[i-v[i]] 

dp = [0] * (k+1)
dp[0] = 1

for coin in coin_values:
    for i in range(coin, k + 1):
        dp[i] += dp[i-coin]

print(dp[k])