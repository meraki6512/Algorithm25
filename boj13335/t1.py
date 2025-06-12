# 13335. 트럭
# 1s, 512MB

from collections import deque
import sys
input = sys.stdin.readline
# n 트럭 수(1000), w 다리의 단위 길이(100), L 다리의 최대 하중(1000)
n, w, L = map(int, input().split()) 
# 각 트럭의 무게 (10)
trucks = deque(map(int, input().split()))   

# sol
time = 0

while trucks:

    cur = 0
    t = -1  # 이동하는 트럭 수 (-1)만큼 계산
    
    # 더 이동 가능할 때 이동
    while trucks and cur + trucks[0] <= L and t < w-1:
        cur += trucks.popleft()
        t += 1

    # 총 다리 길이 + 이동하는 트럭 수 (-1)만큼 time에 더하면서 계산
    time += (w + t)

# 마지막 빠져나가는 트럭까지 
print(time + 1)



# -> 틀림