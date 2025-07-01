# 14713. 앵무새
# 1s, 512MB

# 1. 한 앵무새당 한 문장을 말함 (여러 단어로 이뤄짐)
# 2. 단어 간 띄어쓰기에서만 다른 앵무새가 말을 가로챌 수 있음
# 3. 단어 간 중복 없음

# input
from collections import deque
import sys
input = sys.stdin.readline

N = int(input())                                    # 앵무새 수 100
S = [deque(input().split()) for _ in range(N)]      # 문장 (단어 수 100 이하) (각 단어는 32개 이하 문자)
L = deque(input().split())                          # 만든 문장 (10^4)

# sol
possible = True

# 만든 문장 검사
while L:                                            

    keyword = L.popleft()                           # 단어별로 확인
    found = False

    for i in range(N):
        if S[i] and S[i][0] == keyword:             # 각 앵무새가 현재 전할 수 있는 단어 중에 같은게 있으면
            S[i].popleft()
            found = True
            break
    
    if not found:                                   # 없으면
        possible = False
        break

# 앵무새가 할 말을 다 못 전해도 impossible
for i in range(N):                        
    if S[i]:
        possible = False
        break          


# output
ans = "Possible" if possible else "Impossible"
print(ans)