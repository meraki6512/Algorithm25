# 1525. 퍼즐
# https://www.acmicpc.net/problem/1525
# 1초, 32MB / 3*3

# input
import sys

input = sys.stdin.readline
puzzle = ""
for _ in range(3):
    puzzle += ''.join(input().split()) 


# bfs
from collections import deque

visited = set([puzzle])
que = deque([(puzzle, 0)])

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

ans = -1

while que:

    cur, cnt = que.popleft()

    # escape
    if cur == "123456780":
        ans = cnt
        break
    
    # index of 0 (as arr)
    idx = cur.index("0")
    x, y = idx // 3, idx % 3

    # keep searching
    for i in range(4):

        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= 3 or ny < 0 or ny >= 3:
            continue  
        
        # swap
        temp = list(cur)
        nidx = nx * 3 + ny
        temp[nidx], temp[idx] = temp[idx], temp[nidx]
        next = ''.join(temp)

        # check visited
        if next in visited:
            continue
        else:
            visited.add(next)
            que.append((next, cnt + 1))


# output
print(ans)


# self-feedback
# 2차원일 때 1차원으로 생각해볼 수 있어야 한다
# 1차원 문자열을 숫자로 계산하는 게 더 효율적일지도.. 연습해보기
# bfs 사용하는 것도 연습 더 해야할 듯
