# 1520. 내리막길
# 높이가 낮은 지점으로만 (0,0)->(n-1,n-1) 이동하는 경우의 수는?

# 2s, 128mb
# m n 500, 높이 10^4

# aprch5 ... 다시 dp... 경우의 수 저장...
# 500*500의 배열에서 R, D으로만 좌상->우하로 가는 경우의 수: 1000C500 (10^299정도)
# L, U까지 포함한다면 무수히 많음
# 백트래킹으로 조건을 쳐내야 함

# input
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
m, n = map(int, input().split())
_map = [list(map(int, input().split())) for _ in range(m)]

# solution
dp = [[-1] * n for _ in range(m)]  # -1: 아직 방문 안함

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def dfs(x, y):
    
    # 도착점에 도달하면 1을 반환
    if x == m - 1 and y == n - 1:
        return 1
    
    # 이미 계산된 경우
    if dp[x][y] != -1:
        return dp[x][y]
    
    dp[x][y] = 0  # 경로 수 초기화

    for i in range(4):

        nx, ny = x + dx[i], y + dy[i]

        if 0 <= nx < m and 0 <= ny < n and _map[nx][ny] < _map[x][y]:
            dp[x][y] += dfs(nx, ny)
        
    return dp[x][y]

print(dfs(0, 0))



# self-feedback
# 생각해내는 것도 오래 걸렸음
# 구현도 어려워서 gpt 도움받았음...
# dp로 저장해도 recurse 횟수는 최악의 경우 모든 칸에 들어감 - recursionlimit 세팅 요구됨
# 나중에 다시 풀고 velog에 정리하기