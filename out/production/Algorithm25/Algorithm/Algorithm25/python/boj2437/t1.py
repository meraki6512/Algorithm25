# 2437. 저울
# https://www.acmicpc.net/problem/2437
# apprch2. prefix_sum

# input
N = int(input()) #1000
weights = list(map(int, input().split()))
weights.sort()

# 간단하게 생각해보자.
# 부분합
p = [0] * (N+1)
for i in range(N):
    p[i+1] = p[i] + weights[i]

# 모든 부분합 -> 10^6
s = set([])
for i in range(N):
    for j in range(i+1, N+1):
        s.add(p[j]-p[i])

result = list(s)
result.sort()

# 양의 정수 1부터 확인
ans = result[-1] + 1
for i in range(1, len(result)+1):
    if i != result[i]:
        ans = i
        break

print(ans)