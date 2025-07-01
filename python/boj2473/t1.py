# 2473. 세 용액
# https://www.acmicpc.net/problem/2473
# 1s, 256MB

# 용액들의 특성 값이 주어졌을 때 세 개를 혼합해 0에 가장 가까운 용액을 만드는 세 용액의 값을 찾아라. (오름차순)

# 전체 용액 수 N (3 ~ 5000)
# 특성 값 (-10^9 ~ 10^9)

# input
import sys
input = sys.stdin.readline

N = int(input())
solutions = list(map(int, input().split()))
solutions.sort()

# sol
# 한 개를 고정하고 두 개를 투 포인터로 찾는다.
_min = sys.maxsize
ans = []

for i in range(N-2):    # 최소 세 용액이 필요하므로 N-3부터까지만

    s = i + 1
    e = N - 1

    # 선택한 첫 용액에 적절한 두 용액을 선택함
    total = -1
    while s < e:
        total = solutions[i] + solutions[s] + solutions[e]

        if abs(total) < _min:
            _min = abs(total)
            ans = [solutions[i], solutions[s], solutions[e]]

        if total < 0:
            s += 1
        elif total > 0:
            e -= 1
        else: # total == 0
            break
    
    # 선택한 세 용액의 합이 0이면 더이상 탐색할 필요X
    if total == 0:
        break

print(ans[0], ans[1], ans[2])

# 시간복잡도가 정렬하는게 NlogN이고, 루프가 N^2(for:N, while:N)이라서 범위 안인데
# python 제출하니 시간 초과가 난다.. 
# -> PyPy3로 제출해야 한다

# 이분 탐색을 써도 마찬가지..


# ❗ 그런데 왜 Python3에서는 위의 방법이 시간초과가 나냐?

# 1. 파이썬은 1억 연산/초가 아님
# 25,000,000번 연산이 **파이썬의 일반 연산 속도(4~8백만 연산/초)**를 살짝 넘을 수 있음.

# -> N=5000인 경우에는 파이썬을 사용할 때 N**2의 연산을 사용해야하면 1초안에 못 끝낼 가능성이 매우 높다!!

# 특히 함수 호출, 조건문, 리스트 접근 등 복합 연산이니 단순 int 더하기보다 느림.

# 2. GC 및 인터프리터 오버헤드
# Python3는 GC(Garbage Collector), 인터프리터 실행 등이 중간에 개입됨.
# PyPy는 JIT이지만 Python3는 JIT 없이 인터프리팅만 → 기본 속도 자체가 느림.

# 3. 시간 제한 오차
# 백준의 1초 제한은 실행 + 초기화 + 종료 시간까지 포함이야.
# 실제 연산 가능한 시간은 0.9초 내외일 수도 있음.

# 4. 입력 처리 병목
# 네 코드가 sys.stdin.readline() 써서 최적화는 되어 있지만,
# 파이썬3 자체의 I/O도 PyPy보다 느림.


