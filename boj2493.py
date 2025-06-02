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



# * 이 과정이 O(N)이라고 말할 수 있을까?

# N번 push
# pop 횟수 + 루프 (비교)횟수 = N번
# -> O(N+N) -> O(N)

# 예를 들어
# stack = []
# A = [10, 9, 8, 7, 6, 5] (내림차순)
# → 각 수는 전부 stack[-1] < A[i] 조건에서 False
# → while 한 번도 안 돌고 바로 stack.append(i)만 됨
# → 총 N번
# 반대로
# A = [1, 2, 3, 4, 5, 6] (오름차순)
# → 이 경우엔 stack[-1] < A[i]가 항상 True
# → stack은 매번 비워짐 (모든 이전 원소를 다 pop)
# → 각 원소는 pop 되고 나면 다시 비교 대상이 아님
# → 총 N번


# self-feedback
# stack이라는 걸 미리 모르고 풀었으면 어려웠을 문제 .. 
# 스택 사용 고려할 타이밍밍
# 1. 순차 비교 시 과거의 상태를 기억해야하는 경우, 시간복잡도를 고려할 때, 스택 사용 가능
# 2. 재귀를 사용하는 경우, 스택으로 대체 가능