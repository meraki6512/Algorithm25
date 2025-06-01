# 2504. 괄호의 값
# () -> 2, [] -> 3
# 올바르지 못하면 0
 
# (()[[]])([]) = {2 + 3 * 3} * 2 + 2 * 3 = 28
# 1 sec, 128 MB
# string 1~30

def convert(c):

    if c in "()":
        return 2
    
    elif c in "[]":
        return 3
    

import sys
input = sys.stdin.readline
string = list(input())

# 여는 괄호 (면 *2저장, [면 *3저장
# 닫는 괄호 ans += pop 
# (()[[]]) -> 2*2 + 2*3*3 = 22
# ([]) -> 2*3 = 6

stack = []
ans = 0
result = 0 

for c in string:

    n = convert(c)

    if c in "([":

        stack.append(n)
        
        t = 1
        for i in range(len(stack)):
            t *= stack[i]

        result = t if not result else result * n

    elif c in ")]":

        if (not stack) or stack.pop() != n:
            ans = 0
            break
        
        ans += result
        result = 0

ans = 0 if stack else ans
print(ans)
