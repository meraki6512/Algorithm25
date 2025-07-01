# 1863. 스카이라인 쉬운거
# 건물 그림자 - 고도가 바뀌는 지점 x와 고도 y -> 최소 건물 개수는?
'''
1 1
2 2
5 1
6 3
8 1
11 0
15 2
17 3
20 2
22 1
..........................
.....XX.........XXX.......
.XXX.XX.......XXXXXXX.....
XXXXXXXXXX....XXXXXXXXXXXX
'''
# n: 5*10^4, x: 10^6, y: 5*10^5

# input
import sys
input = sys.stdin.readline
n = int(input())
ys = [int(input().split()[1]) for _ in range(n)] + [0] # y만 사용, 0 마진

# solution
ans = 0
stack = []

for y in ys:

    while stack and stack[-1] > y:
        stack.pop()
        ans += 1
    
    while stack and stack[-1] == y:
        stack.pop()
    
    stack.append(y)

# output
print(ans)

