# 1966. 프린터 큐
# 2s, 128MB

# 우선 순위가 있는 문서들의 인쇄를 할 때
# 어떤 문서가 몇 번째로 인쇄되는가

# 문서의 개수 N (100)
# 어떤 문서의 위치 M (N-1)
# 문서의 중요도 (9)

from collections import deque
import sys
input = sys.stdin.readline
test_case = int(input())

for _ in range(test_case):

    # input 
    N, M = map(int, input().split())
    prior = list(map(int, input().split()))

    q = deque([(prior[i], i) for i in range(N)])

    # sol
    ans = 0

    while q:

        max_q_prior, _ = max(q)         # max 찾기
        c_prior, idx = q[0]

        if max_q_prior > c_prior:       # 더 큰 게 있으면
            q.append(q.popleft())       # 맨 뒤에 재배치

        elif max_q_prior == c_prior:    # 더 큰 게 없으면
            q.popleft()                 # 바로 인쇄
            ans += 1

            if idx == M:                # 인쇄한 인덱스가 M과 같다면
                break                   # 종료

    print(ans)