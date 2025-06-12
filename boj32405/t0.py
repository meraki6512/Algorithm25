# 32405. 배틀로얄
from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
d = list(map(int, input().split()))
h = list(map(int, input().split()))
q = deque(list(range(N)))               # idx

# 공격력에 비해 너무 체력이 많으면 한번 줄여줌 (생략 가능)
sumd = sum(d)
for _ in range(N):
    i = q.popleft()

    if h[i] % (sumd-d[i]):
        q.append(i)
    
    h[i] %= (sumd-d[i])

# 배틀로얄 시작
while len(q) > 1:
    
    sumd = 0                        

    # 현재 남은 플레이어들의 총 공격량 구하면서 0보다 같거나 작아지면 제외
    for _ in range(len(q)):

        i = q.popleft()

        h[i] -= sumd

        if h[i] > 0:            # 내 앞순서의 공격을 모두 받았을 때 살아남으면
            sumd += d[i]        # 내 공격도 추가하고
            q.append(i)         # 살아남음        


# 인덱스 출력 (1부터)
print(q[0] + 1)


# -> 1번 플레이어는 절대 공격을 안 받게 됨..