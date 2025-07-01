# 25604. 비행기 전시
# 1s, 512MB

# 2대의 비행기 위치 0번, 1번
# 트럭이 두 위치 사이를 이동하는 시간 T 100
# N개의 부품 (더 분해 가능)           1000    
# 트럭 최대 적재량 M                  1000

import sys
input = sys.stdin.readline
N, M, T = map(int, input().split())

# 각 부품이 나오는 비행기의 번호와 무게, 운송이 준비되는 시간이 주어질 때,
# 각 부품이 출발하고 도착하는 시간을 순서대로 출력하라.
# *
# 부품이 분해된 경우, 출발 시간은 최초 출발 시간, 도착 시간은 모든 분해된 부품이 도착한 시간
# 운송이 준비되는 시간은 감소하지 않는 순서로 주어짐
# 처음 부품은 항상 0번 위치에 있는 비행기의 부품
# 처음 트럭은 항상 0번 위치에 있음
from collections import deque
p0 = deque()
p1 = deque()
p = [p0, p1]

for i in range(N):
    di, mi, ri = map(int, input().split())
    p[di].append([mi, ri])

# solution
cur_time = 0
cur_pos = 0
ans = [[] for _ in range(N)]
i = 0
part = [0] * 2
cur_M = M

while p[cur_pos] or p[cur_pos^1]:

    # 현재 위치에 적재할 부품이 없으면 바로 이동
    if not p[cur_pos]:  
        cur_time += T
        cur_pos ^= 1

    # cur_pos에서 최대한 많이 부품 적재 (트럭 한 번 이동)
    while cur_M > 0:

        # cur_pos에서 적재할 부품 확인
        mi, di = p[cur_pos][0]
        
        # 해당 부품이 아직 운송 준비되지 않았을 때
        while di < cur_time:
            if cur_M < M and p[cur_pos^1][0][1] >= cur_time: # 이미 무언가 적재했고 다른 장소는 준비되었을 경우
                
                if part[cur_pos] == 0:              # 시간 저장
                    ans[i] = [cur_time, cur_time + T]
                else:                       
                    ans[i][1] += 2 * T
                    part[cur_pos] = 0

                cur_M = 0                           # 다른 장소로 이동
                break
            else:                                   # 다른 장소도 아직인 경우
                cur_time = di
        
        # 부품의 무게가 트럭 최대 적재량보다 크다면
        # 나눠서 가야 함 - 다음에 무조건 가능 (mi: 1 ~ M)
        if mi > cur_M:
            p[cur_pos][0][0] = i - cur_M
            part[cur_pos] = 1

        # 시간 저장
        if part[cur_pos] == 0:      # 부품을 나누지 않은 상태라면
            ans[i] = [cur_time, cur_time + T]
        else:                       # 부품을 나눈 상태라면
            ans[i][1] += 2 * T
            part[cur_pos] = 0

        # cur_pos에서 부품 이동
        p[cur_pos].popleft()

        # 다음 부품
        cur_M -= mi
        i += 1

    # cur_pos에서 적재할 만큼 다 적재하고 이동시켰다면
    cur_M = M
    cur_pos ^= 1
    cur_time += T

print(ans)


# 너무 꼬여있음
# 트럭과 부품 번호 기준으로 다시 짜보자!
# -> t1