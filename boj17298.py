# 17298. 오큰수
# A[i]의 오큰수 NGE(i) 
# : 오른쪽에서 A[i]보다 크면서 가장 왼쪽에 있는 수
# 없으면 -1
# 수열의 크기 N: 10^6 

# 1. 2중 loop
# 굳이 거꾸로 할 필요 없어보임
# i=0부터
import sys
input = sys.stdin.readline
N = int(input()) 
A = list(map(int, input().split()))

def NGEs1():
    NGEs = ""

    for i, a in enumerate(A):   # 10^6

        NGE = -1
        for p in range(i+1, N): # 10^6... ... time out expected
            if a < A[p]:
                NGE = A[p]
                break
        
        NGEs += str(NGE) + " "

    print(NGEs.strip())

# 2. Stack 사용
# -1로 셋팅해두고, 처리하지 않은 원소에 대해서만 업데이트해줌
# A: 3527
# 0. [0] = 5
# 1, [1] = 7
# ,, [2] = 7
# 2  [3] = /

def NGEs2():
    NGEs = [-1] * N 
    stack = [0] # 인덱스

    for i in range(1, N):

        while stack and A[stack[-1]] < A[i]: 
            NGEs[stack.pop()] = A[i]
        
        stack.append(i)
        
    ans = " ".join(map(str, NGEs))
    print(ans)


NGEs2()


# self-feedback
# 나중에 다시 풀기
# https://edder773.tistory.com/259