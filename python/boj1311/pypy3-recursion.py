# 1311. 할 일 정하기
# 1s, 512MB

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

# N명 -> N개 일을 하는 경우의 최소 비용
# N: 20
# D[i][j] = i번 사람이 j번 일을 하는 cost: 각 10^4

N = int(input())
D = [[*map(int, input().split())] for _ in range(N)]    # [*map()]: map한 것을 unpacking 후 list에 넣음: list(map())과 같은 효과

# 원래대로는 N!
# DP + 비트 마스킹.. -> N * 2^N까지 가능

dp = [[-1]*(1<<N) for _ in range(N)]    # [k][visited] # k번째 사람(~N), visited 배정된 일(~1<<N) => 까지의 최소 비용

# 배정/할당하는 문제
# k: 지금까지 맡긴 사람 수
# visited: 어떤 일을 했는가 (boolean 배열 대신 bit mask) # e.g. 0b011: 0번, 1번 일 완료

def dfs(k, visited):

    if visited == (1<<N) - 1:       # 마지막 일까지 (모든 일) 배정 완료
        return 0                    # 더하지 않고 종료
    
    if dp[k][visited] != -1:        # 일을 배정한 상태라면 
        return dp[k][visited]       # 기존 값 사용
    
    ret = 1e10
    for i in range(N):              
        if not (visited & (1<<i)):  # 남은 일들 중에서
            ret = min(ret, (dfs(k+1, visited | (1<<i)) + D[k][i]))    # min 기존 값 vs 새로운 일의 비용
    
    dp[k][visited] = ret            # k번째 사람이 현재 일을 하는 데의 비용을 저장해둠
    return ret                      # (dp 업데이트 후) 해당 비용 리턴

print(dfs(0, 0))    # dfs(0, 0b000) : 0번째 사람 차례, 아직 아무도 일 배정X

# N = 3
# cost = [
#   [2, 3, 3],
#   [3, 2, 3],
#   [3, 3, 2]
# ]