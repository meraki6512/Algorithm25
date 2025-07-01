# 10026. 적록색약
# https://www.acmicpc.net/problem/10026
# 1s, 128MB

# N * N (100)
# RG를 구분하지 못하는 적록색약인 사람과 아닌 사람이 봤을 때 구역의 수를 각각 구하라.

# input
from collections import deque
import sys
input = sys.stdin.readline
N = int(input())
grid = [list(input()) for _ in range(N)]

# sol
# bfs 내부: 같을 때만 계속 탐색하면서 visited 체크해주고, 다르면 빠져나옴
# 모든 칸을 최소 한 번씩 bfs하면서 (중복은 visited로 관리) 그 개수를 세자.

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(start_point, visited, isblind):

    que = deque()
    que.append(start_point)

    while que:

        x, y = que.popleft()

        # 큐에서 꺼낼 때 visited 체크하면 비효율적임! 주의
        # if visited[x][y]: continue
        # visited[x][y] = True

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx >= 0 and nx < N and ny >= 0 and ny < N and not visited[nx][ny]:

                cur_clr = grid[x][y]
                nxt_clr = grid[nx][ny]

                # 같을 경우(색맹일 땐 RG까지 포함)에만 더 탐색
                if isblind:
                    if (cur_clr == nxt_clr) or (cur_clr in 'RG' and nxt_clr in 'RG'):
                        visited[nx][ny] = True
                        que.append((nx, ny))
                
                elif cur_clr == nxt_clr:
                    visited[nx][ny] = True
                    que.append((nx, ny))

# output
# 일반과 적록의 로직 처리가 달라 따로 두 번 해줌
def sol(isblind):
    
    visited = [[False]*N for _ in range(N)]
    ans = 0

    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                visited[i][j] = True
                bfs((i, j), visited, isblind) 
                ans += 1
    
    return ans

print(sol(isblind=False), sol(isblind=True))















# sf
# 두 개를 따로 처리해야한다는 걸 떠올리는 점에 막힘이 있었음
# + 아이디어는 잘 구상해놓고 구현하는 게 되게 오래걸리고 어려웠음
# dfs, bfs 구현 연습이 잘 안되어있어서 그런듯. 더 연습하자.