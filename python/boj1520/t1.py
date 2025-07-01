# 1520. 내리막길
# 높이가 낮은 지점으로만 (0,0)->(n-1,n-1) 이동하는 경우의 수는?

# 2s, 128mb
# m n 500, 높이 10^4

# aprch2: dfs, stack!
# 500*500의 배열에서 R, D으로만 좌상->우하로 가는 경우의 수: 1000C500 (10^299정도)
# L, U까지 포함한다면 무수히 많음
# 백트래킹으로 조건을 쳐내야 함

# input
import sys
input = sys.stdin.readline
m, n = map(int, input().split())
_map = [list(map(int, input().split())) for _ in range(m)]

# solution
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
ans = 0

stack = [(0, 0)]

while stack:

    x, y = stack.pop()

    if x == m-1 and y == n-1:
        ans += 1
        continue
    
    for i in range(4):

        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < m and ny >= 0 and ny < n:
            if _map[nx][ny] < _map[x][y]:
                
                stack.append((nx, ny))

# output
print(ans)


# Time-out