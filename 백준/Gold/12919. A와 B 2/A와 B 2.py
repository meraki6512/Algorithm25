# BOJ 12919 A와 B 2
# A와 B로만 이뤄진 문자열; 
# S를 T로 바꿀 수 있으면 1 없으면 0을 출력
# 가능한 2가지 연산: 뒤에 A추가 or "뒤에 B추가 후 뒤집기"

# t->s (!)
# deque
# pop() -> A check
# popleft() -> B check, rev


# input
s = input() # 49
t = input() # 50

# solution
from collections import deque
import time

class Solution:

    def canChange(s, t):

        cur = deque(t)
        que = [cur]

        while que: # Lt - Ls # 50
 
            cur = que.pop()
            if not cur:
                continue

            if "".join(cur) == s:
                return True
        
            if cur[0] == 'B':
                cur.popleft()
                que.append(deque(reversed(cur))) # 50
                cur.appendleft('B')

            if cur[-1] == 'A':
                cur.pop()
                que.append(cur.copy()) # 50 # 주의 Call by assignment
                cur.append('A') # X
            
        return False

# output
ans = 1 if Solution.canChange(s, t) else 0
print(ans)