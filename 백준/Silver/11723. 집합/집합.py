# 11723. 집합
# 1.5s, 4MB

# bitmask로 구해보기

import sys
input = sys.stdin.readline

M = int(input())            # 3*10^6
S = 0                       # 20

for i in range(M):

    op = input().strip()

    if op == "all":
        S = -1              # 모든 비트 1로 설정
    
    elif op == "empty":
        S = 0               # 모든 비트 0으로 설정
    
    else:
        f, x = op.split()
        x = int(x)

        if f == "add":
            S |= (1<<x)
        
        elif f == "remove":
            S &= ~(1<<x)        # 해당 비트 0으로 설정 

        elif f == "check":
            print(int((S & (1<<x)) >= 1))

        elif f == "toggle":
            S ^= (1<<x)     