# 15686. 치킨 배달
# 1초, 512MB

# N*N 도시 (2<=N<=50)
# 각 칸: {빈칸0, 집1, 치킨집2} 중 하나
# 각 칸: (row, col) 형태로 나타냄 / row=col=1부터 시작
# 
# "치킨거리" = 집과 가장 가까운 치킨집 사이의 거리
# 각각의 집은 치킨거리를 가지고 있음
# 도시의 치킨거리는 모든 집의 치킨거리의 합
# 
# 임의의 두 칸 (r1, c1), (r2, c2) 사이의 거리 = |r1-r2| + |c1-c2|
#
# 도시의 치킨 거리가 가장 작게되는, 수익이 가장 많을 치킨집의 개수 M (1<=M<=13)
# M개를 제외하고는 모두 폐업시켰을 때, 도시의 치킨 거리의 최솟값을 구하라.
#
# 1 <= 집의 개수 <= 2N
# M <= 치킨집의 개수 <= 13

# [1] input
N, M = map(int, input().split())
city_map = [list(map(int, input().split())) for _ in range(N)]
# == [list(map(int, sys.stdin.readline().split())) for _ in range N]

# aprch1.
# 각각의 치킨거리를 구하기 
# dfs # each_chicken_dist_map = [[]]
# 모든 경우 계산해 비교

# aprch2. (adopted)
# M개의 치킨집 선택 -> "조합" nCr
# M개의 치킨집을 뽑고, 최소치킨거리합을 구함
# 반복하며 가장 작은 최소치킨거리합을 구함
# dfs, next_permutation/combinations
# backtracking

# [2] solution

# 1. 집, 치킨집 위치 구하기 (거리계산용)
house_list = []
chicken_list = []

for r in range(N): # 50
    for c in range(N): # 50
        if city_map[r][c] == 1: # 집 발견
            house_list.append([r, c])
        elif city_map[r][c] == 2: # 치킨집 발견
            chicken_list.append([r, c])


# 2. 치킨집 중에서 M개의 치킨집 선택하기
INF = 1e9 # float('inf')
min_city_chicken_dist = INF

# 2-1
# 최소 도시치킨거리를 찾는 메소드
def find_min_city_chicken_dist():

    global min_city_chicken_dist

    sum_min_dist = 0
    for hx, hy in house_list: # 특정 집에서
        
        min_dist = INF
        for i in survived_chicken_list: # (남은 치킨집들 중)
            
            cx, cy = chicken_list[i] # 특정 치킨집까지의
            dist = abs(hx-cx) + abs(hy-cy)
            min_dist = min(min_dist, dist) # 최소거리 (== 집별치킨거리)

        sum_min_dist += min_dist #의 합 (== 도시치킨거리)
    
    min_city_chicken_dist = min(min_city_chicken_dist, sum_min_dist) # 도시치킨거리 중 최소값

    return

# 2-1. survived_chicken_list를 전역 변수로 사용
survived_chicken_list = [] # 선택된 치킨집 수

def back_tracking(i, cnt): #현재 치킨집 인덱스, 지금까지 선택한 치킨집 수
    
    # M개를 선택완료한 경우:
    if cnt == M:
        find_min_city_chicken_dist() # 최소값 갱신 (조합 찾을 때마다 즉시 갱신함)
        return
    
    # 인덱스 초과 방지 조건
    if i >= len(chicken_list): 
        return

    # 아직 M개를 다 선택하지 못했을 때: 
    # - 현재 치킨집을 선택할 경우
    survived_chicken_list.append(i)
    back_tracking(i+1, cnt+1)
    # 
    # - 현재 치킨집을 선택 취소할 경우 (선택하지 않은 경우, 개수 늘리지 않고 다음으로 이동만)
    survived_chicken_list.pop()
    back_tracking(i+1, cnt)

    return 

# 2-2
# 최소 도시치킨거리를 찾는 메소드
def find_min_city_chicken_dist_with_visited():

    global min_city_chicken_dist

    sum_min_dist = 0
    for hx, hy in house_list: # 특정 집에서
        
        min_dist = INF
        for i in range(len(chicken_list)): # (치킨집들 중)
            if visited[i]: # (선택한 치킨집이라면)

                cx, cy = chicken_list[i] # 특정 치킨집까지의
                dist = abs(hx-cx) + abs(hy-cy)
                min_dist = min(min_dist, dist) # 최소거리 (== 집별치킨거리)

        sum_min_dist += min_dist #의 합 (== 도시치킨거리)
    
    min_city_chicken_dist = min(min_city_chicken_dist, sum_min_dist) # 도시치킨거리 중 최소값

    return

# 2-2. 또다른 풀이: 선택한 치킨집을 visited로 관리
# 굳이 리스트를 만들지 않고, 거리만 계산하기 위함
visited = [False] * len(chicken_list)

def dfs(i, cnt):

    # M개를 선택완료한 경우:
    if cnt == M:
        find_min_city_chicken_dist_with_visited() # 최소값 갱신 (조합 찾을 때마다 즉시 갱신함)
        return
    
    # visited 체크
    for rc in range(i, len(chicken_list)):

        # 아직 방문하지 않은 치킨집이면 선택
        if not visited[rc]:
            visited[rc] = True

            # 이동
            dfs(rc+1, cnt+1)

            # 백트래킹: 원상 복구 (선택 취소)
            # 조합을 만들어내야하기 때문에 
            # 완성한 이후에는 다시 복구해줘야 새로운 조합을 더 찾을 수 있음
            visited[rc] = False



# 2-3. 또다른 풀이: itertools.combinations 이용
# combinations(iterable, r): r짜리 모든 조합 반환 (사전순 정렬 순서로)
# 조합의 개수 = nCr = n!/(r!*(n-r)!)
# 문제의 최대 치킨집 수 <= 13이므로 최대 13C6 (=1716)
from itertools import combinations

def comb():

    global min_city_chicken_dist
    
    # 치킨집 M개 조합 생성
    for chicken_comb in combinations(chicken_list, M):

        # 조합 중 도시치킨거리가 가장 작은 값을 찾음
        sum_min_dist = 0
        for hx, hy in house_list:

            dist = min(abs(hx - cx) + abs(hy - cy) for cx, cy in chicken_comb)
            sum_min_dist += dist

        min_city_chicken_dist = min(min_city_chicken_dist, sum_min_dist)


# [3] output
back_tracking(0, 0) # 또는 dfs(0, 0) # 또는 comb()
print(min_city_chicken_dist)



# self-feedback
# 거의 새롭게 공부하면서 풀었음
# 나중에 다시 스스로 풀기
# 백트래킹 다른 문제들 연습하기