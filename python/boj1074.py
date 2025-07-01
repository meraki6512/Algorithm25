# 1074. Z
# https://www.acmicpc.net/problem/1074
# N(1~15), r, c(0~2^N)
# 2^N*2^N 배열을 4등분해 재귀적으로 순서대로 방문한다
# [r][c]는 몇 번째로 방문했을까?

N, r, c = map(int, input().split())

# 개수만 세고 배열을 실제로 만들 필요는 없지 않을까? 
# -> 0부터 숫자만 계산한다. (방문 순서는 0부터 시작)

# 핵심
# [0][0](순서=0)을 제외하고는 
# 행과 열의 값이 2배가 될 때, 숫자가 4배가 된다.!

def Z(N, r, c):
    
    # 1. divide

    if N == 0:
        return 0

    # 2^(N-1) x 2^(N-1) 크기에서의 r, c 위치로 재귀 호출
    # 더 작은 크기의 사분면까지의 방문 순서를 먼저 구함
    base = Z(N-1, r//2, c//2)

    # 2. conquer

    # 현재 단계의 순서 값 (= 작은 단계의 시작 순서 값의 4배)
    cur_base = 4 * base

    # [0][0]: 0, [0][1]: 1
    # [1][0]: 2, [1][1]: 3
    offset = 2*(r%2) + c%2

    # 실제 순서 값
    return cur_base + offset

print(Z(N, r, c))



# self-feedback
# 다시 풀기

# ---
# # 해당 행과 열이 어느 사분면에 속하는지 계산한다.?
# # 숫자를 계산하면서 해당 행과 열과 같다면 종료한다.?

# answer = 0
# L = 2**N

# def Z(start_r, start_c, end_r, end_c, N):

#     global answer    

#     L = 2**N
#     CL = L//2
    
#     print(start_r, start_c, end_r, end_c, " / ", N, L)

#     if N == 1:
#         if start_r == r and start_c == c:
#             print(answer)
#         answer += 1
#         if start_r == r and end_c == c:
#             print(answer)
#         answer += 1
#         if end_r == r and start_c == c:
#             print(answer)
#         answer += 1
#         if end_r == r and end_c == c:
#             print(answer)
#         answer += 1
        
#         return

#     Z(start_r, start_c, start_r + CL-1, start_c + CL-1, N-1)
#     Z(start_r, CL, start_r + CL, start_c + L-1, N-1)
#     Z(CL, start_c, start_r + L-1, start_c + CL-1, N-1)
#     Z(CL, CL, start_r + L-1, start_c + L-1, N-1)
    
# Z(0, 0, L-1, L-1, N)