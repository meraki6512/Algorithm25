# 7576. 토마토
# N, M: 1000 * 1000
# -1: 없, 0: 안익, 1:익
# 매일 1의 상하좌우는 1로 바뀜
# 0을 모두 1로 바꾸는 최소 day = ?
# 토마토 0인 게 없으면 0, 0인 걸 1로 모두 못바꾸면 -1

import sys
input = sys.stdin.readline
M, N = map(int, input().split())

from collections import deque
tmt = []
queue = deque([])
temp = deque([])
target = 0

# 토마토 위치 입력받으면서
for x in range(N):              # N * M : 10^6

    line = list(map(int, input().split()))
    tmt.append(line)            # 토마토 정보 겟

    for y in range(M):

        if line[y] == 1:        # 익은 토마토 위치 겟
            temp.append([x, y])

        elif line[y] == 0:      # 안 익은 토마토 없는지 체크를 위함
            target += 1
    
    queue.append((temp, 1))     # 익은 토마토 위치, day(lv) = 1 초기화


# 토마토 익히기
cnt = 0
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

# 탐색할 토마토 리스트가 존재하면서 익힐 수 있는 토마토의 총 개수와 현재 익힌 토마토의 개수가 다를 동안 반복
while queue and target != cnt:      # 4 * N * M

    que, lv = queue.pop()
    day = lv
    
    temp = deque()

    while que:                      # 같은 lv(day)의 익은 토마토

        x, y = que.popleft()

        for i in range(4):          # 다음(주변) 토마토 중에서

            nx = x + dx[i]
            ny = y + dy[i]

            if nx >= 0 and nx < N and ny >= 0 and ny < M:   # 범위 내에서
                if tmt[nx][ny] == 0:                        # 토마토 안 익었으면
                    tmt[nx][ny] = 1                         # 익히고
                    temp.append([nx, ny])                   # 동일한 lv의 리스트에 저장하고
                    cnt += 1                                # 익힌 토마토 수++
    
    if temp:                                                # 저장할 게 있으면
        queue.append((temp, lv + 1))                        # 동일한 lv(++)로 저장

# target이 0이었으면 0, 다 못 익혔으면 -1, 다 익혔으면 1
ans = 0
if not target:
    ans = 0
elif cnt < target:
    ans = -1
elif cnt == target:
    ans = day
    
print(ans)