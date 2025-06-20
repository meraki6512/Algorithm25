# 16964. DFS 스페셜 저지
# 2s, 512MB

import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())

## set 없이 인덱스를 정렬해서 할 수 있음
# -> O(N log N)  (정렬이 가장 큰 비용)
def sol1():

    graph = [[] for _ in range(N + 1)]
    for _ in range(N - 1):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)

    given = list(map(int, input().split()))

    # 첫 노드가 1이 아니면 무조건 불가능
    if given[0] != 1:
        print(0)
        exit()

    # 주어진 방문 순서를 인덱스화
    pos = [0] * (N + 1)
    for i in range(N):
        pos[given[i]] = i

    # graph 인접 노드들의 순서를 주어진 방문 순서에 맞게 정렬
    for i in range(1, N + 1):
        graph[i].sort(key = lambda x: pos[x])


    # 정렬한 graph에 맞게 dfs 탐색하고, 탐색한 순서대로 result 배열에 담음
    visited = [False] * (N + 1)
    visited[1] = True
    result = []

    def dfs(x):

        result.append(x)

        # 가능한 엣지들에 대해 탐색함
        for nxt in graph[x]:
            if not visited[nxt]:
                visited[nxt] = True
                dfs(nxt)
        
    dfs(1)
    print(1 if result == given else 0)  # 일치하면 1, 불일치하면 0


## set 활용 시
# O(N)  (정렬 없음, set 조회 O(1))
# (이 경우에도 dfs 내에서 계산하는 게 아니라 미리 계산해두고 확인)
def sol2():

    graph = [set() for _ in range(N + 1)]
    for _ in range(N - 1):
        x, y = map(int, input().split())
        graph[x].add(y)
        graph[y].add(x)

    given = list(map(int, input().split()))

    # 첫 노드가 1이 아니면 무조건 불가능
    if given[0] != 1:
        print(0)
        exit()

    # dfs
    idx = 1     # given 배열의 idx

    def dfs(x):
        nonlocal idx

        while idx < N and given[idx] in graph[x]:   # graph[x] set에 현재 값이 있으면 탐색 가능
            nx = given[idx]
            graph[x].remove(nx)                     # 방문한 노드에 대해서는 set에서 제거해줌
            graph[nx].remove(x)
            idx += 1
            dfs(nx)
        
    dfs(1)
    print(1 if idx == N else 0)                     # 탐색 종료했을 때 given 모두 봤다면 1


# sol1()
sol2()