# 28066. 타노스는 요세푸스가 밉다.
# 0.1s, 512MB

# N마리 청설모가 원형 (N: 10^6)
# 1번을 제외하고 2-K번째 청설모가 제거됨 (K: 10^6)
# 최종 청설모 = ?
# in
from collections import deque
N, K = map(int, input().split())
que = deque(list(range(1, N+1)))

# sol
while len(que) > 1:                 # 1마리 남을 때까지 반복

    cur = que.popleft()             # 현재 아이를 기준(1번)으로

    i = 2
    while que and i <= K:           # 2~K번째 아이들을
        que.popleft()               # 없앰
        i += 1

    que.append(cur)                 # 현재 아이를 큐의 끝에 넣음 (순서 꼴등)

# out
print(que.popleft())