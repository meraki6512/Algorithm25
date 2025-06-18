# 2178. 미로 탐색
# 1s, 192MB

# N*M 미로 (100)
# 1이면 이동 가능, 0이면 이동 불가
# (1,1)에서 (N,M)까지 이동하는 최소 칸 수를 구하라; (첫 칸, 막 칸도 포함)

# import
import sys
input = sys.stdin.readline
N, M = map(int, input().split())

# sol
maze = [[False] * (M+1)] + [[False] + list(map(bool, map(int, list(input())[:-1]))) for _ in range(N)]
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
ans = sys.maxsize

def dfs(x, y, depth, visited):

    global ans

    if x == N and y == M:
        ans = min(ans, depth)
        return
    
    visited[x][y] = True

    for i in range(4):

        nx, ny = x + dx[i], y + dy[i]

        if nx > 0 and nx <= N and ny > 0 and ny <= M and not visited[nx][ny] and maze[nx][ny]:

            dfs(nx, ny, depth + 1, visited)

            visited[nx][ny] = False

# output
# dfs(1, 1, 1, [[False] * (M+1) for _ in range(N+1)])
# print(ans)

# 시간 초과남 : 미로에 1이 많을 수록 비효율적임
# -> bfs로 풀어보자. 먼저 도착하면 바로 종료하도록
from collections import deque

def bfs(x, y):

    que = deque([(x,  y, 1)])
    visited = [[False] * (M+1) for _ in range(N+1)]
    visited[x][y] = True
    ans = -1

    while que:

        x, y, d = que.popleft()     

        if x == N and y == M:
            ans = d
            break
        
        for i in range(4):

            nx, ny, nd = x + dx[i], y + dy[i], d + 1

            if nx > 0 and nx <= N and ny > 0 and ny <= M and not visited[nx][ny] and maze[nx][ny]:
                visited[nx][ny] = True
                que.append((nx, ny, nd))

    return ans

print(bfs(1, 1))