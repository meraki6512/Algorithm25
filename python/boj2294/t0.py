# 2294. 동전 2

# 1초, 128MB
# n가지 종류의 동전을 사용해 합이 k가 되도록 하는
# 동전의 최소 개수는?

# n 100  k 10000  가치 100000
# 가치가 같은 동전이 여러 번 주어질 수도 있음
# 불가능 시 -1

n, k = map(int, input().split())
temp = [int(input()) for _ in range(n)]

coins = sorted(list(set(temp)), reverse=True) # 가치가 큰 것부터, 중복 없이
ans = 0 # 사용한 동전의 개수
stack = [] # 사용한 동전
sum_stack = 0

for i in range(len(coins)):
    cur_coin = coins[i]

    while sum_stack + cur_coin <= k:
        stack.append(cur_coin)
        sum_stack += cur_coin
        ans += 1
    
    if sum_stack == k:
        break

ans = -1 if sum_stack != k else ans
print(ans)


# -> 틀린 풀이
# 12 + 1 + 1 + 1이 아닌 5 + 5 + 5가 최소임