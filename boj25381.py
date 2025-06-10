# 25381. ABBC
# 3s, 1024MB

# A, B, C로 이뤄진 문자열 S
# A와 그 뒤쪽에 나오는 B를 지우거나
# B와 그 뒤쪽에 나오는 C를 지우거나
# 최대 시행 횟수 = ?

# S: 3*10^5

# ABCBA -> CBA (1)
# ABCBA -> BCA -> A (2)
# B는 A의 영향을 받고, C는 B의 영향을 받음
# C부터 처리해야함

from collections import deque
import sys
input = sys.stdin.readline
S = deque(list(input()))

ans = 0
Aq = deque()
Bq = deque()

# C와 관련된 B만 먼저 처리
for i, c in enumerate(S):

    if c == 'A':
        Aq.append(i)

    elif c == 'B':
        Bq.append(i)

    elif c == 'C':
        if Bq and Bq[0] < i:
            Bq.popleft()
            ans += 1

# A, B 처리
while Aq and Bq:

    # A..B인 경우
    if Aq[0] < Bq[0]:
        Aq.popleft()
        Bq.popleft()
        ans += 1
    
    # B..A인 경우
    else:
        Bq.popleft()

print(ans)


# sf
# 스터디 팀원 문제를 설명을 듣고 풀어서 너무 간단하게 해결해버렸지만
# 내가 풀은 느낌은 아니다
# 나중에 기억이 까마득해질 무렵 다시 풀어봅세