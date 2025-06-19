# 2461. 대표 선수
# 2s, 256MB

# N개의 학급에 각 M명의 학생이 있고
# 반별 대표를 1명씩 뽑았을 때 
# 최대 능력치 학생에서 최소 능력치 학생을 뺀 그 차이가
# 최소가 되게 하는 경우의 값을 출력하라.

# N, M 1000
# 능력치는 10^9

from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())

# 투포인터 + 그리디
# 각반의 최소 능력치 항생들만 계속 고려하면서 차이의 최솟값을 업데이트

# sol1: deque 기반 -> 시간 초과 나옴
def sol1():

    std = [sorted(map(int, input().split())) for _ in range(N)] # N*MlogM

    ans = sys.maxsize
    std = list(map(deque, std))

    while True:                                         # M

        # 각 반의 최솟값들 중에 최대, 최소인 반을 구함
        _min = _max = 0
        for i in range(1, len(std)):                    # N

            if std[i][0] < std[_min][0]:            
                _min = i
            
            if std[i][0] > std[_max][0]:
                _max = i

        # (최대-최소) 값 차이의 최솟값
        ans = min(ans, std[_max][0]-std[_min][0])   

        # 최소인 반에서 해당 최솟값은 제외해줌
        std[_min].popleft()
        if not std[_min]:   # 제외했을 때 더이상 남은 값이 없다면
            break           # 종료
    
    return ans


# heapq 기반
import heapq

def sol2():

    std = [sorted(map(int, input().split())) for _ in range(N)] # N*MlogM
    
    heap = []
    _max = -1

    # 최솟값들 중 최댓값 구하면서 heap에 넣기
    for i in range(N):
        v = std[i][0]
        heapq.heappush(heap, (v, i, 0))         # 능력치, 반번호, 인덱스
        _max = max(_max, v)

    ans = sys.maxsize

    while True:

        _min, i, j = heapq.heappop(heap)
        ans = min(ans, _max - _min)

        if j + 1 == M:
            break

        # heap에 다음 값 넣고, 최댓값 업데이트
        v = std[i][j + 1]
        heapq.heappush(heap, (v, i, j + 1))     # 해당 반은 최소 능력치 학생 다음으로 
        _max = max(_max, v)

    return ans

print(sol2())