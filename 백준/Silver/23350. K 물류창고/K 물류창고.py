# 23350. K 물류창고
# 1s, 512MB

# N개의 컨테이너
# - 무게 W 
# - 우선순위 1~M (큰 값을 먼저 처리)
# 
# 현재 컨테이너보다 작은 값이 뒤에 하나라도 있다면 맨 뒤로 보냄 (무게만큼의 비용 발생)
# 
# 컨테이너를 적재할 때 우선 순위가 같다면 무게가 무거운 컨테이너를 아래에 위치시킴
# 가벼운게 먼저 적재돼 있으면, 빼내고 다시 적재 (빼낼 때도 무게만큼의 비용 발생)
# 
# 우선 순위 1~M에 대해 각각 최소 하나의 컨테이너가 존재함

from collections import deque
import sys
input = sys.stdin.readline
N, M = map(int, input().split())
priorities = []
que = deque()

for i in range(N):
    p, w = map(int, input().split())
    priorities.append(p)
    que.append([p, w])

priorities.sort()
warehouse = []
cost = 0

while que:

    p, w = que.popleft()

    # 현재 컨테이너의 우선순위가 가장 큰 우선순위라면 
    # (현재 컨테이너 적재 가능)
    if p == priorities[-1]:         
        temp = []
        
        # 현재와 동일한 우선순위의 컨테이너 중에서 무게에 따른 적절한 위치에 컨테이너를 적재 (무거운 게 아래); Stack
        while warehouse and warehouse[-1][0] == p and warehouse[-1][1] < w:
            cost += warehouse[-1][1] * 2    # 가벼운 컨테이너 무게의 2배만큼의 비용 (빼고, 적재하고)
            temp.append(warehouse.pop())    # 잠깐 빼둠

        warehouse.append([p, w])
        cost += w                           # 현재 컨테이너 적재하는 비용

        warehouse += reversed(temp)         # 빼둔 가벼운 컨테이너들 다시 순서대로 적재함
        priorities.pop()        

    # 현재 컨테이너의 우선순위보다 큰 우선순위가 존재한다면
    else:                                
        que.append([p, w])                  # 현재 컨테이너 맨 뒤로 보냄
        cost += w                           # 현재 컨테이너 맨 뒤로 보내는 비용 

print(cost)