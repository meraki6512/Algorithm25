# 1916. 최소비용 구하기
# https://www.acmicpc.net/problem/1916
# 0.5s, 128MB

# 도시 개수 N (1000)
# 버스 개수 M (10^5) - 버스 비용 [0, 10^5)

# input
import heapq
import sys
input = sys.stdin.readline

N = int(input())
M = int(input())

bus = [[] for _ in range(N + 1)]
for _ in range(M):
    dprt, arrv, cost = map(int, input().split())
    bus[dprt].append([arrv, cost])

s, e = map(int, input().split())

# sol
# dijkstra
# 출발지로부터 각 노드의 min cost를 저장
# 방문하지 않은 노드 중 가장 cost가 적은 노드 선택
# 해당 노드 거쳐 특정한 노드로 가는 경우를 고려해 최소 비용 갱신
costs = [1e9] * (N + 1)                     # result 담을 배열
costs[s] = 0

heap = []
heapq.heappush(heap, [0, s])                # cur cost, cur pos(start)
while heap:
    
    cost, pos = heapq.heappop(heap)
    
    if costs[pos] < cost:                   # 현재 cost보다 이미 costs에 담긴 값이 작으면 pass
        continue

    for n_pos, n_cost in bus[pos]:
        if cost + n_cost >= costs[n_pos]:   # 더 작아지지 않으면
            continue                        # 갱신하지 X

        costs[n_pos] = cost + n_cost        # 더 작아지면 갱신하고 heap에 push
        heapq.heappush(heap, [cost + n_cost, n_pos])    

# output
print(costs[e])


# 다시 풀기