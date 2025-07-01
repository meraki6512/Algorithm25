# 7453. 합이 0인 네 정수
# https://www.acmicpc.net/problem/7453
# 12s, 1024MB

# 크기가 같은 배열 4개 (A, B, C, D)
# 배열의 크기 n (4000) 

# 합이 0이 되는 쌍의 개수를 출력하라.

# input
import sys
input = sys.stdin.read
data = list(map(int, input().split()))
n = data[0]
nums = data[1:]
A, B, C, D = nums[::4], nums[1::4], nums[2::4], nums[3::4]
# n = int(input())
# A, B, C, D = [], [], [], []
# for i in range(n):
#     a, b, c, d = map(int, input().split())
#     A.append(a)
#     B.append(b)
#     C.append(c)
#     D.append(d)

# sol
# A+B+C+D = 0 <-> A+B=-(C+D)
from collections import Counter
AB = Counter()
for a in A:                     # n^2
    for b in B:
        AB[a+b] += 1

# -(C+D) 값 중에 (A+B)와 같은 값이 있으면 그 개수만큼 더해줌
ans = 0
for c in C:                     # n^2
    for d in D:
        ans += AB[-(c+d)]

print(ans)


# 다시 풀기