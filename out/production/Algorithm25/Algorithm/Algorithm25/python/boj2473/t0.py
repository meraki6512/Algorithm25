# 2473. 세 용액
# https://www.acmicpc.net/problem/2473
# 1s, 256MB

# 용액들의 특성 값이 주어졌을 때 세 개를 혼합해 0에 가장 가까운 용액을 만드는 세 용액을 찾아라.

# 전체 용액 수 N (3 ~ 5000)
# 특성 값 (-10^9 ~ 10^9)

# input
import sys
input = sys.stdin.readline

N = int(input())
solutions = list(map(int, input().split()))
solutions.sort()

# sol
# 두 개를 고정하고 하나를 찾는다. >> N^2 정도
# 최대 N정도
# 합이 0에 젤 가깝게 하는, (e,s)내의 세번째 용액의 값을 구하는 함수
def find_third(sum2, f, r):

    res = solutions[f]              # 업데이트 안되는 경우는 X
    a = abs(sum2 + res)

    for t in range(f+1, r+1):

        vt = solutions[t]
        sum3 = sum2 + vt

        if abs(sum3) < a:
            a = abs(sum3)
            res = vt
    
    return res

# 최대 N정도
s = 0
e = N-1
m = sys.maxsize                     # 업데이트 안되는 경우는 X
while e-s > 1:                      # 용액 최소 3개인 동안

    vs = solutions[s]
    ve = solutions[e]
    vt = find_third(vs + ve, s+1, e-1)   # 합의 abs가 가장 작게 만드는, e-s 내의 세번째 용액의 값

    total = vs + ve + vt            # 선택한 세 용액의 합

    if abs(total) < m:              # 합이 0에 더 가까우면 저장
        ans = [vs, vt, ve]
        m = abs(total)

    if total == 0:                  # 0이면
        break                       # 더이상 탐색할 필요 없음
    elif total > 0:                 # 양수면
        e -= 1                      # 더 작은 값들 중에서 고름
    elif total < 0:                 # 음수면
        s += 1                      # 더 큰 값들 중에서 고름


# output
print(ans[0], ans[1], ans[2])


# ..
# 반례가 있음 - 모든 가능한 조합을 고려하지 않음
# ex) 
# 6
# -4 -6 8 5 -10 -2
# 정답이 -6 -2 8인데, 
# 오답인 -4 -2 5을 출력하게 됨

# 생각해보자.
# -10 -6 -4 -2 5 8 (정렬)
# -10 + 8 = -2라서 중간 값은 5가 될 것임
# -10 + 8 + 5 = 3 > 0라서 e-=1이 되고 8을 제외하게 되니까 그럼 올바른 정답 케이스를 고려하지 X

# -> 하나를 고정하고 나머지를 투 포인터로 찾으면 된다..! N**2