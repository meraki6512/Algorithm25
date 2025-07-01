# 1707. 이분 그래프
# https://www.acmicpc.net/problem/1707
# 2s, 256MB

# 이분 그래프란 정점의 집합을 둘로 분할했을 때 같은 집합 내의 정점은 연결되지 않는 그래프
# 그래프가 이분 그래프인지 판별하라.
# K: tc (5)
# V: 정점 (2*10^4)
# E: 간선 (2*10^5)

import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
K = int(input())

def dfs(v, c):

    global is_bi

    color[v] = c

    for u in adj[v]:

        if not color[u]:            # 아직 미방문이면 계속 탐색
            dfs(u, -c)              # 색깔은 반대 (인접했으니까)

        elif color[u] == color[v]:  # 방문했는데, 색깔이 같으니까
            is_bi = False           # 이분 그래프 아님
            return                  # 종료

for _ in range(K):

    V, E = map(int, input().split())
    adj = [[] for _ in range(V + 1)]
    for _ in range(E):
        x, y = map(int, input().split())
        adj[x].append(y)
        adj[y].append(x)

    is_bi = True
    color = [0] * (V + 1)       # 0은 미방문, 1/-1은 색깔

    for i in range(1, V+1):
        if not color[i]:        # 아직 색칠X (미방문)
            dfs(i, 1)

            if not is_bi:       # 탐색 후 False면 더이상 탐색하지않고 즉시 종료
                break

    print("YES" if is_bi else "NO")


# ..
# 스터디원 설명으로는 bfs, color 개념 이용하는 방식도 가능하더라...
# 사실 그게 더 편할 수도 있어.