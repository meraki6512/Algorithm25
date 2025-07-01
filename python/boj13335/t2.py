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
time = 0

while bridge:

    time += 1                                       # 시간은 트럭 유무와 관계없이 흐름
    bridge.popleft()                                # 다리 위의 흐름도 트럭 유무와 관계없음

    # 이동 트럭 무게 제한 괜찮으면 다른 트럭 더 이동 
    # (이동 트럭 개수는 고려X (0이 채우고 있음))
    if trucks:                                      # 남은 트럭이 존재할 경우에만
        if sum(bridge) + trucks[0] <= L:            # 해당 트럭이 이동가능한지 판단
            bridge.append(trucks.popleft())         # 가능하면 이동
        else:
            bridge.append(0)                        # 불가하면 0으로 채움

# out
print(time) 