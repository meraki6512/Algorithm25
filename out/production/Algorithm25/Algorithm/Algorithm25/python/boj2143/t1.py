# 2143. 두 배열의 합 (pref, bisect!)
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

for i in range(0, n): 
    pA[i+1] = pA[i] + A[i]  # A의 부분합
for i in range(1, m):
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


# 모든 부분합을 다 구했으니 이제 조합을 찾을 차례다.
# T - sA 값이 sB에 몇 개 있나 탐색 -> bisect!
# bisect: 이진탐색으로, 주어진 원소가 들어갈 위치 idx를 리턴함. (left면 기준 왼편, right면 기준 오른편)
import bisect

ans = 0
sB.sort() # 이진 탐색을 하기 위해 정렬
for a in sA:
    key = T-a
    ans += bisect.bisect_right(sB, key) - bisect.bisect_left(sB, key) 

print(ans)

# self-feedback
# pref_sum 구할 때 [0]을 마진으로 두면 계산할 때 훨씬 편하고 활용도가 좋음.
# bisect lib 연습 더 하기
    
# ex1)
# A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우
# pA = {0, 1, 4, 5, 7}, pB = {0, 1, 4, 6}
# sA = {1, 4, 5, 7, 3, 4, 6, 1, 3, 2}
# sB = {1, 4, 6, 3, 5, 2}
# T-sA = {4, 1, 0, -2, 2, 1, -1, 4, 2, 3}
# sorted(sB) = {1, 2, 3, 4, 5, 6} # (B의 원소가 음수일 수 있음)

# ex2)
# T-sA = {3, 3, 1, 4, 2}
# sorted(sB) = {2, 2, 2, 3, 4}
# ans = 1 + 1 + 0 + 1 + 3 = 6