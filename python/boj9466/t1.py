# 9466. 텀 프로젝트
# 3s, 256MB

# 1~n번 학생
# 각 학생이 자신이 팀으로 원하는 한 사람을 선택 (혼자하고 싶으면 자기자신 고름)
# 팀을 못 이루는 학생 수 = ?

import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline
T = int(input())

# 방문을 기록하면서 사이클이면 한 팀으로 엮고 계산
def dfs(x):

    global notGrouped

    visited[x] = True           # 방문 체크
    nx = pick[x]                # x가 선택한 학생 nx에 대해서...

    if not visited[nx]:         # 방문하지 않았다면
        dfs(nx)                 # 할 수 있는 데까지 방문
    
    elif not search_done[nx]:   # 방문을 했었는데, dfs 탐색을 아직 종료한 게 아니라면 (사이클)

        while nx != x:          # 해당 사이클 전부
            notGrouped -= 1     # 한 팀으로 계산
            nx = pick[nx]       
        
        notGrouped -= 1         # 자기 자신(nx == x인 경우)까지 계산

    # dfs 탐색이 끝나면 search_done 체크 (사이클 감지용)
    search_done[x] = True    


# testcase에 대해 dfs 실행 후 바로 출력
for _ in range(T):

    ans = 0
    n = int(input())
    pick = [-1] + list(map(int, input().split()))
    notGrouped = n
    visited = [False] * (n + 1)
    search_done = [False] * (n + 1)

    # 모든 학생에 대해 최소 한 번씩 탐색
    for i in range(1, n+1):
        if not visited[i]:          # 이미 방문했었다면 제외 (팀을 이뤘는 지 아닌 지 확인을 이미 한 상태)
            dfs(i)
    
    print(notGrouped)