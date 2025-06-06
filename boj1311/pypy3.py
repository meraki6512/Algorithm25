# 1311. 할 일 정하기
# 1s, 512MB

# N명 -> N개 일을 하는 경우의 최소 비용
# N: 20
# D[i][j] = i번 사람이 j번 일을 하는 cost: 각 10^4

import sys
input = sys.stdin.readline
N = int(input())
D = [[*map(int, input().split())] for _ in range(N)]    # [*map()]: map한 것을 unpacking 후 list에 넣음: list(map())과 같은 효과

# 원래대로는 N!
# DP -> N^2~3까지는 줄일 수 있지 않을까 -> ..X
# -> 비트 마스킹.. -> N * 2^N까지 가능

# 배정/할당하는 문제

dp = [1e10]*(1<<N)  # 1을 왼쪽으로 N번 밀면 2^N개 표현 가능
dp[0] = 0

for i in range(1<<N):
    k = 0
    for j in range(N):
        if i & (1<<j):  # j의 일 배정 완료 시
            k += 1      # 즉, k개의 일을 배정하면 k번째 사람이 다음 일할 차례
    
    for j in range(N):
        if i & (1<<j) == 0:     # j의 일 아직 배정 X
            dp[i | (1<<j)] = min(dp[i | (1<<j)], dp[i] + D[k][j]) # 기존 cost vs 현재 i 상태의 cost에 + k번째 사람이 j번째의 일을 배정한다면 그 cost 중 작은 값

print(dp[-1])
                 

## -> PyPy로 통과 가능, python은 시간 초과
## -> python은 recursion으로 풀어야 통과 가능


# self-feedback
# 일단 아이디어도 떠올리지 X
# 검색을 통해 공부할 때 bitmask 개념이 너무 새롭게 느껴졌음
# 우선 velog에 정리하면서 문제부터 다시 이해하고 풀어보기
# pypy3 말고 python3 방법은 아이디어를 봐뒀으니 스스로 풀어보기 (dp, 백트래킹, 비트마스크)
# 다시 공부하면서 유사한 문제 풀기
# 11049 행렬 곱셈 순서: https://www.acmicpc.net/problem/11049
# 16234 인구 이동 : https://www.acmicpc.net/problem/16234
# 1094 막대기 : https://www.acmicpc.net/problem/1094
# 2098 외판원 순회 (TSP) : https://www.acmicpc.net/problem/2098
# 10971 외판원 순회2 : https://www.acmicpc.net/problem/10971
# 1941 소문난 칠공주 : https://www.acmicpc.net/problem/1941
# 12996 Tidy Numbers : https://www.acmicpc.net/problem/12996