# 1525. 퍼즐
# 1초, 32MB
# 3*3 퍼즐
n = 3

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
puzzle = [list(map(int, input().split())) for _ in range(n)]

# 0의 위치를 옮기면서 백트래킹?
i0 = j0 = 0
for i in range(n):
    for j in range(n):
        if puzzle[i][j] == 0:
            i0, j0 = i, j
            break

visited = [[0] * n] * n 
visited[i0][j0] = 1

sol = [[0] * n] * n 
cnt = 1
for i in range(n):
    for j in range(n):
        sol[i][j] = cnt
        cnt += 1

def check_puzzle():
    for i in range(n):
        for j in range(n):
            if sol[i][j] != puzzle[i][j]:
                return False
    return True


def do_puzzle(x, y, ans):
    
    if x == n-1 and y == n-1:
        if check_puzzle():
            return ans
        
    if visited[x][y]:
        return ans
    
    for i in range(4):

        xp = x + dx[i] 
        yp = y + dy[i]

        if xp < 0 or xp >= n or yp < 0 or yp >= n:
            continue

        visited[xp][yp] = 1

        ans = min(ans, do_puzzle(x + dx[i], y + dy[i], ans + 1))

        visited[xp][yp] = 0
    
    return ans

ans = do_puzzle(i0, j0, 0)
result = ans if ans > 0 else -1 
print(result)