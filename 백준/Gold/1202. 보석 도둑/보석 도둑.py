# 보석 도둑
# m: 무게, v: 가격
# K개의 가방마다 넣을 수 있는 무게 c
# sort + heap + greedy

# input
import sys
input = sys.stdin.readline

N, K = map(int, input().split())
jewels = [list(map(int, input().split())) for _ in range(N)]
bags = [int(input()) for _ in range(K)]
ans = 0

# solution
import heapq
heap = []

# 무게 기준으로 asc 정렬 # nlogn
jewels.sort()
bags.sort()

j = 0 # jewels, bags가 무게 기준으로 정렬됨 -> jewels는 한 번씩만 push됨

# nlogn + klogn
for c in bags: 

    while j < N and jewels[j][0] <= c: # c보다 작거나 같은 무게들은 전부 heap에 넣음 # N
        heapq.heappush(heap, -jewels[j][1]) # max heap
        j += 1
    
    if heap: # heap이 존재하면 
        ans += -heapq.heappop(heap)

# output   
print(ans)


# 예시로 설명
# 보석 N = 4, 가방 K = 2

# 보석 정보 (무게, 가격):
# (2, 99)
# (1, 65)
# (4, 150)
# (3, 80)

# 가방 무게:
# 2
# 10

# jewels (무게 오름차순 정렬): [(1, 65), (2, 99), (3, 80), (4, 150)]
# bags (무게 오름차순 정렬): [2, 10]

# 첫 번째 가방 c = 2
# 무게 ≤ 2인 보석을 모두 push: heap = [99, 65]
# heappop: 99

# 두 번째 가방 c = 10
# 2 < 무게 ≤ 10인 남은 보석을 push: heap = [150, 65, 80]
# heappop: 65




## self-feedback
## 시간복잡도 계산 상 괜찮을 때 시간초과가 나면 sys.stdin.readline 떠올리기
## 시간복잡도 고려: max/min 값을 구해 처리해야 할 때 heap을 먼저 떠올리자...
## 나중에 다시 풀기


# solution # 시간 초과 (9*10^10)
# ans = [0] * N
 
# jewels.sort(key=lambda x: -x[1]) # 보석 가격 v desc 정렬 (클 수록 좋음) # nlogn #n=3*10^5
# bags.sort() # 가방 최대 무게 c asc 정렬 (작은 곳에 큰 것 먼저) #nlogn #n=3*10^5

# for i, c in enumerate(bags): # 3*10^5

#     for j, (m, v) in enumerate(jewels): # 3*10^5

#         if ans[j]: # 한 번 넣은 보석은 제외
#             continue

#         elif m <= c:
#             ans[j] = v
#             break

# print(sum(ans))     