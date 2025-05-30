# 2493. 탑
# 탑의 개수 N, 각 탑의 높이가 주어질 때 수신한 탑들의 번호를 출력하라.

# 각 층의 꼭대기에 송신기
# 반대방향으로 레이저 발사, 가장 먼저 만나는 탑에서 수신
# 6, 9, 5, 7, 4
#          7<-4
#    9 <-- 7
#    9<-5
# x<-9
# 0, 0, 2, 2, 4 (탑의 번호=1부터 시작)

# 아무도 수신하지 못하면 0
# 1.5sec, 128MB
# N: 5*10^5

# input
import sys
input = sys.stdin.readline
N = int(input())
towers = list(map(int, input().split()))

# solution
rcvs = [0] * N  # 답
stack = []      # index 저장

for i in range(0, N): # 앞에서부터 순차적으로 검사 # N

    while stack and towers[stack[-1]] < towers[i]:  # towers[stack.top]이 작은 경우 
        stack.pop()                                 # stack에서 뺌 (towers[i]에서 무조건 걸리게 되어있음)
    
    if stack and towers[stack[-1]] >= towers[i]:    # towers[stack.top]이 크거나 같은 경우 
        rcvs[i] = stack[-1] + 1                     # stack.top + 1을 rcvs에 저장 (pop 하지 않음)
    
    stack.append(i)                                 # 자기 차례가 끝날 때 stack.append(idx)

# output
print(" ".join(map(str, rcvs)))