# 32405. 배틀로얄
import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
d = [0] + list(map(int, input().split()))
h = [0] + list(map(int, input().split()))
q = deque(list(range(1, N+1)))

# 배틀 시작
while len(q) > 1:
    
    # 라운드별 남은 플레이어들의 공격력의 총합
    sumd = 0
    
    # 살아남는 플레이어를 먼저 구하고, 그 공격력의 총합을 계산해둠
    for _ in range(len(q)):
        i = q.popleft()

        if h[i] - sumd > 0:         # 앞까지의 플레이어가 모두 공격했을 때 살아남으면
            sumd += d[i]            # 현재 공격력도 추가하고
            q.append(i)             # q 끝에 다시 넣음

    # hp는 이후 처리 
    # : 살아남은 플레이어만 먼저 구해 그 공격력의 총합을 계산한 후 hp 계산함       
    for _ in range(len(q)):
        i = q.popleft()
        h[i] -= (sumd - d[i])       # 현재 플레이어들의 공격력의 총합에서 자기 자신 공격력을 빼고 모두 공격 처리
        
        if h[i] > 0:                # 자기자신을 제외한 모든 플레이어의 공격을 받고 살아남으면
            q.append(i)             # q 끝에 다시 넣음
        
        # 현재 백준에서 이 부분이 없어도 정답 처리되고 있음
        # 아마 테스트 케이스 부족
        if len(q) == 1:             # 모든 플레이어가 동시에 죽는 경우: 그럴 수는 없으니까 마지막 생존자는 살려줘야함
            break

print(q[0])