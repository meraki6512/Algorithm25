# 1520. 내리막길
# 높이가 낮은 지점으로만 (0,0)->(n-1,n-1) 이동하는 경우의 수는?

# 2s, 128mb
# m n 500, 높이 10^4

# aprch3 dfs, dp!! - 3-2
# 3-1) nx, ny를 dp에 저장 -> Time-out
# 3-2) 경우의 수를 dp에 저장
# 둘 다 try해봅시당
# 500*500의 배열에서 R, D으로만 좌상->우하로 가는 경우의 수: 1000C500 (10^299정도)
# L, U까지 포함한다면 무수히 많음
# 백트래킹으로 조건을 쳐내야 함

# input
import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline
m, n = map(int, input().split())
_map = [list(map(int, input().split())) for _ in range(m)]

# solution
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
dp = [[0] * n for _ in range(m)]
visited = [[False] * n for _ in range(m)]
ans = 0

def dfs(x, y):

    global ans

    visited[x][y] = True

    if x == m-1 and y == n-1:       # 도착
        ans += 1
        return True
        
    for i in range(4):

        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < m and ny >= 0 and ny < n:
            if not visited[nx][ny] and _map[nx][ny] < _map[x][y]:
                
                # print(x, y, nx, ny, visited[nx][ny], dp)

                if not dp[nx][ny]:          # 탐색 안 한 곳만
                    
                    arrived = dfs(nx, ny)   # 탐색
                    
                    if arrived:             # 끝에 도달한 경우
                        dp[nx][ny] += 1     # 업데이트
                    
                    visited[nx][ny] = False
                
                else:                       # 업데이트된 경우
                    ans += 1                # 길이 있다고 봄


# output
dfs(0, 0)
print(ans)


# -> Time-out