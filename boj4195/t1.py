# 4195. 친구 네트워크
# https://www.acmicpc.net/problem/4195
# T 테스트케이스
# F 친구 관계 수

from collections import Counter

# Union-Find
# x가 어느 집합에 속하는 지 (루트 노드) 반환
def find(x):
    if (parent[x] == x): 
        return x

    parent[x] = find(parent[x]) # 재귀 줄이기 (경로 압축)
    return parent[x]

# a와 b가 속한 두 집합을 하나로 합침
# 다를 경우에만 루트 노드 값을 전달하며 union 호출하기 때문에 따로 조건 검사 안함
# rank 계산 (최적화)
def union(x, y):

    a = find(x)
    b = find(y)

    if a == b:
        # return max(ans) # 전체 네트워크를 구하라는 게 아님!!!
        # 현재 주어진 두 개의 노드의 네트워크를 구해야 함
        return ans[a]

    if rank[a] < rank[b]:
        parent[a] = b
        ans[b] += ans[a]
        return ans[b]

    elif rank[b] < rank[a]:
        parent[b] = a
        ans[a] += ans[b]
        return ans[a]

    else: # rA == rB
        parent[a] = b
        rank[b] += 1
        ans[b] += ans[a]
        return ans[b]
    


test_case = int(input())

for t in range(test_case):

    F = int(input())
    parent, ans, rank = {}, {}, {}

    for _ in range(F):
        x, y = input().split()

        if x not in parent:
            parent[x] = x
            ans[x] = 1
            rank[x] = 0
        
        if y not in parent:
            parent[y] = y
            ans[y] = 1
            rank[y] = 0

        print(union(x, y))


# self-feedback
# 친구(friend/edge)가 생길 때마다 네트워크를 구하라고 했음. 한 번에 주어지는 게 아님.
# union find 더 연습해야겠다.. 헷갈려!
# 나중에 다시 풀기