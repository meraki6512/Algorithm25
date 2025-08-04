# # 백준이[]에서 btm 부터 꺼내면서 
# # 동생[]에 넣고 
# # 정렬해 중간 값을 구함

# ybro = []

# N = int(input())

# for i in range(N):
#     x = int(input())
    
#     ybro.append(x)
#     ybro = sorted(ybro)

#     mid = int(len(ybro)/2)
#     idx = (mid - 1) if (len(ybro) % 2 == 0) else mid
    
#     print(ybro[idx])

# 시간 초과
# 정렬을 안 하는 방법??? 
# nlogn탐색이라서 그런거같은데

# ->>> 우선순위 큐 (logn)
# 입력이 들어올 때마다 중간값과 비교해 left, right heap을 각각 구성해야함
# left heap은 max heap
# right heap은 min heap
# 으로 구성해 root가 mid가 되도록 해야 함

# 또 주의할 점
# input()은 시간 오래 걸림 ->>> sys.stdin.readline()

import heapq
import sys

N = int(sys.stdin.readline())
mid = 0

left_max_heap = []
right_min_heap = []

for i in range(N):
    x = int(sys.stdin.readline())
    
    # left와 right heap의 균형이 맞도록 push
    if len(left_max_heap) == len(right_min_heap): 
        heapq.heappush(left_max_heap, -x) # 홀수일 때 작은 걸 출력해야하므로 left에 먼저 push; max heap
    else:
        heapq.heappush(right_min_heap, x)

    # left와 right가 모두 존재하고
    # push 했을 때 left의 root*(-1)(최댓값)이 right의 root(최솟값)보다 더 크다면 스위치
    if right_min_heap and left_max_heap[0]*(-1) > right_min_heap[0]:
        lv = heapq.heappop(left_max_heap)
        rv = heapq.heappop(right_min_heap)
        
        heapq.heappush(left_max_heap, -rv)
        heapq.heappush(right_min_heap, -lv)
        
    print(left_max_heap[0]*(-1))
