# 3190. 뱀

# N 보드 크기 100       / N * N
# K 사과 개수 100
# (K줄) 사과 위치 i, j
# L 방향 전환 횟수 100
# (L줄) X초 후, LD(왼오) / X: 10^4  

# 이동 칸에 사과가 있으면 사과는 사라지고 몸은 늘어난다 (머리가 아닌 꼬리가 늘어남)
# 방향 전환이라면 머리만 이동하는 건가..?
# 시작하자마자 1초로 계산하면 되나? 아닌듯..

from collections import deque
import sys

# input 
input = sys.stdin.readline
N = int(input())
K = int(input())
apples = [list(map(int, input().split())) for _ in range(K)]
L = int(input())
que = deque()
for _ in range(L):
    que.append(list(input().split()))

# solution
x, y = 1, 1                             # 좌상단(1, 1)에서 시작 
d = [0, 1]                              # 맨 처음 머리는 오른쪽 (x, y++)
time = 0                                # ans
baam = [[0] * (N+1) for _ in range(N+1)]    # 뱀의 몸이 있는 곳 (1부터 N)
tail = deque()                          # 꼬리s 저장

# 사과 세팅
for _x, _y in apples:
    baam[_x][_y] = -1

# rl 값에 따라 d 업데이트
def rotate(rl):
    
    if d[0] == 0:           # 원래 상하하 방향으로 이동하고 있었을 때
        if rl == "D":       # 오른쪽으로 회전하하면
            d[0] = d[1]    
        else:               # 왼쪽으로 회전하면
            d[0] = -d[1]
        d[1] = 0            # 좌우 방향으로 바꿈
    
    elif d[1] == 0:         # 원래 좌우 방향으로 이동하고 있었을 때
        if rl == "D":       # 오른쪽으로 회전하면
            d[1] = -d[0]
        else:               # 왼쪽으로 회전하면
            d[1] = d[0]
        d[0] = 0            # 상하 방향으로 바꿈

while True:

    # 뱀의 몸 체크
    baam[x][y] = 1
    tail.append((x, y))

    # rotate
    if que and int(que[0][0]) == time:
        rotate(que.popleft()[1])

    # 다음 움직일 칸 계산
    nx, ny = x + d[0], y + d[1]

    # 벽 또는 자기 자신에 닿으면 GAME OVER
    if nx <= 0 or nx > N or ny <= 0 or ny > N or baam[nx][ny] == 1:
        time += 1
        break

    # 사과 처리
    if baam[nx][ny] < 0:        # 사과를 만나면
        baam[nx][ny] = 1        # 사과 먹고, 꼬리칸 업데이트 안함 (그대로 1; 꼬리 늘어남)
    
    else:                       # 사과 안 만났으면 
        tx, ty = tail.popleft() # 꼬리칸 옆으로 이동
        baam[tx][ty] = 0        # 꼬리칸 업데이트 (0으로 되돌리기; 해당 칸에 꼬리 없음)

    # x, y를 다음으로 이동
    x, y = nx, ny
    
    # 이동 한 번에 1초 증가
    time += 1


print(time)