# BOJ 12904 A와 B
# A와 B로만 이뤄진 문자열; 
# S를 T로 바꿀 수 있으면 1 없으면 0을 출력
# 가능한 2가지 연산: 뒤에 A추가 or 뒤집고 뒤에 B추가

# id1. s->t
# dfs, deque
# L2 - L2 loop

# id2. t->s (!)
# deque
# 맨 끝값만 확인한다
# 단 끝값이 B였을 땐 rev = not rev로 한다
# rev == True일 땐 앞에서 맨 끝값만 확인한다


# input
s = input() # 999 
t = input() # 999

# solution
from collections import deque

class Solution:

    def canChange(s, t):
        
        rev = False 
        que = deque(t)

        while que:
            
            cur_str = "".join(que)
            
            # rev 확인해서 값 비교 주의
            if not rev and cur_str == s:
                return True
            elif rev and cur_str == s[::-1]:
                return True
            
            c = que.popleft() if rev else que.pop()

            if c == 'B':
                rev = not rev

        return False
    
# output
ans = 1 if Solution.canChange(s, t) else 0
print(ans)