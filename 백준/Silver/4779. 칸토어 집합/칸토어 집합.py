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