# 수들의 합 2
# N개의 수로 된 수열에서, i-j 부분합이 M이되는 경우의 수를 구하라.

# 0.5sec, 128MB
# N 10^4

# input
N, M = map(int, input().split())
A = list(map(int, input().split()))

# solution
# prefix sum
p = [0] * (N+1)             # N+1
for i in range(N):
    p[i+1] = p[i] + A[i]

# dict counter (p)      
from collections import Counter
d = Counter(p)              # N+1
    
# find in dict
ans = 0
for i in range(N+1):        # N+1
    key = M + p[i]

    if key in d.keys():

        if d[key] == 1:
            del d[key]
        else:
            d[key] -= 1

        ans += 1

print(ans)


# self-feedback
# 굿!