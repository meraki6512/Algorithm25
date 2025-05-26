# 4195. 친구 네트워크
# https://www.acmicpc.net/problem/4195
# T 테스트케이스
# F 친구 관계 수

test_case = int(input())

for t in range(test_case):

    F = int(input())
    friends = [list(input().split()) for _ in range(F)]

    # Hash Map 
    dF = dict()
    d = 0
    edges = []

    for friend in friends:

        if friend[0] not in dF.keys():
            dF[friend[0]] = d
            d += 1
        
        if friend[1] not in dF.keys():
            dF[friend[1]] = d
            d += 1
        
        edges.append([dF[friend[0]], dF[friend[1]]])


    # Find Network

    # x가 어느 집합에 속하는 지 (루트 노드) 반환
    def find(x):
        if (parent[x] == x): 
            return x

        parent[x] = find(parent[x]) # 재귀 줄이기 (경로 압축)
        return x
    
    # a와 b가 속한 두 집합을 하나로 합침
    # 다를 경우에만 루트 노드 값을 전달하며 union 호출하기 때문에 따로 조건 검사 안함
    # rank 계산 (최적화)
    def union(a, b):

        if rank[a] < rank[b]:
            parent[a] = b
        elif rank[b] < rank[a]:
            parent[b] = a
        else: # ==
            parent[a] = b
            rank[b] += 1


    N = len(dF.keys())
    parent = [i for i in range(N)]
    rank = [0] * N

    for edge in edges:

        a = find(edge[0])
        b = find(edge[1])

        if a != b:
            union(a, b) # 루트 노드 값만 전달


# self-feedback
# 친구(friend/edge)가 생길 때마다 네트워크를 구하라고 했음. 한 번에 주어지는 게 아님.
        