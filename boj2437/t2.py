# 2437. 저울
# https://www.acmicpc.net/problem/2437
# apprch3. combination

# input
N = int(input()) #1000
weights = list(map(int, input().split()))
weights.sort()

# solution
# 간단하게 생각해보자.
# 굳이 연속해서 고를 필요가 없다. (부분합 X)
# 조합을 사용하면 될 것이다.
from itertools import combinations 
# 128MB 메모리 제한,    -> 메모리 초과
# 최대 1000!/500!500!  -> 시간 초과

result = []
for i in range(1, N+1): 
    combs = list(map(sum, combinations(weights, i))) 
    result += combs

result = list(set(result))
result.sort()

# ouput
ans = result[-1] + 1
for i in range(1, ans):
    if i != result[i-1]:
        ans = i
        break

print(ans)