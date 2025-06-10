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

# input
from collections import deque
que = [deque(), deque()]
parts = []      

for i in range(N):
    d, m, r = map(int, input().split()) 
    parts.append(m)                     # parts = 부품의 무게 리스트
    que[d].append([i, m, r])            # [d번 비행기]에 # 부품 번호, 무게, 준비 시각

# solution
result = [[-1, -1] for _ in range(N)]
loaded = [0] * N
cur_time = cur_pos = 0

print("전체 부품")
print("0번 부품 리스트:", que[0])
print("1번 부품 리스트:", que[1])
print()

while que[0] or que[1]:

    sumM = 0            # 현재 트럭에 적재된 무게 (초기화)
    q = que[cur_pos]    # 현재 위치에 있는 부품들

    print()
    print("현재 위치:", cur_pos)
    print("0번 부품 리스트:", que[0])
    print("1번 부품 리스트:", que[1])

    # 부품 적재
    # 준비되었고 트럭 적재 가능한 무게의 부품에 한해 적재
    while q and q[0][2] <= cur_time and sumM < M:  
        
        i, m, r = q[0]

        # 적재 가능한 만큼 적재
        can_load = min(M - sumM, m)     # min 남은 적재 용량 vs 현재 무게
        sumM += can_load
        loaded[i] += can_load
        print("현재 적재량:", can_load)

        # 출발 시간 저장
        if result[i][0] == -1:          # 처음 부품을 담을 때만
            result[i][0] = cur_time     # 업데이트
        
        # 도착 시간 저장
        if loaded[i] == parts[i]:       
            result[i][1] = max(result[i][1], cur_time + T)
        
        # 아직 부품이 남아있으면 무게 업데이트
        if can_load < m:                # (can_load == M - sumM) < m
            q[0][1] = m - can_load 
        else:                           # 부품 쪼개지 않았으면
            q.popleft()                 # 꺼냄

        print("현재 시간:", cur_time)
        print("현재 결과:", result)

    # 트럭 이동
    print("트럭 총적재량:", sumM)
    print("0번 부품 리스트:", que[0])
    print("1번 부품 리스트:", que[1])
    nq = que[cur_pos^1]

    if sumM != 0:                                   # 뭔가 적재했으면
        cur_time += T                               # 이동부터
        cur_pos ^= 1        

    elif nq:                                        # 다음이 있는데
        if q and q[0][2] <= nq[0][2]:               # 현재가 더 먼저 준비되면
            cur_time += q[0][2]                     # 현재부터
        
        elif q and q[0][2] > nq[0][2]:              # 다음이 더 먼저 준비되면
            cur_time = nq[0][2] + T                 # 다음까지 기다린 뒤에 이동
            cur_pos ^= 1
        
        else:                                       # 현재가 더이상 없다면
            cur_time += T                           # 다음으로 바로 먼저 이동
            cur_pos ^= 1

    elif q:                                         # 현재가 있으면
        cur_time += q[0][2]                         # 현재 계속

    print("이동 후 현재 시간:", cur_time)
    print("이동 후 현재 위치:", cur_pos)
    print()

# output
for s, e in result:
    print(s, e)



# 두 비행기에서 분해된 부품이 아직 운송이 준비되지 않은 경우도 존재할 수 있다. 이런 경우 하나의 부품이 준비될 때까지 기다리다가 부품이 먼저 준비된 쪽으로 이동한다. ...
# 이 부분을 좀 다르게 이해했던 것 같음.
# 어쨌든 먼저 준비된 쪽으로 이동하고 나서 기다리는 게 아니라, 기다리다가 먼저 준비되는 게 생기면 이동하는 거라서,
# 먼저 현재 시간은 준비된 시간에 추가로 T만큼의 시간을 더해야 하는게 포인트 같다.
#
# 만약, 현재 트럭이 있는 비행기가 모두 분해되었으면 반대쪽 비행기로 바로 이동해서 분해된 부품이 나올 때까지 대기한다.
# 그리고 현재가 없을 경우에만 바로 이동하는 거라 이때는 현재 시간은 현재 시간에 T만큼 더해주기만 하면 된다.