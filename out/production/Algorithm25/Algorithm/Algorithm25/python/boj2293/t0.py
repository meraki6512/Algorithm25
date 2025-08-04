# 2293. 동전1
# https://www.acmicpc.net/problem/2293
# 서로 가치가 다른 n가지 종류의 동전 -> 합이 k
# 경우의 수를 구하라
# (단, 순서는 고려하지 않는다.)

# input
n, k = map(int, input().split()) # 100, 10000

# n 개의 동전의 가치 100,000 보다 작거나 같은 자연수
values = [int(input()) for _ in range(n)]

# solution
# 1. 중복 조합 nHr -> 시간 초과 (n-1+rCr)
def nHr():
    ans = 0

    from itertools import combinations_with_replacement

    i = 0
    while i <= k:

        combs = combinations_with_replacement(values, i)

        for c in combs:
            if sum(c) == k:
                ans += 1

        i += 1

    print(ans)

# 2. 재귀 -> maximum recursion depth exceeded in comparison
def recurs():
    
    ans = 0
    values.sort(reverse=True) # nlogn

    def dfs(cur): # 100 * 10000 # maximum : 1000

        if values[cur] == k:
            ans += 1
            return
        
        elif values[cur] > k:
            return
        

        for i in range(cur, n):
            dfs(i)

    dfs(0)
    print(ans)
