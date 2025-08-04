# 9466. 텀 프로젝트
# 3s, 256MB

# 1~n번 학생
# 각 학생이 자신이 팀으로 원하는 한 사람을 선택 (혼자하고 싶으면 자기자신 고름)
# 팀을 못 이루는 학생 수 = ?

import sys
import time
input = sys.stdin.readline
T = int(input())

# cycle이 생기는 곳에 팀이 생김
# 각 상태에서 visited를 하면서 사이클이 생기는 지를 체크 ... union-find?
# 이미 팀을 이뤘으면 검사를 안해도 될듯 (총 n번만)

for _ in range(T):

    ans = 0
    n = int(input())
    pick = [-1] + list(map(int, input().split()))
    notGrouped = [0] + [1] * n

    # union-find 
    root = [pick[i] for i in range(n+1)]

    def find(x):

        if x < root[x]:                  # root에 도달하지 않은 상태면
            root[x] = find(root[x])       # root 찾아서 경로 압축

        return root[x]

    def union(x, y):
        
        px = find(x)
        py = find(y)

        if px == py:                        # 여기서 사이클 부분은 어떻게 제거하지?
            if x < y:
                root[y] = x
            else:
                root[x] = y

    # 같은 팀끼리 먼저 구분해두고
    for i in range(1, n+1):
        union(i, root[i])

    # 팀 이룬 경우는 제거해주고 합을 구함
    for i in range(1, n+1):

        # 자기 자신을 선택한 경우 (무조건) or 남을 선택한 경우 (그 학생과 같은 부모루트를 가질 때)
        if i == pick[i] or root[i] == root[pick[i]]:
            notGrouped[i] = 0     

    print(sum(notGrouped))


# union-find로는 사이클을 체크하기 어렵다
# ... -> 다른 방법을 찾아보자