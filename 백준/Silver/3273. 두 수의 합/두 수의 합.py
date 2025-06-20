# 3273. 두 수의 합
# 1s, 128MB

import sys
input = sys.stdin.readline

n = int(input())
nums = sorted(list(map(int, input().split())))
x = int(input())

s = 0
e = n-1
ans = 0
while s < e:

    key = nums[s] + nums[e]

    if key == x:
        ans += 1
        s += 1
    
    elif key < x:
        s += 1
    
    elif key > x:
        e -= 1

print(ans)