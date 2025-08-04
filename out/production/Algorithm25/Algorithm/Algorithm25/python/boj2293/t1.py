# 2293. 동전1
# https://www.acmicpc.net/problem/2293
# 서로 가치가 다른 n가지 종류의 동전 -> 합이 k
# 경우의 수를 구하라
# (단, 순서는 고려하지 않는다.)

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

# e.g.
# 1, 2, 5 -> 10
# dp[0] = margin 1
# 동전 1:       1/1 1 1 1 1 1 1 1 1 1
#               1원: 1      : 1개
#               2원: 1+1    : 1개
#               3원: 1+1+1  : 1개
#               ...
# 동전 (1,)2:   1/1 2 2 3 3 4 4 5 5 6
#               2원 밑은 바뀔 일 없음
#               2원: 1+1,/ 2              : dp[2] + dp[0] 개
#               3원: 1+1+1,/ 1+2          : dp[3] + dp[1] 개
#               4원: 1+1+1+1,/ 1+1+2, 2+2 : dp[4] + dp[2] 개
#               ...
#               (현재 coin이 없었을 때 만들 수 있는 i)기존 경우의 수 
#               + 현재 coin 만큼을 뺐을 때의 경우의 수
# 동전 (1,2,)5: 1/1 2 2 3 4 5 6 7 8 10
#               5원 밑은 바뀔 일 없음
#               5원: dp[5] + dp[0] 개
#               6원: dp[6] + dp[1] 개
#               7원: dp[7] + dp[2] 개
#               ...
# 동전 1, 2, 5로 10을 만들 수 있는 경우의 수: dp[10]











# self-feedback
# 참고자료: 
# https://velog.io/@rhdmstj17/%EB%B0%B1%EC%A4%80-2293%EB%B2%88-%EB%8F%99%EC%A0%84-1-python-%EB%8B%A4%EC%9D%B4%EB%82%98%EB%AF%B9-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D
# https://velog.io/@jxlhe46/%EB%B0%B1%EC%A4%80-2293%EB%B2%88.-%EB%8F%99%EC%A0%84-1-bfi120m5
# 넘어려웟 .. 생각을 더 유연하게./
# 다시 풀어보기.
# dp 쉬운 문제부터 여럿 연습하기.