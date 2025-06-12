import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
attacks = [0] + list(map(int, input().split()))
healths = [0] + list(map(int, input().split()))

q = deque()
for i in range(1,n+1):
    q.append(i)

while len(q) > 1:
    attacks_sum = 0
    q_len = len(q)
    
    for _ in range(q_len):
        i = q.popleft()
        if healths[i] - attacks_sum >0:
            attacks_sum += attacks[i]
            q.append(i)
            
    q_len = len(q)
    for _ in range(q_len):
        i = q.popleft()
        healths[i] -= (attacks_sum - attacks[i])
        
        if healths[i] >0:
            q.append(i)

print(q[0])