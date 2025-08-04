# N 10^4 # M 3억
# input
N, M = map(int, input().split())
A = list(map(int, input().split()))

# solution
# ap2.
# def two_pointer():
    
#     ans = 0    

#     return 

# # output
# print(ans)



# ap1. N**2 -> time-out
def bf():

    ans = 0    

    p = [0] * N
    p[0] = A[0]

    for i in range(1, N):       # N
        p[i] = A[i] + p[i-1]


    for i in range(N):          # N**2

        if p[i] == M:
            ans += 1
        
        for j in range(i+1, N): 

            if (p[j] - p[i]) == M:
                ans += 1

    return ans


# self-feedback
# 처음 문제 볼 때 제대로 보자.
# 시간 정해놓고 확실히 이해한 다음 접근하기.
