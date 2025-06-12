# 13335. 트럭
# 1s, 512MB

from collections import deque
import sys
input = sys.stdin.readline
# n 트럭 수(1000), w 다리의 단위 길이(100), L 다리의 최대 하중(1000)
n, w, L = map(int, input().split()) 
# 각 트럭의 무게 (10)
trucks = deque(map(int, input().split()))   


# 이동 횟수 계산 대신 빈 다리의 공간을 0으로 설정해둠
bridge = deque([0]*w)
cnt = 0
time = 0
moving_num = 0
moving_sum = 0

while cnt < n:

    # 이동 트럭 개수, 트럭 무게 제한 괜찮으면 다른 트럭 더 이동
    while moving_num < w and trucks and moving_sum + trucks[0] <= L:
        
        nxt = trucks.popleft()

        bridge.popleft()
        bridge.append(nxt)

        moving_num += 1
        moving_sum += nxt

        time += 1

    # 모든 가능한 트럭들이 이동 중이라면
    # 이동하는 트럭들이 다리 한 칸씩 이동
    out = bridge.popleft()

    if out:
        moving_num -= 1
        moving_sum -= out
        cnt += 1
    
    bridge.append(0)

    # 이동하는 트럭이 있을 때만 time++ (없을 때 새롭게 추가되는 건 동시로 처리)
    if moving_num:
        time += 1


# 마지막 트럭이 빠져나가는 시간까지 더해줘야함 (+1) 
print(time + 1) 



# -> testcase.ac 시간초과