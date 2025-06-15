# 22234. 가희와 은행
# 1.5s, 512MB

# 고객
# (0초) N명 대기 중 : x번 손님 id값 px, 업무 처리 시간 tx
# (1초) M명 추가로  : x번 손님 id값 px, 업무 처리 시간 tx, 영업 시작 cx초 후에 들어옴
# 
# 직원
# T초간 하나의 업무 처리 (T초 전에 한 손님의 업무가 모두 처리되지 않으면 손님은 맨 뒤로 이동)
#
# W-1초가 지날 때까지 처리한 모든 업무의 고객 id를 출력하라.

# N, M, T, W: 2*10^5
# 대기 큐가 비어 있는 경우는 존재하지 X
# px, tx, cx: 10*9
# cx는 중복되지 X (같은 시간에 동시에 여러 명이 들어오지 X)

# input
from collections import deque
import sys
input = sys.stdin.readline

N, T, W = map(int, input().split())
que = [list(map(int, input().split())) for _ in range(N)]
M = int(input())

late = dict()
for _ in range(M):
    px, tx, cx = map(int, input().split())
    late[cx] = [px, tx]                     # 중복 X니까 바로 넣어줌

que = deque(que)

# sol
# px를 num초 동안 응대함 출력
def print_px(px, num):
    for _ in range(num):
        print(px)

# 응대 후에 다시 대기하는 손님보다는 먼저 도착하는 것으로 처리함
def check_late(frm, to):
    for k in range(frm, to + 1):
        if k in late.keys():                    # 오픈 후 오는 손님 처리
            que.append(late[k])   

t = 0
while True:          

    # 큐가 비어있는 경우는 존재X -> 고려X
    px, tx = que.popleft()              # 응대할 손님 선택

    if t + min(tx, T) > W-1:            # 다음 시점이 검사할 시점보다 넘어간다면
        print_px(px, W-t)               # 남은 시점까지만 응대하고
        break                           # 종료

    elif tx <= T:                       # 손님의 처리 시간 tx가 더 짧거나 같다면
        check_late(t + 1, t + tx)       # 다음부터 tx 더한 시점까지 오는 손님을 que에 넣고
        print_px(px, tx)                # tx만큼만 응대하고 손님은 나감 (큐에 추가X)
        t += tx

    else:                               # 직원의 처리 시간 T가 더 크면
        check_late(t + 1, t + T)        # 다음부터 T 더한 시점까지 오는 손님을 que에 넣고
        print_px(px, T)                 # T만큼만 응대하고
        que.append([px, tx - T])        # 손님은 다시 끝에서 기다림
        t += T
    