# 4779. 칸토어 집합 (S3)
# https://www.acmicpc.net/problem/4779
# N (0~12)
# 3^N 문자열에서 3등분을 하고 가운데를 비우는 작업을
# 모든 문자의 길이가 1일 때까지 반복함

# input
Ns = []
while True:
    try:
        Ns.append(int(input()))
    except:
        break


# solution
# apprch3: 1부터 문자열 만들기
def cantor_approx(i, ans):

    # 종료 조건
    if i == N:
        return ans
    
    # 탐색
    ans = (ans + ' ' * len(ans) + ans)
    ans = cantor_approx(i+1, ans)

    return ans


# output
for N in Ns:
    ans = "-" if N==0 else cantor_approx(1, '- -')
    print(ans)






# --- 
# apprch1 - 문제: string copy가 그렇게 안됨.
# n부터 시작해서
# 가운데 공백으로 바꾸고 남은 것 다시 탐색
# 남은 것이 길이 1이라면 종료

# string = ['-' for _ in range(3**N)]

# def cantor_approx(N, string):

#     if N == 0:
#         return 
        
#     elif N == 1:
#         string[1] = ' '
#         return
    
#     m = 3**(N-1)
#     cantor_approx(N-1, string[:m]) 
    
#     for i in range(m, 2*m+1):
#         string[i] = ' '

#     cantor_approx(N-1, string[2*m+1:])

# cantor_approx(N, string)
# print(''.join(string))

# ---
# apprch2: 번호를 매겨서 숫자를 계산하고 해당 숫자에만 문자를 출력함
# 3^1 123 -> 1 3
# 3^2 123456789 -> 123 789 -> 1 3 7 9