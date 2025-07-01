# 15961. 회전초밥
# 1s, 512MB

# 회전 초밥
# 이벤트
# 1. k개의 접시를 연속해서 먹으면 할인
# 2. 1번을 할 경우, c번 초밥 하나 무료 (벨트에 없으면 새로 만들어줌)

# 이때 먹을 수 있는 초밥 가짓수의 최댓값을 구하라.

# 접시 수 N        3*10^6
# 초밥의 가짓수 d   3*10^3

import sys
input = sys.stdin.readline
N, d, k, c = map(int, input().split())
belt = [int(input()) for _ in range(N)]

# 첫번째 슬라이드만 먼저 계산
kind = [0] * (d + 1)     
kind_cnt = 0            # 슬라이드 내의 종류 개수

for i in range(k):

    key = belt[i]

    if not kind[key]:       # 해당 종류를 추가하는 게 처음이면
        kind_cnt += 1       # 가짓수 추가

    kind[key] += 1

isCInbelt = 0 if kind[c] else 1     # c(무료 초밥 번호)가 현재 슬라이드에 있는지 체크 (O: 가짓수 그대로, X: 가짓수++)

# N번 (슬라이드) 순회
ans = kind_cnt + isCInbelt
for i in range(N):

    # 슬라이드 처음
    s = belt[i]               
    kind[s] -= 1            # 슬라이드 처음 종류를 제외했을 때

    if not kind[s]:         # 그 종류가 더이상 남아있지 않다면
        kind_cnt -= 1       # 가짓수 하나 줄임

    # 슬라이드 끝
    e = belt[(i + k) % N]   # 끝에서 하나 더 이동한 것 (%: 범위 넘어서면 0부터)
    
    if not kind[e]:         # 그 종류가 처음이라면
        kind_cnt += 1       # 가짓수 하나 늘림
    
    kind[e] += 1  

    # 슬라이드마다 가짓수 비교
    isCInbelt = 0 if kind[c] else 1     # c(무료 초밥 번호)가 현재 슬라이드에 있는지 체크 (O: 가짓수 그대로, X: 가짓수++)
    ans = max(ans, kind_cnt + isCInbelt)


# output
print(ans)