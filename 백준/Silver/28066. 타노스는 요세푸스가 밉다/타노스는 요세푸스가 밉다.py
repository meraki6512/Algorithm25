# 28066. 타노스는 요세푸스가 밉다.
# 0.1s, 512MB

# N마리 청설모가 원형 (N: 10^6)
# 1번을 제외하고 2-K번째 청설모가 제거됨 (K: 10^6)
# 최종 청설모 = ?
from collections import deque
N, K = map(int, input().split())
que = deque(list(range(1, N+1)))

while len(que) > 1:

    cur = que.popleft()

    i = 2
    while que and i <= K:
        que.popleft()
        i += 1

    que.append(cur)

print(que.popleft())