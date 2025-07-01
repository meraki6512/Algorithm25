# 13549. 숨바꼭질 3
# 2s, 512MB

N, K = map(int, input().split())
MAX_SIZE = 100001

# Idea
# 가중치가 0-1인 최단거리 문제라서 BFS로 풀 수 있음
# 시간복잡도의 문제도 없음 (3*10^5)

from collections import deque

def bfs(N, K):

    que = deque([(N, 0)])
    visited = [False] * MAX_SIZE
    visited[N] = True
    ans = MAX_SIZE
    
    while que:

        X, t = que.popleft()
        visited[X] = True           # 큐 전부 탐색해야 하므로 꺼낼 때 체크

        if X == K:
            ans = min(ans, t)
            # break                 # ans 계속 업데이트해줘야 하므로 break하지X
        
        # X+1, X-1: 가중치 1
        for nx in (X+1, X-1):
            if 0 <= nx < MAX_SIZE and not visited[nx]:
                # visited[nx] = True
                que.append((nx, t + 1))
        
        # X*2: 가중치 0
        nx = X*2
        if 0 <= nx < MAX_SIZE and not visited[nx]:
            # visited[nx] = True
            que.appendleft((nx, t))

    return ans

###
# 간선의 가중치가 같지 않고, 탐색 시작 지점이 1개이므로 다익스트라(-heapq) 이용할 수도 있음
# 시간복잡도: 3*10^5*5 (최악의 경우)

import heapq

def dijkstra(N, K):

    hq =[]
    heapq.heappush(hq, (0, N))          # time으로 정렬, 현위치 N
    visited = [False] * MAX_SIZE
    visited[N] = True

    while hq:

        t, X = heapq.heappop(hq)

        if X == K:
            print(t)
            break
        
        # X*2: 가중치 0 (먼저 계산)
        nx = X*2
        if 0 <= nx < MAX_SIZE and not visited[nx]:
            visited[nx] = True
            heapq.heappush(hq, (t, nx))


        # X+1, X-1: 가중치 1
        for nx in (X+1, X-1):
            if 0 <= nx < MAX_SIZE and not visited[nx]:
                visited[nx] = True
                heapq.heappush(hq, (t+1, nx))
        
    return


###
print(bfs(N, K))
# dijkstra(N, K)
