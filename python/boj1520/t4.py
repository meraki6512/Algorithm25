# 1520. 내리막길
# 높이가 낮은 지점으로만 (0,0)->(n-1,n-1) 이동하는 경우의 수는?

# 2s, 128mb
# m n 500, 높이 10^4

# aprch4 ... 높이/도달값 자체를 업데이트하면서 경로를 축소해가면서 탐색?
# 500*500의 배열에서 R, D으로만 좌상->우하로 가는 경우의 수: 1000C500 (10^299정도)
# L, U까지 포함한다면 무수히 많음
# 백트래킹으로 조건을 쳐내야 함

# 그니까.. 더이상 갈 곳이 없는 곳은 4방향 중 가장 큰 값으로 업데이트하면서 이동

# input
import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline
m, n = map(int, input().split())
_map = [list(map(int, input().split())) for _ in range(m)]

# solution
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
ans = 0

def dfs(x, y):

    global ans

    if x == m-1 and y == n-1:
        ans += 1
        return
    

    # 경로 축소하면서 탐색
    
    move = False
    max_n = list()

    for i in range(4):

        nx = x + dx[i]
        ny = y + dy[i]

        if nx >=0 and nx < m and ny >=0 and ny < n:

            if _map[nx][ny] < _map[x][y]:

                move = True
                dfs(nx, ny)

            else:
                max_n.append(_map[nx][ny])
    
    _map[x][y] = _map[x][y] if move else max(max_n) 


# output
dfs(0, 0)
print(ans)


# -> Time OUT!!!!