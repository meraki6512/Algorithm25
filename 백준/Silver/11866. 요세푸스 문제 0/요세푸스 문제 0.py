# 11866. 요세푸스 문제 0
# 2s, 512MB

# 요세푸스 순열이란?
# 1부터 N번까지 N명의 사람이 원을 이루며 앉아있고, 양의 정수 K가 주어질 때, (N, K (1000))
# 순서대로 다음 K번째 사람을 제거하며 모두 제거하는 순서.

from collections import deque
N, K = map(int, input().split())

q = deque(list(range(1, N+1)))
ans = []

while q:

    for i in range(K-1):
        q.append(q.popleft())
    
    ans.append(q.popleft())

ans = ", ".join(map(str, ans)).strip()
print("<" + ans + ">")



# e.g. (7, 3) Jsps = <3, 6, 2, 7, 5, 1, 4>
# 1234567
# _456712
# __71245 ...

# (이번sf코테 1번과 유사해서 풀어봄)