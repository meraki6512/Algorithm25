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
        h[i] -= sumd            # 내 앞순서의 공격을 모두 받았을 때

        if h[i] > 0:            # 살아남으면
            sumd += d[i]        # 내 공격량 추가하고
            q.append(i)         # 큐 끝에 넣음

        if len(q) == 1:         # 한 번에 모든 q가 다 사라지는 경우를 방지 (모든 플레이어의 hp가 모두 0보다 작거나 같아지는 경우)
            break
    
    # sumd를 초기화하기 전에 첫 번째 플레이어도 때려줘야 함
    if len(q) > 1:
        i = q[0]                # 첫번째 플레이어
        h[i] -= (sumd - d[i])   # 내 뒷순서의 공격을 모두 받음

        if h[i] <= 0:           # 죽었으면
            q.popleft()         # 뺌


# 인덱스 출력 (1부터)
print(q[0] + 1)



# 모르겠다..
# 그냥 공격량을 추가하는 부분과 플레이어 체력을 깎는 부분은 따로 분리하는 게 편하겠다.
# -> t2