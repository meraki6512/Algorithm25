# 2143. 두 배열의 합 (실패!)
# 원소가 정수인 배열 A[1]...A[n], B[1]...B[n]
# A의 부분합 + B의 부분합 == T인 모든 부분합 쌍의 개수

# input
T = int(input()) # 20억개
n = int(input()) # 1000
A = list(map(int, input().split()))
m = int(input()) # 1000
B = list(map(int, input().split()))

# solution
ans = 0
pA = [A[0]] + [0] * (n-1)
pB = [B[0]] + [0] * (m-1)

for i in range(1, n): 
    pA[i] = pA[i-1] + A[i]  # A의 부분합
for i in range(1, m):
    pB[i] = pB[i-1] + B[i]  # B의 부분합

# sA: pA의 모든 i,j 부분합
# sB: pB의 모든 i,j 부분합
sA = [A[0]]
sB = [B[0]]

for i in range(n-1): # n^2 (10^6)
    for j in range(i+1, n):
        sA.append(pA[j]-pA[i]) # pA간 차이 넣음

for i in range(m-1): # m^2 (10^6)
    for j in range(i+1, m):
        sB.append(pB[j]-pB[i]) # pB간 차이 넣음

# 원소별로 몇개씩 있나 세기
dA = dict()
for i in range(n):

    if sA[i] >= T:
        continue

    if sA[i] not in dA.keys():
        dA[sA[i]] = 1
    else:
        dA[sA[i]] += 1

dB = dict()
for i in range(m):

    if sB[i] >= T:
        continue

    if sB[i] not in dB.keys():
        dB[sB[i]] = 1
    else:
        dB[sB[i]] += 1

# setA와 setB 중에 조합해서 합이 T가 되는 경우 세기
from itertools import combinations

setA = list(set(sA))
setB = list(set(sB))
N = min(len(setA), len(setB)) # 10^6

for r in range(1, N):
    combA = list(combinations(setA, r))   
    combB = list(combinations(setB, N-r))

    # combA = [[1, 2], ...]
    # combB = [[1, 2], ...]
    # give up!

print(ans)

# 1312 / 1457 / 3461312 -> 13461312 -> 11123346 12346 5Cr
# 132 / 146 / 352 -> 1352 -> 1235 1235 4CT-r

# 1 / 1 + 3
# 1 + 3 / 1
# 3 / 2
# 3 + 1 / 1
# 1 / 1 + 3
# 1 + 2 / 2
# 2 / 3
