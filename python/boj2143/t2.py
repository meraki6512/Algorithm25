# 2143. 두 배열의 합 (another sol: pref, hashMap(Counter)!)
# https://www.acmicpc.net/problem/2143
# 원소가 (-10^6 ~ 10^6) 정수인 배열 A[1]...A[n], B[1]...B[n]
# A의 부분합 + B의 부분합 == T인 모든 부분합 쌍의 개수

# input
T = int(input()) # 20억개
n = int(input()) # 1000
A = list(map(int, input().split()))
m = int(input()) # 1000
B = list(map(int, input().split()))

# solution
ans = 0
pA = [0] * (n+1)
pB = [0] * (m+1)

for i in range(n): 
    pA[i+1] = pA[i] + A[i]  # A의 부분합
for i in range(m):
    pB[i+1] = pB[i] + B[i]  # B의 부분합

# sA: pA의 모든 i,j 부분합
# sB: pB의 모든 i,j 부분합
sA = []
sB = []

for i in range(n): # n^2 (10^6)
    for j in range(i+1, n+1):
        sA.append(pA[j]-pA[i]) # pA간 차이 넣음

for i in range(m): # m^2 (10^6)
    for j in range(i+1, m+1):
        sB.append(pB[j]-pB[i]) # pB간 차이 넣음

# 해시 맵 collections.Counter 활용! 
# Counter: dict 기반 + 자동 개수 세기 기능이 있는 빈도 해시맵 (우왕왕)
# sB를 미리 Counter로 만들어서 B의 부분합이 각각 몇 번 나오는지 저장해둠
from collections import Counter
counterB = Counter(sB)

ans = 0
for a in sA:
    ans += counterB[T-a]

print(ans)